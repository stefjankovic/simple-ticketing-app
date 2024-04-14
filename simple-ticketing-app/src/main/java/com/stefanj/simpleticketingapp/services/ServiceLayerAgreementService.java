package com.stefanj.simpleticketingapp.services;

import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

public interface ServiceLayerAgreementService {
	ServiceLayerAgreement getById(Long id);
	ServiceLayerAgreement save(ServiceLayerAgreement serviceLayerAgreement);
	void delete(Long id);
//	TODO List<ServiceLayerAgreementService> findAll();
}
