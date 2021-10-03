package com.sebavr.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Producto {
	@Id @GeneratedValue( strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="El campo nombre no puede estar vacio.") 
	@Size(min=4,max=255 ,message="El campo nombre debe tener entre 4 y 255 carácteres.")
	private String nombre;
	@Size(max=255 ,message="El campo descripcion no más de 255 carácteres.")
	private String descripcion;
	@Min(value = 0,message="El campo precio debe ser mayor o igual a 0")
	private double precio;
	@Min(value = 0,message="El campo precio debe ser mayor o igual a 0")
	private long stock;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	@NotNull(message="El campo presentacion no debe ser null")
	private Presentacion presentacion;

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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}
	
	

}
