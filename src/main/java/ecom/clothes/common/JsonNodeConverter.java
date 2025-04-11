package ecom.clothes.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert JsonNode to String", e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            return mapper.readTree(dbData);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert String to JsonNode", e);
        }
    }
}
