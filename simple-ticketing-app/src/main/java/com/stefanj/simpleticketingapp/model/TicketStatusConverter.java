package com.stefanj.simpleticketingapp.model;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TicketStatusConverter implements AttributeConverter<TicketStatus, String> {

	@Override
	public String convertToDatabaseColumn(TicketStatus status) {
		if (status == null) {
			return null;
		}
		return status.getCode();
	}

	@Override
	public TicketStatus convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(TicketStatus.values())
				.filter(p -> p.getCode()
				.equals(code))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
