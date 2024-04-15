package com.stefanj.simpleticketingapp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserGroup extends AbstractEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@ManyToMany(mappedBy = "groups")
	private List<User> users;
}
