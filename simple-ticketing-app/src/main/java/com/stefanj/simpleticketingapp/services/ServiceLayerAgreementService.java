package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

public interface ServiceLayerAgreementService {
	ServiceLayerAgreement getById(Long id);
	ServiceLayerAgreement save(ServiceLayerAgreement serviceLayerAgreement);
	void delete(Long id);
	List<ServiceLayerAgreement> getAll();
}
