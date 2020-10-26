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
import com.escalab.model.Vendedor;
import com.escalab.service.IVendedorService;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private IVendedorService vendedorService;

	@GetMapping
	public ResponseEntity<List<Vendedor>> listar(){
		List<Vendedor> supervisores = vendedorService.listar();
		return new ResponseEntity<List<Vendedor>>(supervisores, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> leerPorId(@PathVariable("id") Integer id){
		Vendedor obj = vendedorService.leerPorId(id);
		if (obj.getIdVendedor() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Vendedor>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Vendedor vendedor){
		Vendedor obj = vendedorService.registrar(vendedor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(obj.getIdVendedor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Vendedor> modificar(@Valid @RequestBody Vendedor vendedor){
		Vendedor obj = vendedorService.modificar(vendedor);
		return new ResponseEntity<Vendedor>(obj, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Vendedor obj = vendedorService.leerPorId(id);
		if (obj.getIdVendedor() == null){
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		vendedorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}