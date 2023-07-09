package com.chtrembl.petstoreapp.petstoreorderreserver.function;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CartFunction {

//	private final CartService cartService;
//
//	@Bean
//	public Function<Message<String>, String> update(JsonMapper mapper) {
//		return message -> {
//			String value = message.getPayload();
//			ExecutionContext context = (ExecutionContext) message.getHeaders().get("executionContext");
//			try {
//				Map<String, String> map = mapper.fromJson(value, Map.class);
//
//				cartService.update(map);
//
//				if (context != null) {
//					context.getLogger().info("Function: " + context.getFunctionName() + " is uppercasing " + value);
//				}
//
//				return mapper.toString(map);
//			} catch (Exception e) {
//				e.printStackTrace();
//				if (context != null)
//					context.getLogger().severe("Function could not parse incoming request");
//
//				return ("Function error: - bad request: \n" + e.getMessage() + " \n cause: " + e.getCause());
//			}
//		};
//	}
}
