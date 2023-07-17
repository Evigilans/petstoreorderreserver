package com.chtrembl.petstoreapp.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class CartUpdateFunction extends FunctionInvoker<Message<String>, String> {

	@FunctionName("cartUpdate")
	public void cartUpdate(
			@ServiceBusQueueTrigger(
					name = "message",
					queueName = "msjad-reserve-queue",
					connection = "serviceBusConnectionString")
			String message,
			ExecutionContext context) {
		context.getLogger().info("cartUpdate: received the following message from ServiceBus: ");
		context.getLogger().info(message);

		Message<String> input = MessageBuilder.withPayload(message).build();
		handleRequest(input, context);
	}
}
