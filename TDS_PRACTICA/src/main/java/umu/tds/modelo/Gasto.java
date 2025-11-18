package umu.tds.modelo;

import java.time.LocalDate;

/*
- id : Integer
- nombre : String
- fecha : LocalDateTime
- cantidad : Double
--
+ actualizar() -> operación del Controlador relacionada con esta clase
*/

public class Gasto {
	private Integer id;				//identifica al gasto
	private String nombreGasto;
	private LocalDate fecha;
	private Double cantidad;
	private Categoria categoria;
	
	public Gasto(Integer id, String nombre, LocalDate fecha, Double cantidad, Categoria categoria) {
		this.id = id;				//necesario para identificar al objeto en la Base de Datos
		this.nombreGasto = nombre;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.categoria = categoria;
	}
	
	public Gasto(String nombre, LocalDate fecha, Double cantidad, Categoria categoria) {
		this.nombreGasto = nombre;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.categoria = categoria;
	}
	
	//A parte de necesitar los métodos get(), incluiremos los métodos set()
	// para los updates de los gastos
	public Integer getID() {
		return id;
	}
	
	public String getNombre() {
		return nombreGasto;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public Double getCantidad() {
		return cantidad;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setNombre(String nuevoNombre) {
		this.nombreGasto = nuevoNombre;
	}
	
	public void setFecha(LocalDate nuevaFecha) {
		this.fecha = nuevaFecha;
	}
	
	public void setCantidad(Double nuevaCantidad) {
		this.cantidad = nuevaCantidad;
	}
	
	public void setCategoria(Categoria nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}
}
