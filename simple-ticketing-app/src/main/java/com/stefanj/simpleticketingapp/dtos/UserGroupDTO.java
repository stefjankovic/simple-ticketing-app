package com.stefanj.simpleticketingapp.dtos;

import java.util.List;

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
public class UserGroupDTO {
	private Long id;
	private String name;
	private String description;
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
