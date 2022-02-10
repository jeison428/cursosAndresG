package com.aprendiendo.app.productos.models.service;

import java.util.List;

import com.aprendiendo.app.productos.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto parProducto);
	public void deleteById(Long parId);
	
}
