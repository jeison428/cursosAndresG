package com.formacionbdi.microservicios.app.cursos.controller;

import java.util.List;
import java.util.Optional;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.services.CursoService;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.controller.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCurso(@RequestBody Curso curso, @PathVariable Long id){
        ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Curso> opc = this.service.findById(id);
		if (opc.isPresent()) {
			Curso cursoDb = opc.get();
			cursoDb.setNombre(curso.getNombre());
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDb));
		}
		return respuesta;
    }

	@PutMapping("/editar/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Alumno> alumnos){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Curso> opc = this.service.findById(id);
		if (opc.isPresent()) {
			Curso cursoDb = opc.get();
			alumnos.forEach(a -> {
				cursoDb.addAlumno(a);
			});
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDb));
		}
		return respuesta ;
	}

	@PutMapping("/editar/{id}/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Alumno alumno){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Curso> opc = this.service.findById(id);
		if (opc.isPresent()) {
			Curso cursoDb = opc.get();
			cursoDb.removeAlumno(alumno);
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDb));
		}
		return respuesta ;
	}

	@GetMapping(value="/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		Curso curso = this.service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}

	@PutMapping("/editar/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Curso> opc = this.service.findById(id);
		if (opc.isPresent()) {
			Curso cursoDb = opc.get();
			examenes.forEach(e -> {
				cursoDb.addExamen(e);
			});
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDb));
		}
		return respuesta ;
	}

	@PutMapping("/editar/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @RequestBody Examen examen){
		ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
		Optional<Curso> opc = this.service.findById(id);
		if (opc.isPresent()) {
			Curso cursoDb = opc.get();
			cursoDb.removeExamen(examen);
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDb));
		}
		return respuesta ;
	}
	

}
