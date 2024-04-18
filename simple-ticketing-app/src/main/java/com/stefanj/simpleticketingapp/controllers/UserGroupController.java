package com.stefanj.simpleticketingapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.UserGroupDTO;
import com.stefanj.simpleticketingapp.services.UserGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "UserGroup")
@RequestMapping(path="/api/v1/user-groups", produces="application/json")
public class UserGroupController {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

	private final UserGroupService userGroupService;
	
	public UserGroupController(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@GetMapping
	@Operation(summary = "List all user groups")
	public ResponseEntity<List<UserGroupDTO>> getUserGroups() {
		logger.debug(getClass().getSimpleName() + ".getUserGroups: Start.");
		return new ResponseEntity<>(userGroupService.getAll().stream().map(UserGroupDTO::fromEntity).toList(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get user group", description = "Get user group by id")
	public ResponseEntity<UserGroupDTO> getUserGroupById(@PathVariable @Parameter(description = "Group Id") Long id, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getUserGroupById: Called for id (" + id + ").");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.getById(id, authentication.getName())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PostMapping
	@Operation(summary = "Create user group")
	public ResponseEntity<UserGroupDTO> createUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".createUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.save(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PutMapping
	@Operation(summary = "Update user group")
	public ResponseEntity<UserGroupDTO> updateUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".updateUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.update(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user group")
	public ResponseEntity<String> deleteUserGroup(@PathVariable @Parameter(description = "Group Id") Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteUserGroup: Called for id (" + id + ").");
		userGroupService.delete(id);
		return ResponseEntity.ok().build();
	}
}
