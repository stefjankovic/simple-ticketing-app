package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stefanj.simpleticketingapp.model.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

}
