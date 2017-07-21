package com.greenfield.springbootmvc.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfield.springbootmvc.domain.Role;
import com.greenfield.springbootmvc.repositories.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	// In order to initialize list of lazily loaded users field before transaction is committed and session is closed,
	// we need to utilize Hibernate class initialize method to fetch this list before transaction is committed.
	// This strategy will resolve standalone application issue with accessing lazily loaded list field after session is closed and could
	// not retrieve the list through the closed session.
	@Override
	public List<Role> listAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		
		for ( Role role: roles ) {
			Hibernate.initialize(role.getUsers());
		}
		
		return roles;
	}

	@Override
	public Role getById(Integer id) {
		Role role = roleRepository.findOne(id);
		
		// Initialize lazily loaded users list before the transaction is committed and session is closed automatically.
		Hibernate.initialize(role.getUsers());
		
		return role;
	}

	@Override
	public Role saveOrUpdate(Role domainObject) {
		return roleRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		roleRepository.delete(id);	
	}

}
