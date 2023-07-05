package com.chtrembl.petstoreapp.petstoreorderreserver.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	private boolean status;
	private String message;
	private Object data;
}
