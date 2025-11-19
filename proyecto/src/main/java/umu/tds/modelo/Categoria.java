package umu.tds.modelo;

/*
- id : Integer
- nombre : String
--
Métodos del controlador referentes a las historias de usuario:
+ addGasto()
+ removeGasto()
+ modifyGasto()
*/

public class Categoria {
	private Integer id;
	private String nombre;
	
	public Categoria(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Categoria (String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getID() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
}