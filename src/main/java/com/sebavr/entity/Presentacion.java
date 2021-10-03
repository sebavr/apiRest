package com.sebavr.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table
public class Presentacion {
	@Id @GeneratedValue( strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="El campo nombre no puede estar vacio.") 
	@Size(min=4,max=255 ,message="El campo nombre debe tener entre 4 y 255 carácteres.")
	private String nombre;
	@Size(max=255 ,message="El campo descripcion no más de 255 carácteres.")
	private String descripcion;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy="presentacion")
	private List<Producto> productos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
