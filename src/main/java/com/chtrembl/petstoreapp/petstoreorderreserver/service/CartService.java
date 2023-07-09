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

	public void update(Map<Object, Object> map) {
		String fileName = java.util.UUID.randomUUID().toString();
		BlobClient blobClient = new BlobClientBuilder()
				.connectionString(connectionString)
				.containerName("msajdsa-blob")
				.blobName(fileName)
				.buildClient();

		if (map != null) {
			blobClient.upload(BinaryData.fromObject(map));
		}
	}
}
