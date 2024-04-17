package com.stefanj.simpleticketingapp.model;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TicketCategoryConverter implements AttributeConverter<TicketCategory, String> {

	@Override
	public String convertToDatabaseColumn(TicketCategory category) {
		if (category == null) {
			return null;
		}
		return category.getCode();
	}

	@Override
	public TicketCategory convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(TicketCategory.values())
				.filter(p -> p.getCode()
				.equals(code))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}