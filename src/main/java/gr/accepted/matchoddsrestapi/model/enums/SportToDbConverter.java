package gr.accepted.matchoddsrestapi.model.enums;

import jakarta.persistence.AttributeConverter;

public class SportToDbConverter implements AttributeConverter<Sport, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Sport attribute) {
        return attribute.getId();
    }

    @Override
    public Sport convertToEntityAttribute(Integer dbData) {
        return Sport.from(dbData);
    }
}
