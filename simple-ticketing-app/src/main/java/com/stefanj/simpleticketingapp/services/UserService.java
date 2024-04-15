package com.stefanj.simpleticketingapp.services;

import java.util.List;

import com.stefanj.simpleticketingapp.model.User;

public interface UserService {
	User getById(Long id);
	User save(User user);
	User update(User user);
	void delete(Long id);
	List<User> getAll();
}
