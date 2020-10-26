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
import com.escalab.model.Venta;
import com.escalab.service.IVentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private IVentaService ventaService;

	@GetMapping
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> supervisores = ventaService.listar();
		return new ResponseEntity<List<Venta>>(supervisores, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> leerPorId(@PathVariable("id") Integer id){
		Venta obj = ventaService.leerPorId(id);
		if (obj.getIdVenta() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta){
		Venta obj = ventaService.registrar(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta venta){
		Venta obj = ventaService.modificar(venta);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Venta obj = ventaService.leerPorId(id);
		if (obj.getIdVenta() == null){
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		ventaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}