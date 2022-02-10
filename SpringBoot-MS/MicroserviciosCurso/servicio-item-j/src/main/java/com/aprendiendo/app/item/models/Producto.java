package com.aprendiendo.app.item.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {

	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;
	private int port;
	
}
