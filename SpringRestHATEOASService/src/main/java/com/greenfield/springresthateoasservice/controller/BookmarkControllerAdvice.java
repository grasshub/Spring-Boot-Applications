package com.greenfield.springresthateoasservice.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.greenfield.springresthateoasservice.exception.BookmarkNotFoundException;
import com.greenfield.springresthateoasservice.exception.UserNotFoundException;

@ControllerAdvice
public class BookmarkControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public VndErrors userNotFoundExceptionHandler(UserNotFoundException ex) {
		return new VndErrors("Error ", ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookmarkNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public VndErrors bookmarkNotFoundExceptionHandler(BookmarkNotFoundException ex) {
		return new VndErrors("Error ", ex.getMessage());
	}

}
