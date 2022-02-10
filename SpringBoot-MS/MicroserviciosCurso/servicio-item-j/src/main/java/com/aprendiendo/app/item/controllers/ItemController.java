package com.aprendiendo.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aprendiendo.app.item.models.Item;
import com.aprendiendo.app.item.models.Producto;
import com.aprendiendo.app.item.models.service.ItemService;

/**
 * el @RefreshScope sirve para refrescar los componentes inyectados mediante @Autowired
 * de esta forma los cambios que se realicen en los archivos de configuracion se van a ver 
 * reflejados en tiempo real cuando se guarden dichos cambios, como por ejemplo el nombre y email
 * guardados en el archivo de configuracion para el microservicio servicio-items llamado
 * servicio-items-dev.yml ya que estamos accediendo a ese archivo mediante el "Enviroment"
 * que inyectamos, todo esto mediante una ruta (end point) de Spring Actuator (agregar la dependencia)
 * 
 * Se hace el cambio en el/los archivos de configuracion, se agregan los cambios al repo, se hace el
 * commit y luego se hace una peticion POST a la URL local del microservicio donde se cambio la
 * configuracion, ejemplo: localhost:8007/actuator/refresh y tambien sirve con la URL dinamica que
 * se usa mediante el GATEWAY, ejemplo: localhost:9000/items/actuator/refresh
 */
@RefreshScope
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Autowired
	@Qualifier("serviceFeing")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.update(producto, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String port){
		Map<String, String> json = new HashMap<String, String>();
		
		log.info(texto);
		
		json.put("texto", texto);
		json.put("puerto", port);
		
		ResponseEntity<Map<String, String>> resultado = new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
		
		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		
		return resultado;
	}

}
