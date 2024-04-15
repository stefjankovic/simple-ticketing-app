package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.ServiceLayerAgreement;

@Repository
public interface ServiceLayerAgreementRepository extends JpaRepository<ServiceLayerAgreement, Long> {

}
