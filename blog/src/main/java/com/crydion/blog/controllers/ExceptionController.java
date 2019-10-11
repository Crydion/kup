package com.crydion.blog.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crydion.blog.dtos.ErrorDTO;
import com.crydion.blog.exceptions.PostNotFoundException;
import com.crydion.blog.exceptions.SwearwordException;

@RestControllerAdvice
public class ExceptionController {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

	private static final String SOMETHING_WENT_WRONG = "Something went wrong - {}";
	
	@ExceptionHandler(SwearwordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleSwearwordException(SwearwordException ex) {
		UUID code = UUID.randomUUID();
		return new ErrorDTO()
			.setStatus(HttpStatus.BAD_REQUEST.value())
			.setMessage(ex.getMessage())
			.setCode(code);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handlePostNotFoundException(PostNotFoundException ex) {
		UUID code = UUID.randomUUID();
		LOG.error(SOMETHING_WENT_WRONG, code);
		LOG.info(ex.getMessage());
		return new ErrorDTO()
			.setStatus(HttpStatus.NOT_FOUND.value())
			.setMessage(ex.getMessage())
			.setCode(code);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handleException(Exception ex) {
		UUID code = UUID.randomUUID();
		LOG.error(SOMETHING_WENT_WRONG, code, ex);
		return new ErrorDTO()
			.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.setMessage(ex.getMessage())
			.setCode(code);
	}

}
