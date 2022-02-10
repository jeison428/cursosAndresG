package com.formacionbdi.microservicios.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="examenes")
@Getter
@Setter
@AllArgsConstructor
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL},
                orphanRemoval = true, mappedBy = "examen")
    private List<Pregunta> preguntas;

    public Examen (){
        this.preguntas = new ArrayList<>();
    }

    public void setPreguntas(List<Pregunta> preguntas){
        this.preguntas.clear();
        preguntas.forEach(this :: addPregunta);
    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta){
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
			return true;
		}
		if (!(obj instanceof Examen)){
			return false;
		}

		Examen e = (Examen) obj;

		return this.id != null && this.id.equals(e.getId());
	}
}
