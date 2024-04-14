package com.stefanj.simpleticketingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.UserGroupDTO;
import com.stefanj.simpleticketingapp.services.UserGroupService;

@RestController
@RequestMapping(path="/api/v1/user-groups", produces="application/json")
public class UserGroupController {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

	private final UserGroupService userGroupService;
	
	public UserGroupController(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserGroupDTO> getUserById(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".getUserById: Called for id (" + id + ").");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.getById(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserGroupDTO> createUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".createUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.save(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UserGroupDTO> updateUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".updateUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.save(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserGroup(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteUserGroup: Called for id (" + id + ").");
		userGroupService.delete(id);
		return ResponseEntity.ok().build();
	}
}
