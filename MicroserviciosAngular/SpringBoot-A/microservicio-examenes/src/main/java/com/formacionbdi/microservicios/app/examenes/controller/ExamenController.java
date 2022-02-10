package com.formacionbdi.microservicios.app.examenes.controller;

import java.util.Optional;

import com.formacionbdi.microservicios.app.examenes.service.ExamenService;
import com.formacionbdi.microservicios.commons.controller.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
        ResponseEntity<?> respuesta = ResponseEntity.notFound().build();
        Optional<Examen> opc = this.service.findById(id);
        if (opc.isPresent()){
            Examen examenDb = opc.get();
            examenDb.setNombre(examen.getNombre());
            
            examenDb.getPreguntas()
            .stream()
            .filter(pdb -> !examen.getPreguntas().contains(pdb))
            .forEach(examenDb :: removePregunta);
            System.out.println("\nantes del set");
            examenDb.setPreguntas(examen.getPreguntas());
            System.out.println("despues del set\n");
            System.out.println("a ver que tiene >>>>> "+examenDb.getPreguntas().get(2).toString());
            
            respuesta = ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));

        }
        return respuesta;
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(this.service.findByNombre(term));
    }
    
}
