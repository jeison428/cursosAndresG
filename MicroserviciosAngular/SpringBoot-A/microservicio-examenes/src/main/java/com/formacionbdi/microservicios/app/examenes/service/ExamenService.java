package com.formacionbdi.microservicios.app.examenes.service;

import java.util.List;

import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.service.CommonService;

public interface ExamenService extends CommonService<Examen>{
    
    public List<Examen> findByNombre(String term);
    
}
