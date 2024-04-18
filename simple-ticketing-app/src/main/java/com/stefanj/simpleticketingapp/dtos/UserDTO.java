package com.stefanj.simpleticketingapp.dtos;

import java.util.List;

import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserGroup;
import com.stefanj.simpleticketingapp.model.UserType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	@Schema(description = "Id of the user")
	private Long id;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Username of the user")
	private String userName;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Password")
	private String password;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Email address of the user")
	private String email;
	@Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Type of the user", allowableValues = {"Admin", "SupportStaff", "Customer", "Auditor"})
	private String userType;
	@Schema(description = "Full (First and Last) name of the user")
	private String fullName;
	@Schema(description = "User state")
	private Boolean active;
	@Schema(description = "List of user groups' ids")
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
