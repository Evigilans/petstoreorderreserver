package com.chtrembl.petstoreapp.petstoreorderreserver.functions;

import com.chtrembl.petstoreapp.petstoreorderreserver.dto.ResponseDTO;
import com.chtrembl.petstoreapp.petstoreorderreserver.entity.Cart;
import com.chtrembl.petstoreapp.petstoreorderreserver.service.CartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Log4j2
public class CartFunction {
	@Autowired
	CartService cartService;

	@Bean("updateCart")
	public Function<Cart, ResponseDTO> update() {
		System.out.println("sout: update Function " + cartService);
		log.info("logger: update Function " + cartService);
		return cart -> cartService.update(cart);
	}
}
