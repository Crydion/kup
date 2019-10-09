package com.crydion.blog.exceptions;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5039809887067545614L;

	public PostNotFoundException(String msg) {
		super(msg);
	}

}
