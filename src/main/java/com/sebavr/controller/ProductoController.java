package com.sebavr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sebavr.entity.Producto;
import com.sebavr.service.IProductoService;

@RestController
@RequestMapping(value="/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> findAll(@RequestParam(required=false) Integer page,
												@RequestParam(required=false) Integer size){
		Sort sortByName=Sort.by("nombre");
		
		ResponseEntity<List<Producto>> responseEntity=null;
		
		List<Producto> productos=null;
		
		if(page!=null && size!=null) {
			//con paginacion
			Pageable pageable=PageRequest.of(page, size, sortByName);
			
			productos=productoService.findAll(pageable).getContent();

		}else {
			//sin paginacion
			productos=productoService.findAll(sortByName);
		}
		if(productos.size()>0) {
			responseEntity= new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
		}else {
			responseEntity= new ResponseEntity<List<Producto>>( HttpStatus.NO_CONTENT);
		}
		return responseEntity;	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Producto> findById(@PathVariable long id){
		
		Producto producto=productoService.findById(id);
		
		ResponseEntity<Producto> responseEntity=null;
		
		if(producto!=null) {
			responseEntity=new ResponseEntity<Producto>(producto,HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<Producto>(producto,HttpStatus.NO_CONTENT);
			
		}
		return responseEntity;
		
	}
	
	@PostMapping
	public ResponseEntity<Map<String,Object>> insert(@Valid @RequestBody Producto producto,BindingResult result){
		
		Map<String,Object> responseAsMap=new HashMap<String,Object>();
		ResponseEntity<Map<String,Object>> responseEntity=null;
		
		List<String> errores=null;
		if(result.hasErrors()) {
			errores= new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errores.add(error.getDefaultMessage());	
			}
			responseAsMap.put("errors", errores);
			responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		try {
			Producto productoFromDB= productoService.save(producto);
			
			if(productoFromDB!=null) {
				responseAsMap.put("producto",producto);
				responseAsMap.put("mensaje","El producto con id "+producto.getId()+" se ha creado exitosamente.");
				responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK);
			}else {
				responseAsMap.put("mensaje","El producto no se ha creado exitosamente.");
				responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e){
			responseAsMap.put("mensaje","El producto no se ha creado exitosamente."+e.getMostSpecificCause().toString());
			responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
		
		return responseEntity;
	}
	
//actualizar
	@PutMapping(value="/{id}")
	public ResponseEntity<Map<String,Object>> update(@PathVariable long id,@Valid @RequestBody Producto producto,BindingResult result){
		
		Map<String,Object> responseAsMap=new HashMap<String,Object>();
		ResponseEntity<Map<String,Object>> responseEntity=null;
		
		List<String> errores=null;
		if(result.hasErrors()) {
			errores= new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errores.add(error.getDefaultMessage());	
			}
			responseAsMap.put("errors", errores);
			responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		try {
			producto.setId(id);
			Producto productoFromDB= productoService.save(producto);
			
			if(productoFromDB!=null) {
				responseAsMap.put("producto",producto);
				responseAsMap.put("mensaje","El producto con id "+producto.getId()+" se ha actualizado exitosamente.");
				responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.OK);
			}else {
				responseAsMap.put("mensaje","El producto no se ha actualizado exitosamente.");
				responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e){
			responseAsMap.put("mensaje","El producto no se ha actualizado exitosamente."+e.getMostSpecificCause().toString());
			responseEntity=new ResponseEntity<Map<String,Object>>(responseAsMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
		
		return responseEntity;
	}


}
