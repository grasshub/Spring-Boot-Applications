package com.greenfield.springresthateoasservice.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.greenfield.springresthateoasservice.hateoas.BookmarkResource;
import com.greenfield.springresthateoasservice.domain.Bookmark;
import com.greenfield.springresthateoasservice.exception.BookmarkNotFoundException;
import com.greenfield.springresthateoasservice.exception.UserNotFoundException;
import com.greenfield.springresthateoasservice.repository.AccountRepository;
import com.greenfield.springresthateoasservice.repository.BookmarkRepository;

// For real code of OAuth2 environment ready, you could remove username from URI and method parameter and replace it with Principal that will be injected by Spring Security.
@RestController
@RequestMapping("/{username}/bookmarks")
public class BookmarkRestController {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private void validateUser(String username) {
		accountRepository.findByUsername(username).orElseThrow( () -> new UserNotFoundException(username) );
	}
	
	// User context like principal injects by Spring Security
	@RequestMapping(method = RequestMethod.GET) 
	public List<BookmarkResource> listBookmarks(@PathVariable String username) {
		validateUser(username);
	
		return bookmarkRepository.findByAccountUsername(username)
														.stream().map(BookmarkResource::new)
														.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public BookmarkResource getBookmark(@PathVariable String username, @PathVariable("id") Long id) {
		validateUser(username);
		
		// Find bookmarks belongs to specific user
		Collection<Bookmark> bookmarks = bookmarkRepository.findByAccountUsername(username);
		
		if ( bookmarks.contains(bookmarkRepository.findOne(id)) )
			return new BookmarkResource(bookmarkRepository.findOne(id));
		else
			throw new BookmarkNotFoundException(id, username);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBookmark(@PathVariable String username, @RequestBody Bookmark bookmark) {
		validateUser(username);
		
		return accountRepository.findByUsername(username)
				.map( account -> {
						Bookmark result = bookmarkRepository.save(new Bookmark(account, bookmark.getUri(), bookmark.getDescription()));
						// Create the link for self
						Link resultLink = new BookmarkResource(result).getLink("self");
						
						return ResponseEntity.created(URI.create(resultLink.getHref())).build();
				})
				.orElse(ResponseEntity.noContent().build());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBookmark(@PathVariable String username, @PathVariable("id") Long id, @RequestBody Bookmark bookmark) {
		validateUser(username);
		
		return accountRepository.findByUsername(username)
				.map( account -> {	
						// Find bookmarks belongs to specific user
						Collection<Bookmark> bookmarks = bookmarkRepository.findByAccountUsername(username);
						
						Bookmark existingBookmark = bookmarkRepository.findOne(id);
						
						if ( existingBookmark == null || !bookmarks.contains(existingBookmark))
							throw new BookmarkNotFoundException(id, username);				
						
						existingBookmark.setUri(bookmark.getUri());
						existingBookmark.setDescription(bookmark.getDescription());
						
						bookmarkRepository.save(existingBookmark);
							
						return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.noContent().build());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteBookmark(@PathVariable String username, @PathVariable("id") Long id) {	
		validateUser(username);
		
		// Find bookmarks belongs to specific user
		Collection<Bookmark> bookmarks = bookmarkRepository.findByAccountUsername(username);
		
		Bookmark bookmark = bookmarkRepository.findOne(id);
		
		if ( bookmark == null || !bookmarks.contains(bookmark))
			throw new BookmarkNotFoundException(id, username);	
		
		bookmarkRepository.delete(bookmark);
	
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteAllBookmarks(@PathVariable String username) {
		validateUser(username);
		
		bookmarkRepository.deleteAllBookmarksByAccountUsername(username);
		
		return ResponseEntity.ok().build();	
	}

}
