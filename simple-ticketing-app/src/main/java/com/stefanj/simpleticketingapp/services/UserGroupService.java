package com.stefanj.simpleticketingapp.services;

import com.stefanj.simpleticketingapp.model.UserGroup;

public interface UserGroupService {
	UserGroup getById(Long id);
	UserGroup save(UserGroup userGroup);
	void delete(Long id);
}
