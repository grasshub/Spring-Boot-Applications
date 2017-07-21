package com.greenfield.springresthateoasservice.exception;

public class BookmarkNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1135617790862728192L;

	public BookmarkNotFoundException(Long id, String username) {
		super("Could not find bookmark id " + id + " with username " + username);
	}
}

