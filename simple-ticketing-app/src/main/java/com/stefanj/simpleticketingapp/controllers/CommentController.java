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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stefanj.simpleticketingapp.dtos.CommentDTO;
import com.stefanj.simpleticketingapp.services.CommentService;

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
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.save(CommentDTO.toEntity(commentDTO))), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.update(CommentDTO.toEntity(commentDTO))), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<CommentDTO> deleteComment(@PathVariable(name = "ticketid") Long id) {
		logger.debug(getClass().getSimpleName() + ".deleteComment: Called for id (" + id + ").");
		commentService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentDTO> getComment(Long id, Authentication authentication) {
		return new ResponseEntity<>(CommentDTO.fromEntity(commentService.getById(id, authentication.getName())), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> getCommentsByTicketId(@RequestParam(required = true) Long ticketId, Authentication authentication) {
		return new ResponseEntity<>(commentService.getByTicketId(ticketId, authentication.getName()).stream().map(CommentDTO::fromEntity).toList(), HttpStatus.OK);
	}
}
