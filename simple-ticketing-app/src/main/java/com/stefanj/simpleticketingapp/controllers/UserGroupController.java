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
	public ResponseEntity<List<UserGroupDTO>> getUserGroups() {
		logger.debug(getClass().getSimpleName() + ".getUserGroups: Start.");
		return new ResponseEntity<>(userGroupService.getAll().stream().map(sla -> UserGroupDTO.fromEntity(sla)).toList(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserGroupDTO> getUserGroupById(@PathVariable Long id, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getUserGroupById: Called for id (" + id + ").");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.getById(id, authentication.getName())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PostMapping
	public ResponseEntity<UserGroupDTO> createUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".createUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.save(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PutMapping
	public ResponseEntity<UserGroupDTO> updateUserGroup(UserGroupDTO userGroupDTO) {
		logger.debug(getClass().getSimpleName() + ".updateUserGroup: Called for " + userGroupDTO + ".");
		return new ResponseEntity<>(UserGroupDTO.fromEntity(userGroupService.update(UserGroupDTO.toEntity(userGroupDTO))), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserGroup(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteUserGroup: Called for id (" + id + ").");
		userGroupService.delete(id);
		return ResponseEntity.ok().build();
	}
}
