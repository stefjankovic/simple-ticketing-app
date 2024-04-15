package com.stefanj.simpleticketingapp.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.exceptions.ErrorCode;
import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;
import com.stefanj.simpleticketingapp.repositories.ServiceLayerAgreementRepository;
import com.stefanj.simpleticketingapp.services.ServiceLayerAgreementService;

@Service
public class ServiceLayerAgreementServiceImpl implements ServiceLayerAgreementService {
private static final Logger logger = LoggerFactory.getLogger(ServiceLayerAgreementServiceImpl.class);
	
	private final ServiceLayerAgreementRepository serviceLayerAgreementRepository;
	
	public ServiceLayerAgreementServiceImpl(ServiceLayerAgreementRepository serviceLayerAgreementRepository) {
		this.serviceLayerAgreementRepository = serviceLayerAgreementRepository;
	}

	@Override
	public ServiceLayerAgreement getById(Long id) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<ServiceLayerAgreement> sla = serviceLayerAgreementRepository.findById(id);
		if (sla.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		return sla.get();
	}

	@Override
	public ServiceLayerAgreement save(ServiceLayerAgreement serviceLayerAgreement) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		ServiceLayerAgreement savedSla = serviceLayerAgreementRepository.save(serviceLayerAgreement);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedSla.getId() + "'.");
		return savedSla;
	}

	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		Optional<ServiceLayerAgreement> sla = serviceLayerAgreementRepository.findById(id);
		if (sla.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		serviceLayerAgreementRepository.delete(sla.get());
	}

	@Override
	public List<ServiceLayerAgreement> getAll() {
		logger.debug(getClass().getSimpleName() + ".findAll: Start.");
		return serviceLayerAgreementRepository.findAll();
	}
}
