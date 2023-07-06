package com.chtrembl.petstoreapp.petstoreorderreserver.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

	@Value("${azure.storage.connection-string}")
	private String connectionString;

	@Value("${azure.storage.container-name}")
	private String containerName;

	public void update(Map<String, String> map) {
		String fileName = java.util.UUID.randomUUID().toString();
		BlobClient blobClient = new BlobClientBuilder()
				.connectionString(connectionString)
				.containerName(containerName)
				.blobName(fileName)
				.buildClient();

		if (map != null) {
			map.forEach((k, v) -> map.put(k, v != null ? v.toUpperCase() : null));
			map.put("connectionString", connectionString);
			blobClient.upload(BinaryData.fromObject(map));
		}
	}
}
