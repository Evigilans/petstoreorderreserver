package com.chtrembl.petstoreapp.petstoreorderreserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

	@Value("${azure.storage.connection-string}")
	private String connectionString;

	public void update(Map<String, String> map) {
		if (map != null) {
			map.forEach((k, v) -> map.put(k, v != null ? v.toUpperCase() : null));
			map.put("connectionString", connectionString);
		}
	}
}
