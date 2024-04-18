package com.stefanj.simpleticketingapp.dtos;

import java.util.List;

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
public class UserGroupDTO {
	@Schema(description = "Id of the user group")
	private Long id;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the user group")
	private String name;
	@Schema(description = "Description of the user group")
	private String description;
	@Schema(description = "List of user ids which belong to the user group")
	private List<Long> userIds;
	
	public static UserGroup toEntity(UserGroupDTO userGroupDTO) {
		UserGroup userGroup = new UserGroup();
		userGroup.setId(userGroupDTO.getId());
		userGroup.setName(userGroupDTO.getName());
		userGroup.setDescription(userGroupDTO.getDescription());
		userGroup.setUsers(userGroupDTO.getUserIds().stream().map(u -> {
			User user = new User();
			user.setId(u);
			return user;
		}).toList());
		return userGroup;
	}
	
	public static UserGroupDTO fromEntity(UserGroup userGroup) {
		return UserGroupDTO.builder()
				.id(userGroup.getId())
				.name(userGroup.getName())
				.description(userGroup.getDescription())
				.userIds(userGroup.getUsers().stream().map(u -> u.getId()).toList())
				.build();
	}
}
