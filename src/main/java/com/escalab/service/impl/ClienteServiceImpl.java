package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Cliente;
import com.escalab.repo.IClienteRepo;
import com.escalab.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepo clienteRepo;
	
	@Override
	public Cliente registrar(Cliente obj) {
		return clienteRepo.save(obj);
	}

	@Override
	public Cliente modificar(Cliente obj) {
		return clienteRepo.save(obj);
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}

	@Override
	public Cliente leerPorId(Integer id) {
		Optional<Cliente> op = clienteRepo.findById(id);
		return op.isPresent() ? op.get() : new Cliente();
	}

	@Override
	public boolean eliminar(Integer id) {
		clienteRepo.deleteById(id);
		return true;
	}
}