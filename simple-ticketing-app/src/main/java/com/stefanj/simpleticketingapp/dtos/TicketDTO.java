package com.stefanj.simpleticketingapp.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.TicketCategory;
import com.stefanj.simpleticketingapp.model.TicketStatus;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
	@Schema(description = "Id of the ticket")
	private Long id;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Title of the ticket")
	private String title;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Description of the ticket")
	private String description;
	@Schema(description = "Category of the ticket", allowableValues = {"ServiceRequest", "Incident", "Problem", "ChangeRequest"})
	private String category;
	@Schema(description = "Status of the ticket", allowableValues = {"Open", "InProgress", "OnHold", "Resolved", "Closed"})
	private String status;
	@Schema(description = "Service layer agreement related to the ticket")
	private ServiceLayerAgreementDTO sla;
	@Schema(description = "Date and time when ticket was resolved")
	private LocalDateTime resolvedDate;
	@Schema(description = "User to whom the ticked is assigned")
	private UserDTO assignedTo;
	@Schema(description = "User who created the ticket")
	private UserDTO createdBy;
	@Schema(description = "User who resolved the ticket")
	private UserDTO resolvedBy;
	@Schema(description = "The group to which the ticket belongs")
	private UserGroupDTO userGroup;
	@Schema(description = "List of the ticket's comments")
	private List<Long> commentIds;
	
	public static Ticket toEntity(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		ticket.setId(ticketDTO.getId());
		ticket.setTitle(ticketDTO.getTitle());
		ticket.setDescription(ticketDTO.getDescription());
		ticket.setCategory(TicketCategory.fromValue(ticketDTO.getCategory()));
		ticket.setStatus(TicketStatus.fromValue(ticketDTO.getStatus()));
		ticket.setSla(ServiceLayerAgreementDTO.toEntity(ticketDTO.getSla()));
		
		User resolvedByUser = new User();
		resolvedByUser.setId(ticketDTO.getResolvedBy().getId());
		ticket.setResolvedBy(resolvedByUser);
		
		User assignedToUser = new User();
		assignedToUser.setId(ticketDTO.getAssignedTo().getId());
		ticket.setAssignedTo(assignedToUser);
		
		User createdByUser = new User();
		createdByUser.setId(ticketDTO.getCreatedBy().getId());
		ticket.setCreatedBy(createdByUser);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setId(ticketDTO.getUserGroup().getId());
		ticket.setUserGroup(userGroup);
		ticket.setComments(ticketDTO.getCommentIds().stream().map(id -> {
			Comment comment = new Comment();
			comment.setId(id);
			return comment;
		}).toList());
		return ticket;
	}
	
	public static TicketDTO fromEntity(Ticket ticket) {
		return TicketDTO.builder()
				.id(ticket.getId())
				.title(ticket.getTitle())
				.description(ticket.getDescription())
				.category(ticket.getCategory().getCode())
				.status(ticket.getStatus().getCode())
				.sla(ServiceLayerAgreementDTO.fromEntity(ticket.getSla()))
				.resolvedDate(ticket.getResolvedDate().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDateTime())
				.resolvedBy(UserDTO.fromEntity(ticket.getResolvedBy()))
				.assignedTo(UserDTO.fromEntity(ticket.getAssignedTo()))
				.createdBy(UserDTO.fromEntity(ticket.getCreatedBy()))
				.userGroup(UserGroupDTO.fromEntity(ticket.getUserGroup()))
				.commentIds(ticket.getComments().stream().map(c -> c.getId()).toList())
				.build();
	}
}
