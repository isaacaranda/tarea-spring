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
import com.escalab.model.Supervisor;
import com.escalab.service.ISupervisorService;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

	@Autowired
	private ISupervisorService  supervisorService;

	@GetMapping
	public ResponseEntity<List<Supervisor>> listar(){
		List<Supervisor> supervisores = supervisorService.listar();
		return new ResponseEntity<List<Supervisor>>(supervisores, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Supervisor> leerPorId(@PathVariable("id") Integer id){
		Supervisor obj = supervisorService.leerPorId(id);
		if (obj.getIdSupervisor() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Supervisor>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Supervisor supervisor){
		Supervisor obj = supervisorService.registrar(supervisor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(obj.getIdSupervisor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Supervisor> modificar(@Valid @RequestBody Supervisor supervisor){
		Supervisor obj = supervisorService.modificar(supervisor);
		return new ResponseEntity<Supervisor>(obj, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Supervisor obj = supervisorService.leerPorId(id);
		if (obj.getIdSupervisor() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		supervisorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}