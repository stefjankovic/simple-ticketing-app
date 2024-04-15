package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.UserGroup;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

}
