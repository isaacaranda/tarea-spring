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
import com.escalab.model.Cliente;
import com.escalab.service.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService; 
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> clientes = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id){
		Cliente obj = clienteService.leerPorId(id);
		if (obj.getIdCliente() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cliente cliente){
		Cliente obj = clienteService.registrar(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente cliente){
		Cliente obj = clienteService.modificar(cliente);
		return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Cliente obj = clienteService.leerPorId(id);
		if (obj.getIdCliente() == null) {
			throw new ModeloNotFoundException(String.format("Id %s no encontrado", id));
		}
		clienteService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}