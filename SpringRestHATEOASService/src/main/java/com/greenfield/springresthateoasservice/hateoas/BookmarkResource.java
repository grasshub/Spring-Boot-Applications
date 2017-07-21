package com.greenfield.springresthateoasservice.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.greenfield.springresthateoasservice.controller.BookmarkRestController;
import com.greenfield.springresthateoasservice.domain.Bookmark;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookmarkResource extends ResourceSupport {
	
	private Bookmark bookmark;
	
	// Add the default constructor to avoid RestClient Json to Object conversion error.
	public BookmarkResource() { }
	
	public BookmarkResource(Bookmark bookmark) {
		String username = bookmark.getAccount().getUsername();
		
		this.bookmark = bookmark;
		
		add(new Link(bookmark.getUri(), "bookmark-uri"));
		add(linkTo(BookmarkRestController.class, username).withRel("bookmarks"));
		add(linkTo(methodOn(BookmarkRestController.class, username).getBookmark(username, bookmark.getId())).withSelfRel());
	}
	
	public Bookmark getBookmark() {
		return bookmark;
	}

}
