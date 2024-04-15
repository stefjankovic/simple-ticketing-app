package com.stefanj.simpleticketingapp.model;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

	@Override
	public String convertToDatabaseColumn(UserType type) {
		if (type == null) {
			return null;
		}
		return type.getCode();
	}

	@Override
	public UserType convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(UserType.values())
				.filter(p -> p.getCode()
				.equals(code))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
