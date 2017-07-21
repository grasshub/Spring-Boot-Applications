package com.greenfield.springbootmvc.service;

import java.util.List;

public interface CRUDService<T> {
	
	public List<T> listAll();
	
	public T getById(Integer id);
	
	public T saveOrUpdate(T domainObject);
	
	public void delete(Integer id);
	
}
