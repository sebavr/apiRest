package com.sebavr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sebavr.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll(Sort sort);
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Producto findById(long id);
	
	public void delete(long id);
	
	public Producto save(Producto producto);

}
