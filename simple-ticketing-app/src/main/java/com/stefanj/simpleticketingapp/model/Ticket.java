package com.stefanj.simpleticketingapp.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Entity
public class Ticket extends AbstractEntity {
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	private TicketCathegory cathegory;
	private TicketStatus status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sla_fk", nullable = false)
	private ServiceLayerAgreement sla;
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolvedDate;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_fk", nullable = false)
	private User assignedTo;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_fk", nullable = false)
	private User createdBy;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by_user_fk", nullable = false)
	private User resolvedBy;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_fk", nullable = false)
	private UserGroup userGroup;
	@OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Comment> comments;
}
