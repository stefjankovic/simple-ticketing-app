package com.stefanj.simpleticketingapp.dtos;

import com.stefanj.simpleticketingapp.model.SLAPriority;
import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceLayerAgreementDTO {
	@Schema(description = "Id of the service layer agreement")
	private Long id;
	@Schema(description = "Name of the service layer agreement")
	private String name;
	@Schema(description = "Description of the service layer agreement")
	private String description;
	@Schema(description = "Ticket priority")
	private String priority;
	@Schema(description = "Ticket response time in minutes")
	private Integer responseTime;
	@Schema(description = "Ticket resolution time in minutes")
	private Integer resolutionTime;
	
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
