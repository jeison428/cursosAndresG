package com.formacionbdi.microservicios.app.cursos.models.repository;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    @Query("select c from Curso c join fetch c.alumnos a where a.id = ?1")
    public Curso findCursoByAlumnoId(Long id);
}
