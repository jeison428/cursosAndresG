package com.formacionbdi.microservicios.app.examenes.service;

import java.util.List;

import com.formacionbdi.microservicios.app.examenes.models.repository.ExamenRepository;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.service.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term){
        return this.repository.findByNombre(term);
    }
    
}
