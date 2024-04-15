package com.stefanj.simpleticketingapp.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.stefanj.simpleticketingapp.model.Comment;
import com.stefanj.simpleticketingapp.model.Ticket;
import com.stefanj.simpleticketingapp.model.TicketCathegory;
import com.stefanj.simpleticketingapp.model.TicketStatus;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserGroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
	private Long id;
	private String title;
	private String description;
	private String cathegory;
	private String status;
	private ServiceLayerAgreementDTO sla;
	private LocalDateTime resolvedDate;
	private UserDTO assignedTo;
	private UserDTO createdBy;
	private UserDTO resolvedBy;
	private UserGroupDTO userGroup;
	private List<Long> commentIds;
	
	public static Ticket toEntity(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		ticket.setId(ticketDTO.getId());
		ticket.setTitle(ticketDTO.getTitle());
		ticket.setDescription(ticketDTO.getDescription());
		ticket.setCathegory(TicketCathegory.fromValue(ticketDTO.getCathegory()));
		ticket.setStatus(TicketStatus.fromValue(ticketDTO.getStatus()));
		ticket.setSla(ServiceLayerAgreementDTO.toEntity(ticketDTO.getSla()));
		ticket.setResolvedDate(Date.from(ticketDTO.getResolvedDate().atZone(ZoneId.systemDefault()).toInstant()));
		
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
		return null;
	}
}
