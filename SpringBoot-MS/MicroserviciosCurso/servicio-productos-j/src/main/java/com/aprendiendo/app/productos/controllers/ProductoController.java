package com.aprendiendo.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aprendiendo.app.productos.models.entity.Producto;
import com.aprendiendo.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Value("${server.port}")
	private int port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(prod -> {
			prod.setPort(port);
			return prod;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto prod = productoService.findById(id);
		prod.setPort(port);
		return prod;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto parProducto) {
		return productoService.save(parProducto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto parProducto, @PathVariable Long parId) {
		Producto productoDB = productoService.findById(parId);
		
		productoDB.setNombre(parProducto.getNombre());
		productoDB.setPrecio(parProducto.getPrecio());
		
		return productoService.save(productoDB);
	}
	
	@DeleteMapping("/eliminar/id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long parId) {
		productoService.deleteById(parId);
	}

}
