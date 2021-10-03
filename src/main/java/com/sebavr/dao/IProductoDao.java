package com.sebavr.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sebavr.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{
	
	@Query(value="select p from Producto p left join fetch p.presentacion")
	public List<Producto> findAll(Sort sort);
	
	//paginacion de todos ordenados por pageable
	//pageable ya lleva el sort dentro
	//Page muestra 10 en 10, 20 en 20
	@Query(value="select p from Producto p left join fetch p.presentacion",
			countQuery ="select count(p) from Producto p left join p.presentacion" )
	public Page<Producto> findAll(Pageable pageable);
	
	@Query(value="select p from Producto p left join fetch p.presentacion where p.id=:id")
	public Producto findById(long id);

}
