package com.stefanj.simpleticketingapp.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.repositories.UserRepository;
import com.stefanj.simpleticketingapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getById(Long id) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<User> user = userRepository.findById(id);
		// TODO if (sla.isEmpty()) throw new NotFoundException();
		return user.get();
	}

	@Override
	public User save(User user) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		User savedUser = userRepository.save(user);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedUser.getId() + "'.");
		return savedUser;
	}

	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		userRepository.deleteById(id);
	}
}
