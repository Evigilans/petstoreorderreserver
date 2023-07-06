package com.chtrembl.petstoreapp.petstoreorderreserver.functions;

import org.springframework.cloud.function.json.JsonMapper;

import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Function;

@Configuration
public class CartFunction {
	@Bean
	public Function<Message<String>, String> update(JsonMapper mapper) {
		return message -> {
			String value = message.getPayload();
			ExecutionContext context = (ExecutionContext) message.getHeaders().get("executionContext");
			try {
				Map<String, String> map = mapper.fromJson(value, Map.class);

				if (map != null)
					map.forEach((k, v) -> map.put(k, v != null ? v.toUpperCase() : null));

				if (context != null)
					context.getLogger().info(new StringBuilder().append("Function: ")
							.append(context.getFunctionName())
							.append(" is uppercasing ").append(value.toString()).toString());

				return mapper.toString(map);
			} catch (Exception e) {
				e.printStackTrace();
				if (context != null)
					context.getLogger().severe("Function could not parse incoming request");

				return ("Function error: - bad request");
			}
		};
	}
}
