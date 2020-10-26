package com.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Producto;
import com.escalab.service.IProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService  productoService;

	@GetMapping
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> productos = productoService.listar();
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> leerPorId(@PathVariable("id") Integer id){
		Producto obj = productoService.leerPorId(id);
		if (obj.getIdProducto() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto){
		Producto obj = productoService.registrar(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto){
		Producto obj = productoService.modificar(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Producto obj = productoService.leerPorId(id);
		if (obj.getIdProducto() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		productoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}