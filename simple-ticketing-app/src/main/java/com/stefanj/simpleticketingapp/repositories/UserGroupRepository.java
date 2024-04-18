package com.stefanj.simpleticketingapp.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.stefanj.simpleticketingapp.model.UserGroup;

@Repository
public interface UserGroupRepository extends ListCrudRepository<UserGroup, Long> {
	boolean existsByIdAndUsersUserName(Long id, String userName);
}
