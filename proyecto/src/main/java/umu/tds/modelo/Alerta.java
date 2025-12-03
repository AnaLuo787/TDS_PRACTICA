package umu.tds.modelo;

import java.time.LocalDateTime;

/*
- id : Integer
- categoria : Categoria
- fecha : LocalDateTime
- frecuencia : enum
- cantidad : double
*/

public class Alerta {
	//Atributos
	public enum Frecuencia {
        SEMANAL, MENSUAL, ANUAL
	};
	private Frecuencia frecuencia;
	private Categoria categoria;
	private LocalDateTime fecha;
	private double cantidad;
	
	//Constructor
	public Alerta(Frecuencia frecuencia, Categoria categoria, double cantidad) {
		this.frecuencia = frecuencia;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}
	
	public Alerta(Frecuencia frecuencia, Categoria categoria, LocalDateTime fecha, double cantidad) {
		this(frecuencia, categoria, cantidad);
		this.fecha = fecha;		//¿guardamos la fecha en la que saltó? ¿O mejor en Notificación?
	}
	
	//Métodos de consulta
	public Frecuencia getFrecuencia() {
		return frecuencia;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public double getCantidad() {
		return cantidad;
	}
	
	@Override
    public String toString() {
        return "Alerta → Frecuencia: " + frecuencia +
               ", Categoría: " + categoria.getNombre() +
               ", Límite: " + cantidad + " €" +
               ", Fecha: " + fecha;
	}
}