package umu.tds.modelo;

import java.time.LocalDateTime;

/*
- id : Integer
- categoria : Categoria
- fecha : LocalDateTime
- saldo : double
*/

public class Alerta {
	//Atributos
	private Categoria categoria;
	private LocalDateTime fecha;
	private double cantidad;
	private int id;
	
	//Constructor
	public Alerta(LocalDateTime fecha, double cantidad, int id) {
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.id = id;
	}
	public Alerta(LocalDateTime fecha, double cantidad, int id, Categoria categoria) {
		this(fecha, cantidad, id);
		this.categoria = categoria;
	}
	
	//Métodos de consulta
	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public double getCantidad() {
		return cantidad;
	}

	public int getId() {
		return id;
	}
}