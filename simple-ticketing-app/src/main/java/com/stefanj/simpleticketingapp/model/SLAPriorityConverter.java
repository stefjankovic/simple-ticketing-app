package com.stefanj.simpleticketingapp.model;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter(autoApply = true)
public class SLAPriorityConverter implements AttributeConverter<SLAPriority, String> {

	@Override
	public String convertToDatabaseColumn(SLAPriority attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
	}

	@Override
	public SLAPriority convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        
        return Stream.of(SLAPriority.values())
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
	}
}