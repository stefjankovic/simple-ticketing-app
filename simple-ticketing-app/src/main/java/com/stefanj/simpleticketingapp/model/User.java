package com.stefanj.simpleticketingapp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ApplicationUser")
public class User extends AbstractEntity {
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private UserType type;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private Boolean active;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "appuser_group",
		joinColumns = @JoinColumn(name = "appuser_id"),
		inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<UserGroup> groups;
	@OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY)
	private List<Ticket> tickets;
}
