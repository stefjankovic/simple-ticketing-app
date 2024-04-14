package com.stefanj.simpleticketingapp.services;

import com.stefanj.simpleticketingapp.model.User;

public interface UserService {
	User getById(Long id);
	User save(User user);
	void delete(Long id);
}
