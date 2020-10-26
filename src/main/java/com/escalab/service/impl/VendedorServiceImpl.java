package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Vendedor;
import com.escalab.repo.IVendedorRepo;
import com.escalab.service.IVendedorService;

@Service
public class VendedorServiceImpl implements IVendedorService {
	
	@Autowired
	private IVendedorRepo vendedorRepo;

	@Override
	public Vendedor registrar(Vendedor obj) {
		return vendedorRepo.save(obj);
	}

	@Override
	public Vendedor modificar(Vendedor obj) {
		return vendedorRepo.save(obj);
	}

	@Override
	public List<Vendedor> listar() {
		return vendedorRepo.findAll();
	}

	@Override
	public Vendedor leerPorId(Integer id) {
		Optional<Vendedor> op = vendedorRepo.findById(id);
		return op.isPresent() ? op.get() : new Vendedor();
	}

	@Override
	public boolean eliminar(Integer id) {
		vendedorRepo.deleteById(id);
		return true;
	}

}
