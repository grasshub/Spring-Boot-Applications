package com.greenfield.springresthateoasservice.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springresthateoasservice.domain.Bookmark;

@Transactional
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
	
	public Collection<Bookmark> findByAccountUsername(String username);
	
	public void deleteAllBookmarksByAccountUsername(String username);
}
