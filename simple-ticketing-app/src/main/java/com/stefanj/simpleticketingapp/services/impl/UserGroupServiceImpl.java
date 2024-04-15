package com.stefanj.simpleticketingapp.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stefanj.simpleticketingapp.exceptions.ErrorCode;
import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.UserGroup;
import com.stefanj.simpleticketingapp.repositories.UserGroupRepository;
import com.stefanj.simpleticketingapp.services.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);
	
	private final UserGroupRepository userGroupRepository;
	
	public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}

	@Override
	public UserGroup getById(Long id) {
		logger.debug(getClass().getSimpleName() + ".getById: id (" + id + ").");
		Optional<UserGroup> userGroup = userGroupRepository.findById(id);
		if (userGroup.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		return userGroup.get();
	}

	@Override
	public UserGroup save(UserGroup userGroup) {
		logger.debug(getClass().getSimpleName() + ".save: Start.");
		UserGroup savedUserGroup = userGroupRepository.save(userGroup);
		logger.debug(getClass().getSimpleName() + ".save: End. Id = '" + savedUserGroup.getId() + "'.");
		return savedUserGroup;
	}

	@Override
	public void delete(Long id) {
		logger.debug(getClass().getSimpleName() + ".delete: id (" + id + ").");
		Optional<UserGroup> userGroup = userGroupRepository.findById(id);
		if (userGroup.isEmpty()) throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, new HashMap<String, Object>(Map.of("id", id)));
		userGroupRepository.delete(userGroup.get());
	}

	@Override
	public List<UserGroup> getAll() {
		logger.debug(getClass().getSimpleName() + ".findAll: Start.");
		return userGroupRepository.findAll();
	}
}
