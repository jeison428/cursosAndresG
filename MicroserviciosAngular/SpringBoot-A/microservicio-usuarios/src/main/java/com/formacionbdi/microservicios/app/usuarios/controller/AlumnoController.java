package com.formacionbdi.microservicios.app.usuarios.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.services.AlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controller.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{
	
	@PutMapping("/editar")
	public ResponseEntity<?> editarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Alumno> alumnoOp = this.service.findById(id);
		if (alumnoOp.isEmpty()) {
			respuesta = ResponseEntity.notFound().build();
		}else {
			Alumno alumnoDb = alumnoOp.get();
			alumnoDb.setNombre(alumno.getNombre());
			alumnoDb.setApellido(alumno.getApellido());
			alumnoDb.setEmail(alumno.getEmail());
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(alumnoDb));
		}
		return respuesta;
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	
}
