package com.greenfield.springresthateoasservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springresthateoasservice.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	public Optional<Account> findByUsername(String username);
}
