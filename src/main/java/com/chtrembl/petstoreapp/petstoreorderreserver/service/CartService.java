package com.chtrembl.petstoreapp.petstoreorderreserver.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

	public void update(Map<String, String> map) {
		String fileName = java.util.UUID.randomUUID().toString();
		BlobClient blobClient = new BlobClientBuilder()
				.connectionString(System.getenv("CONNECTION_STRING"))
				.containerName("msajdsa")
				.blobName(fileName)
				.buildClient();

		if (map != null) {
			map.forEach((k, v) -> map.put(k, v != null ? v.toUpperCase() : null));
			blobClient.upload(BinaryData.fromObject(map));
		}
	}
}
