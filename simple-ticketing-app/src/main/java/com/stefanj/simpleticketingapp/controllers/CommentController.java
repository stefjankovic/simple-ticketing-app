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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.CommentDTO;
import com.stefanj.simpleticketingapp.services.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Comment")
@RequestMapping(path="/api/v1/comments", produces="application/json")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Customer') or hasAuthority('SCOPE_SupportStaff')")
	@PostMapping
	@Operation(summary = "Create comment", description = "Create comment")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, Authentication authentication) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.save(CommentDTO.toEntity(commentDTO), authentication.getName())), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update comment", description = "Update comment")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO, Authentication authentication) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.update(CommentDTO.toEntity(commentDTO), authentication.getName())), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete comment", description = "Delete comment")
	public ResponseEntity<CommentDTO> deleteComment(Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteComment: Called for id (" + id + ").");
		commentService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get comment", description = "Get comment by id")
	public ResponseEntity<CommentDTO> getComment(Long id, Authentication authentication) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.getById(id, authentication.getName())), HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "List all comments in ticket", description = "List all comments which belong to the ticket")
	public ResponseEntity<List<CommentDTO>> getCommentsByTicketId(@RequestParam(required = true) Long ticketId, Authentication authentication) {
		return new ResponseEntity<>(commentService.getByTicketId(ticketId, authentication.getName()).stream().map(CommentDTO::fromEntity).toList(), HttpStatus.OK);
	}
}
