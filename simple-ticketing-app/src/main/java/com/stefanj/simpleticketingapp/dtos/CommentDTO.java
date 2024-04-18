package com.stefanj.simpleticketingapp.dtos;

import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "Id of the comment")
	private Long id;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Id of the ticket to which comment belongs")
	private Long ticketId;
	@Schema(description = "Id of the user which created a comment")
	private Long userId;
	@NotBlank(message = "Comment message is mandatory")
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Comment message")
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
