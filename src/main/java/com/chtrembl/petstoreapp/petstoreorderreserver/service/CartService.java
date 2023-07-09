package com.chtrembl.petstoreapp.petstoreorderreserver.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

	public static final String ID = "id";

	@Value("${azure.storage.connection-string}")
	private String connectionString;

	public void update(Map<String, Object> map) {
		if (map != null) {
			String sessionId = (String) map.get(ID);
			map.remove(sessionId);

			BlobClient blobClient = new BlobClientBuilder()
					.connectionString(connectionString)
					.containerName("msajdsa-blob")
					.blobName(sessionId)
					.buildClient();
			blobClient.upload(BinaryData.fromObject(map));
		}
	}
}
