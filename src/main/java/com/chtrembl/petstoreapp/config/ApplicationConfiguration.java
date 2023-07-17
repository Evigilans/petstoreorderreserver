package com.chtrembl.petstoreapp.config;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.chtrembl.petstoreapp.service.CartService;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpStatus;
import lombok.AllArgsConstructor;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
public class ApplicationConfiguration {

	private final CartService cartService;
	private final ServiceBusSenderClient senderClient;

	@Bean
	public Function<Message<String>, String> cartUpdate(JsonMapper mapper) {
		return message -> {
			ExecutionContext context = (ExecutionContext) message.getHeaders().get("executionContext");
			String orderJSON = message.getPayload();

			try {
				if (context != null) {
					context.getLogger().info("Executing update function...");
				}

				Map<String, Object> map = mapper.fromJson(message.getPayload(), Map.class);
				cartService.update(map, context);
			} catch (Exception exception) {
				if (context != null) {
					context.getLogger().severe("An error occurred: " + exception.getMessage());
					context.getLogger().severe("Forwarding error message to " + senderClient.getEntityPath());
				}

				senderClient.sendMessage(new ServiceBusMessage(orderJSON));
				senderClient.close();
			}

			return HttpStatus.OK.toString();
		};
	}
}
