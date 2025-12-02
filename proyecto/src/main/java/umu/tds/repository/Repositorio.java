package umu.tds.repository;

import java.util.List;

public interface Repositorio<T> {
	void save(T instancia);          // Crear o actualizar
    void delete(T instancia);        // Eliminar
    List<T> findAll();           // Recuperar todos
}