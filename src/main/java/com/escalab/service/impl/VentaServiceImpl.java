package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Venta;
import com.escalab.repo.IVentaRepo;
import com.escalab.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepo ventaRepo;
	
	@Override
	public Venta registrar(Venta obj) {
		return ventaRepo.save(obj);
	}

	@Override
	public Venta modificar(Venta obj) {
		return ventaRepo.save(obj);
	}

	@Override
	public List<Venta> listar() {
		return ventaRepo.findAll();
	}

	@Override
	public Venta leerPorId(Integer id) {
		Optional<Venta> op = ventaRepo.findById(id);
		return op.isPresent() ? op.get() : new Venta();
	}

	@Override
	public boolean eliminar(Integer id) {
		ventaRepo.deleteById(id);
		return true;
	}
	
}