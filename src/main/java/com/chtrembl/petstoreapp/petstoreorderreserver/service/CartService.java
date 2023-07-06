package com.chtrembl.petstoreapp.petstoreorderreserver.service;

import com.chtrembl.petstoreapp.petstoreorderreserver.dto.ResponseDTO;
import com.chtrembl.petstoreapp.petstoreorderreserver.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	public ResponseDTO update(Cart cart) {
		System.out.println("CartService.update");

		ResponseDTO responseDTO = new ResponseDTO();

		if (cart != null) {
			responseDTO.setStatus(true);
			responseDTO.setMessage("Success!");
			responseDTO.setData(cart);
		} else {
			responseDTO.setStatus(false);
			responseDTO.setMessage("Failure!");
		}

		return responseDTO;
	}
}
