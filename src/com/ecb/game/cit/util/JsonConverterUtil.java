package com.ecb.game.cit.util;

import java.io.IOException;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverterUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toString(final Object object)
			throws JsonGenerationException,
			JsonMappingException,
			IOException {
		return mapper.writeValueAsString(object);
	}

	public static <T> T toObject(final String jsonString, final Class<T> clazz)
			throws JsonParseException,
			JsonMappingException,
			IOException {
		T obj = null;

		if (jsonString == null) {
			throw new InvalidParameterException("jsonString is null.");
		}
		obj = mapper.readValue(jsonString, clazz);

		return obj;
	}
}
