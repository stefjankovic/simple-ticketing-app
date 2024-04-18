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

import com.stefanj.simpleticketingapp.dtos.UserDTO;
import com.stefanj.simpleticketingapp.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User")
@RequestMapping(path="/api/v1/users", produces="application/json")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@GetMapping
	@Operation(summary = "List all users")
	public ResponseEntity<List<UserDTO>> getUsers(Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getUsers: Start.");
		logger.debug(authentication.getAuthorities().toString());
		return new ResponseEntity<>(userService.getAll().stream().map(UserDTO::fromEntity).toList(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get user", description = "Get user by id")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id, Authentication authentication) {
		logger.debug(getClass().getSimpleName() + ".getUserById: Called for id (" + id + ").");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.getById(id, authentication.getName())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PostMapping
	@Operation(summary = "Create user")
	public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
		logger.debug(getClass().getSimpleName() + ".createUser: Called for " + userDTO + ".");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.save(UserDTO.toEntity(userDTO))), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@PutMapping
	@Operation(summary = "Update user")
	public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
		logger.debug(getClass().getSimpleName() + ".updateUser: Called for " + userDTO + ".");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.update(UserDTO.toEntity(userDTO))), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteUser: Called for id (" + id + ").");
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
}
