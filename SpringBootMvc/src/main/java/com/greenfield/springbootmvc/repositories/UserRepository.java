package com.greenfield.springbootmvc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springbootmvc.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByUsername(String usernmae);
}
