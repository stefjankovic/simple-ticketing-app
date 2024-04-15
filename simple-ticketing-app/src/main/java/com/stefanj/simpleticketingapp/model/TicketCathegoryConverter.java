package com.stefanj.simpleticketingapp.model;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TicketCathegoryConverter implements AttributeConverter<TicketCathegory, String> {

	@Override
	public String convertToDatabaseColumn(TicketCathegory cathegory) {
		if (cathegory == null) {
			return null;
		}
		return cathegory.getCode();
	}

	@Override
	public TicketCathegory convertToEntityAttribute(String code) {
		if (code == null) {
			return null;
		}

		return Stream.of(TicketCathegory.values())
				.filter(p -> p.getCode()
				.equals(code))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}