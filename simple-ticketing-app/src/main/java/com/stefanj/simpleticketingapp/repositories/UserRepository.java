package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stefanj.simpleticketingapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
