package com.stefanj.simpleticketingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.ServiceLayerAgreementDTO;
import com.stefanj.simpleticketingapp.services.ServiceLayerAgreementService;

@RestController
@RequestMapping(path="/api/v1/sla", produces="application/json")
public class ServiceLayerAgreementController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceLayerAgreementController.class);
	
	private final ServiceLayerAgreementService serviceLayerAgreementService;
	
	public ServiceLayerAgreementController(ServiceLayerAgreementService serviceLayerAgreementService) {
		this.serviceLayerAgreementService = serviceLayerAgreementService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ServiceLayerAgreementDTO> getSLAById(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".getSLAById: Called for id (" + id + ").");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.getById(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ServiceLayerAgreementDTO> createSLA(ServiceLayerAgreementDTO slaDTO) {
		logger.debug(getClass().getSimpleName() + ".createSLA: Called for " + slaDTO + ".");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.save(ServiceLayerAgreementDTO.toEntity(slaDTO))), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ServiceLayerAgreementDTO> updateSLA(ServiceLayerAgreementDTO slaDTO) {
		logger.debug(getClass().getSimpleName() + ".updateSLA: Called for " + slaDTO + ".");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.save(ServiceLayerAgreementDTO.toEntity(slaDTO))), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSLA(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteSLA: Called for id (" + id + ").");
		serviceLayerAgreementService.delete(id);
		return ResponseEntity.ok().build();
	}
}
