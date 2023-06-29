package me.jiniworld.rabbit.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.jiniworld.rabbit.exception.ExceptionMessage;
import me.jiniworld.rabbit.exception.MyBusinessException;

public class JacksonConverter {
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new JsonMapper()
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(SerializationFeature.INDENT_OUTPUT, true)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, false)
      .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
      .registerModule(new JavaTimeModule());
  }
  private JacksonConverter() { }

  public static ObjectMapper getInstance() {
    return objectMapper;
  }

  public static String toJson(Object value) {
    try{
      return objectMapper.writeValueAsString(value);
    } catch (Exception e) {
      throw new MyBusinessException(ExceptionMessage.CONVERT_ERROR.getMessage());
    }
  }

  public static <T> T toObject(String content, Class<T> valueType) {
    try {
      return objectMapper.readValue(content, valueType);
    } catch (Exception e) {
      throw new MyBusinessException(ExceptionMessage.CONVERT_ERROR.getMessage());
    }
  }

  public static <T> T toObject(String content, TypeReference<T> type) {
    try {
      return objectMapper.readValue(content, type);
    } catch (Exception e) {
      throw new MyBusinessException(ExceptionMessage.CONVERT_ERROR.getMessage());
    }
  }

  public static <T> T convert(Object obj, Class<T> type) {
    try {
      return objectMapper.convertValue(obj, type);
    } catch (Exception e) {
      throw new MyBusinessException(ExceptionMessage.CONVERT_ERROR.getMessage());
    }
  }
}
