package com.chtrembl.petstoreapp.petstoreorderreserver.function;

import com.chtrembl.petstoreapp.petstoreorderreserver.service.CartService;
import com.microsoft.azure.functions.ExecutionContext;
import lombok.AllArgsConstructor;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
public class CartFunction {

	public static final String OK_RESPONSE = "OK";

	private final CartService cartService;

	@Bean
	public Function<Message<String>, String> update(JsonMapper mapper) {
		return message -> {
			ExecutionContext context = (ExecutionContext) message.getHeaders().get("executionContext");

			try {
				Map<String, Object> map = mapper.fromJson(message.getPayload(), Map.class);
				if (context != null) {
					context.getLogger().info("Function: " + context.getFunctionName() + " is... ");
				}

				cartService.update(map);
				return OK_RESPONSE;
			} catch (Exception e) {
				if (context != null) {
					context.getLogger().severe("Function could not parse incoming request");
				}
				return ("Function error: - bad request: \n" + e.getMessage() + " \n cause: " + e.getCause());
			}
		};
	}

	@Bean
	public Function<Message<String>, String> updateCart(JsonMapper mapper) {
		return message -> {
			ExecutionContext context = (ExecutionContext) message.getHeaders().get("executionContext");

			try {
				Map<String, Object> map = mapper.fromJson(message.getPayload(), Map.class);
				if (context != null) {
					context.getLogger().info("Function: " + context.getFunctionName() + " is... ");
				}

				cartService.update(map);
				return OK_RESPONSE;
			} catch (Exception e) {
				if (context != null) {
					context.getLogger().severe("Function could not parse incoming request");
				}
				return ("Function error: - bad request: \n" + e.getMessage() + " \n cause: " + e.getCause());
			}
		};
	}
}
