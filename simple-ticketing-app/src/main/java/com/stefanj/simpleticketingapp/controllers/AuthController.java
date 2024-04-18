package com.stefanj.simpleticketingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.services.impl.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication")
@RequestMapping(path="/api/v1/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private final TokenService tokenService;
	
	public AuthController(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@PostMapping("/token")
	@SecurityRequirement(name = "Basic Authentication")
	@Operation(summary = "Get access token", description = "Get access token based on provided username and password")
	public String token(Authentication authentication) {
		logger.debug("Token requested for user: " + authentication.getName());
		String token = tokenService.generateToken(authentication);
		logger.debug("Token: " + token);
		return token;
	}
}
