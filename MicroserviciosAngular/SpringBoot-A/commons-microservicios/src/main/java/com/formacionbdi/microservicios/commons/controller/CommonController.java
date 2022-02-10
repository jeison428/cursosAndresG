package com.formacionbdi.microservicios.commons.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.microservicios.commons.service.CommonService;

public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarAlumnos(){
		return ResponseEntity.ok().body(this.service.findAll());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarAlumno(@PathVariable Long id){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<E> entity = this.service.findById(id);
		if (! entity.isEmpty()) {
			respuesta = ResponseEntity.ok().body(entity.get());
		}
		return respuesta;
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearAlumno(@RequestBody E entity){
		E entityDb = this.service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id){
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
