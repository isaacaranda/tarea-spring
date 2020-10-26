package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Supervisor;
import com.escalab.repo.ISupervisorRepo;
import com.escalab.service.ISupervisorService;

@Service
public class SupervisorServiceImpl implements ISupervisorService {

	@Autowired
	private ISupervisorRepo supervisorRepo;
	
	@Override
	public Supervisor registrar(Supervisor obj) {
		return supervisorRepo.save(obj);
	}

	@Override
	public Supervisor modificar(Supervisor obj) {
		return supervisorRepo.save(obj);
	}

	@Override
	public List<Supervisor> listar() {
		return supervisorRepo.findAll();
	}

	@Override
	public Supervisor leerPorId(Integer id) {
		Optional<Supervisor> op = supervisorRepo.findById(id);
		return op.isPresent() ? op.get() : new Supervisor();
	}

	@Override
	public boolean eliminar(Integer id) {
		supervisorRepo.deleteById(id);
		return true;
	}

}