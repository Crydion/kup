package com.crydion.blog.dtos;

import java.util.UUID;

public class ErrorDTO {

	private Integer status;

	private String message;

	private UUID code;

	public Integer getStatus() {
		return status;
	}

	public ErrorDTO setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ErrorDTO setMessage(String message) {
		this.message = message;
		return this;
	}

	public UUID getCode() {
		return code;
	}

	public ErrorDTO setCode(UUID code) {
		this.code = code;
		return this;
	}

}
