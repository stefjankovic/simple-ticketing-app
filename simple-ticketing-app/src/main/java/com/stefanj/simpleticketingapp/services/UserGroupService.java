package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.UserGroup;

public interface UserGroupService {
	UserGroup getById(Long id);
	UserGroup save(UserGroup userGroup);
	void delete(Long id);
	List<UserGroup> getAll();
}
