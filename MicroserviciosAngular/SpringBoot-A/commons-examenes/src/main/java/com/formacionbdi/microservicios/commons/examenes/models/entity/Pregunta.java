package com.formacionbdi.microservicios.commons.examenes.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @JsonIgnoreProperties(value = {"preguntas"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id")
    private Examen examen;

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
			return true;
		}
		if (!(obj instanceof Pregunta)){
			return false;
		}

		Pregunta p = (Pregunta) obj;

		return this.id != null && this.id.equals(p.getId());
	}

    @Override
    public String toString() {
        if(this.examen != null)
            return "ID: " + this.id + " - Texto: " + this.texto + " - Examen: " + this.examen.getNombre();
        return "ID: " + this.id + "Texto: " + this.texto ;
    }

}
