package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
