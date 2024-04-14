package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stefanj.simpleticketingapp.model.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

}
