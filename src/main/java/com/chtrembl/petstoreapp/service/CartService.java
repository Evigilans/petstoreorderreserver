package com.chtrembl.petstoreapp.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

	public static final String ID = "id";

	@Value("${azure.storage.connection-string}")
	private String connectionString;

	@Value("${azure.storage.container-name}")
	private String containerName;

	public void update(Map<String, Object> map, ExecutionContext context) {
		if (map != null) {
			String sessionId = (String) map.get(ID);
			map.remove(sessionId);

			if (context != null) {
				context.getLogger().info(String.format("Saving reservation message %s to %s", sessionId, containerName));
			}

			BlobClient blobClient = new BlobClientBuilder()
					.connectionString(connectionString)
					.containerName(containerName)
					.blobName(sessionId)
					.buildClient();
			blobClient.upload(BinaryData.fromObject(map), true);
		}
	}
}
