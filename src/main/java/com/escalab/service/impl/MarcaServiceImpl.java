package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Marca;
import com.escalab.repo.IMarcaRepo;
import com.escalab.service.IMarcaService;

@Service
public class MarcaServiceImpl implements IMarcaService {
	
	@Autowired
	private IMarcaRepo marcaRepo;

	@Override
	public Marca registrar(Marca obj) {
		return marcaRepo.save(obj);
	}

	@Override
	public Marca modificar(Marca obj) {
		return marcaRepo.save(obj);
	}

	@Override
	public List<Marca> listar() {
		return marcaRepo.findAll();
	}

	@Override
	public Marca leerPorId(Integer id) {
		Optional<Marca> op = marcaRepo.findById(id);
		return op.isPresent() ? op.get() : new Marca();
	}

	@Override
	public boolean eliminar(Integer id) {
		marcaRepo.deleteById(id);
		return true;
	}

}