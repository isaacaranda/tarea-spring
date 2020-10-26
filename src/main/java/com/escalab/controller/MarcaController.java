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
import com.escalab.model.Marca;
import com.escalab.service.IMarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private IMarcaService marcaService;
	
	@GetMapping
	public ResponseEntity<List<Marca>> listar(){
		List<Marca> marcas = marcaService.listar();
		return new ResponseEntity<List<Marca>>(marcas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> listarPorId(@PathVariable("id") Integer id){
		Marca obj = marcaService.leerPorId(id);
		if (obj.getIdMarca() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Marca>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Marca marca){
		Marca obj = marcaService.registrar(marca);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getIdMarca()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Marca> modificar(@Valid @RequestBody Marca marca){
		Marca obj = marcaService.modificar(marca);
		return new ResponseEntity<Marca>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Marca obj = marcaService.leerPorId(id);
		if (obj.getIdMarca() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		marcaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}