package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Producto;
import com.escalab.repo.IProductoRepo;
import com.escalab.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepo productoRepo;
	
	@Override
	public Producto registrar(Producto obj) {
		return productoRepo.save(obj);
	}

	@Override
	public Producto modificar(Producto obj) {
		return productoRepo.save(obj);
	}

	@Override
	public List<Producto> listar() {
		return productoRepo.findAll();
	}

	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> op = productoRepo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}

	@Override
	public boolean eliminar(Integer id) {
		productoRepo.deleteById(id);
		return true;
	}
}