package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

@Repository
public interface ServiceLayerAgreementRepository extends ListCrudRepository<ServiceLayerAgreement, Long> {

}
