package umu.tds.repository;

import java.util.List;

import umu.tds.modelo.Gasto;

public interface RepositorioGasto {
	void save(Gasto gasto);          // Crear o actualizar
    void delete(Gasto gasto);        // Eliminar
    List<Gasto> findAll();           // Recuperar todos
}