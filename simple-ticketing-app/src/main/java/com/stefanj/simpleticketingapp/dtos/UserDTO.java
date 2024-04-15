package com.stefanj.simpleticketingapp.dtos;

import java.util.List;

import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserGroup;
import com.stefanj.simpleticketingapp.model.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private Long id;
	private String userName;
	private String password;
	private String email;
	private String userType;
	private String fullName;
	private Boolean active;
	private List<Long> userGroupIds;
	
	public static User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.getActive());
		user.setFullName(userDTO.getFullName());
		user.setType(UserType.fromValue(userDTO.getUserType()));
		user.setGroups(userDTO.getUserGroupIds().stream().map(u -> {
			UserGroup userGroup = new UserGroup();
			userGroup.setId(u);
			return userGroup;
		}).toList());
		return user;
	}
	
	public static UserDTO fromEntity(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.userType(user.getType().getCode())
				.fullName(user.getFullName())
				.active(user.getActive())
				.userGroupIds(user.getGroups().stream().map(g -> g.getId()).toList())
				.build();
	}
}
