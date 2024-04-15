package com.stefanj.simpleticketingapp.dtos;

import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
	private Long id;
	private Long ticketId;
	private Long userId;
	@NotBlank(message = "Comment is mandatory")
	private String text;
	
	public static Comment toEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		
		Ticket ticket = new Ticket();
		ticket.setId(commentDTO.getTicketId());
		comment.setTicket(ticket);
		
		User user = new User();
		user.setId(commentDTO.getUserId());
		comment.setUser(user);
		comment.setText(commentDTO.getText());
		return comment;
	}
	
	public static CommentDTO fromEntity(Comment comment) {
		return CommentDTO.builder()
				.id(comment.getId())
				.ticketId(comment.getTicket().getId())
				.userId(comment.getUser().getId())
				.text(comment.getText())
				.build();
	}
}
