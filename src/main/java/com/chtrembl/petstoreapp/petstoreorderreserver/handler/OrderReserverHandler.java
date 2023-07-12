package com.chtrembl.petstoreapp.petstoreorderreserver.handler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Optional;

public class OrderReserverHandler extends FunctionInvoker<Message<String>, String> {

	@FunctionName("update")
	public String update(@HttpTrigger(name = "update", methods = {HttpMethod.POST},
			authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
						 ExecutionContext context) {
		Message<String> message = MessageBuilder.withPayload(request.getBody().get()).copyHeaders(request.getHeaders()).build();
		return handleRequest(message, context);
	}

	@FunctionName("updateCart")
	public String updateCart(@ServiceBusQueueTrigger(name = "updateCart",
			queueName = "msjad-reverve-queue",
			connection = "msjad-servicebus") String message,
									ExecutionContext context) {
		context.getLogger().info(message);
		Message<String> input = MessageBuilder.withPayload(message).build();
		return handleRequest(input, context);
	}
}
