package com.aprendiendo.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aprendiendo.app.productos.models.dao.ProductoDAO;
import com.aprendiendo.app.productos.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private ProductoDAO productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto parProducto) {
		return productoDao.save(parProducto);
	}

	@Override
	@Transactional
	public void deleteById(Long parId) {
		productoDao.deleteById(parId);
	}

}
