package com.stefanj.simpleticketingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Comment extends AbstractEntity {
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_fk", nullable = false)
	private Ticket ticket;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
	private User user;
	private String text;
}
