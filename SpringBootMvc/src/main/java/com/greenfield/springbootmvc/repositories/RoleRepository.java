package com.greenfield.springbootmvc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springbootmvc.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
