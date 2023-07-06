package com.chtrembl.petstoreapp.petstoreorderreserver.controller;

import com.chtrembl.petstoreapp.petstoreorderreserver.dto.ResponseDTO;
import com.chtrembl.petstoreapp.petstoreorderreserver.entity.Cart;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import java.util.Optional;

@Log4j2
public class OrderReserverHandler extends AzureSpringBootRequestHandler<Object, ResponseDTO> {

	@FunctionName("updateCart")
	public ResponseDTO update(
			@HttpTrigger(name = "updateCartRequest", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Cart>> request,
			ExecutionContext context) {
		System.out.println("OrderItemsReserverController.update");
		log.error("OrderReserverHandler");
		return handleRequest(request.getBody().get(), context);
	}
}
