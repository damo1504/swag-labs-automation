package com.saucedemo.swaglabs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T readJsonFromFile(String filePath, Class<T> valueType) throws IOException {
		return objectMapper.readValue(new File(filePath), valueType);
	}
}
