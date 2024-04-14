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

import com.stefanj.simpleticketingapp.dtos.UserDTO;
import com.stefanj.simpleticketingapp.services.UserService;

@RestController
@RequestMapping(path="/api/v1/users", produces="application/json")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".getUserById: Called for id (" + id + ").");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.getById(id)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
		logger.debug(getClass().getSimpleName() + ".createUser: Called for " + userDTO + ".");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.save(UserDTO.toEntity(userDTO))), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
		logger.debug(getClass().getSimpleName() + ".updateUser: Called for " + userDTO + ".");
		return new ResponseEntity<>(UserDTO.fromEntity(userService.save(UserDTO.toEntity(userDTO))), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteUser: Called for id (" + id + ").");
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
}
