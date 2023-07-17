package com.chtrembl.petstoreapp.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.HttpFunctionInvoker;

import java.util.Optional;

public class HealthCheckFunction extends HttpFunctionInvoker<String> {

	@FunctionName("healthCheck")
	public HttpResponseMessage healthCheck(
			@HttpTrigger(
					name = "healthCheck",
					methods = {HttpMethod.GET},
					authLevel = AuthorizationLevel.ANONYMOUS)
			HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {
		context.getLogger().info("Executing health check function.");
		return request.createResponseBuilder(HttpStatus.OK).body("Hello world!").build();
	}
}
