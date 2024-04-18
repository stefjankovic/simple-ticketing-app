package com.stefanj.simpleticketingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class ServiceLayerAgreement extends AbstractEntity {
	@Column(nullable = false)
	private String name;
	private String description;
	@Column(nullable = false)
	private SLAPriority priority;
	@Column(nullable = false)
	private Integer responseTime;
	@Column(nullable = false)
	private Integer resolutionTime;
}
