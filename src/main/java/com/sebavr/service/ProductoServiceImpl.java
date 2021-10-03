package com.sebavr.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebavr.dao.IPresentacionDao;
import com.sebavr.dao.IProductoDao;
import com.sebavr.entity.Presentacion;
import com.sebavr.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IPresentacionDao presentacionDao;

	@Override
	@Transactional(readOnly = true)//indica que son solo lectura
	public List<Producto> findAll(Sort sort) {
		return productoDao.findAll(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(long id) {
		return productoDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		Presentacion presentacion=presentacionDao.
				findById(producto.getPresentacion().getId()).orElse(null);
		producto.setPresentacion(presentacion);
		return productoDao.save(producto);
	}

}
