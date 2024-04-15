package com.stefanj.simpleticketingapp.dtos;

import com.stefanj.simpleticketingapp.model.SLAPriority;
import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceLayerAgreementDTO {
	private Long id;
	private String name;
	private String description;
	private String priority;
	private Long responseTime;
	private Long resolutionTime;
	
	public static ServiceLayerAgreement toEntity(ServiceLayerAgreementDTO slaDTO) {
		ServiceLayerAgreement sla = new ServiceLayerAgreement();
		sla.setId(slaDTO.getId());
		sla.setName(slaDTO.getName());
		sla.setDescription(slaDTO.getDescription());
		sla.setPriority(SLAPriority.fromValue(slaDTO.getPriority()));
		sla.setResponseTime(slaDTO.getResponseTime());
		sla.setResolutionTime(slaDTO.getResolutionTime());
		return sla;
	}
	
	public static ServiceLayerAgreementDTO fromEntity(ServiceLayerAgreement sla) {
		return ServiceLayerAgreementDTO.builder()
				.id(sla.getId())
				.name(sla.getName())
				.description(sla.getDescription())
				.priority(sla.getPriority().getCode())
				.responseTime(sla.getResponseTime())
				.resolutionTime(sla.getResolutionTime())
				.build();
	}
}
