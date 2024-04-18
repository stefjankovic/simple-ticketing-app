package com.stefanj.simpleticketingapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.ServiceLayerAgreementDTO;
import com.stefanj.simpleticketingapp.services.ServiceLayerAgreementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "ServiceLayerAgreement")
@RequestMapping(path="/api/v1/slas", produces="application/json")
public class ServiceLayerAgreementController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceLayerAgreementController.class);
	
	private final ServiceLayerAgreementService serviceLayerAgreementService;
	
	public ServiceLayerAgreementController(ServiceLayerAgreementService serviceLayerAgreementService) {
		this.serviceLayerAgreementService = serviceLayerAgreementService;
	}
	
	@GetMapping
	@Operation(summary = "List all SLAs", description = "List all service layer agreements")
	public ResponseEntity<List<ServiceLayerAgreementDTO>> getSLAs() {
		logger.debug(getClass().getSimpleName() + ".getSLAs: Start.");
		return new ResponseEntity<>(serviceLayerAgreementService.getAll().stream().map(ServiceLayerAgreementDTO::fromEntity).toList(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get SLA", description = "Get service layer agreement by id")
	public ResponseEntity<ServiceLayerAgreementDTO> getSLAById(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".getSLAById: Called for id (" + id + ").");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.getById(id)), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PostMapping
	@Operation(summary = "Create SLA", description = "Create service layer agreement")
	public ResponseEntity<ServiceLayerAgreementDTO> createSLA(ServiceLayerAgreementDTO slaDTO) {
		logger.debug(getClass().getSimpleName() + ".createSLA: Called for " + slaDTO + ".");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.save(ServiceLayerAgreementDTO.toEntity(slaDTO))), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PutMapping
	@Operation(summary = "Update SLA", description = "Update service layer agreement")
	public ResponseEntity<ServiceLayerAgreementDTO> updateSLA(ServiceLayerAgreementDTO slaDTO) {
		logger.debug(getClass().getSimpleName() + ".updateSLA: Called for " + slaDTO + ".");
		return new ResponseEntity<>(ServiceLayerAgreementDTO.fromEntity(serviceLayerAgreementService.update(ServiceLayerAgreementDTO.toEntity(slaDTO))), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete SLA", description = "Delete service layer agreement")
	public ResponseEntity<String> deleteSLA(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteSLA: Called for id (" + id + ").");
		serviceLayerAgreementService.delete(id);
		return ResponseEntity.ok().build();
	}
}
