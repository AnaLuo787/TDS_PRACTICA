package umu.tds.modelo;

import java.util.HashMap;
import java.util.Map;

public class CuentaCompartida {
/*final listaPersonas : Map<String, Double> () //nombre, saldo
	- final porcentajes:  Map<String, Double> ()  //nombre y porcentaje
	-
	+ distribuir()
	+ dineroPendientePorPersona()
	+ asignarPorcentaje() //por defecto o personalizado
*/
	//ATRIBUTOS
	private final Map<String, Double> listaPersonas; //nombre y saldo
	private final Map<String, Double> porcentajes;     // nombre y porcentaje (0.0–1.0)
	//CONSTRUCTOS
	
	public CuentaCompartida() {
		//se inializan a null
		listaPersonas = new HashMap<String, Double> ();
		porcentajes = new HashMap<String, Double> ();
	}
	
	//añadimos a la lista una lista de personas con sus correspondientes saldos y nombres
	public void addPersonasCuentaCompartida(String nombre, double saldo) {
		listaPersonas.put(nombre, saldo);
	}

	// Asignar porcentajes personalizados
    public void asignarPorcentaje(Map<String, Double> porcentajes) {
        double suma = porcentajes.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(suma - 1.0) > 0.0001) {
            throw new IllegalArgumentException("La suma de porcentajes debe ser 1.0");
        }
        this.porcentajes.clear();
        this.porcentajes.putAll(porcentajes);
    }
    
    // Asignar porcentajes iguales por defecto
    public void asignarPorcentajePorDefecto() {
        int n = listaPersonas.size();
        double porcentaje = 1.0 / n;
        porcentajes.clear();
        for (String nombre : listaPersonas.keySet()) {
            porcentajes.put(nombre, porcentaje);
        }
    }
    
 // Calcular dinero pendiente por persona
    private Map<String, Double> dineroPendientePorPersona(double gastoTotal) {
        // Si no hay porcentajes definidos, se asignan por defecto
        if (porcentajes.isEmpty()) {
            asignarPorcentajePorDefecto();
        }   
    Map<String, Double> pendientes = new HashMap<>();
    for (String nombre : listaPersonas.keySet()) {
        double porcentaje = porcentajes.get(nombre); //se coge el porcentaje de cada persona
        double cuota = gastoTotal * porcentaje; //se ve dependiendo del porcentaje lo que tiene q aportar
        double saldoActual = listaPersonas.get(nombre); 
        double pendiente = cuota - saldoActual; //lo que debe pagar - saldo que tiene

        pendiente = Math.round(pendiente * 100.0) / 100.0; // Redondear a 2 decimales
        pendientes.put(nombre, pendiente);
    	}
    return pendientes;
    }
    
    public String explicacionDineroPendiente(double gasto) {
    	Map<String, Double> solucion = dineroPendientePorPersona(gasto);
    	StringBuilder sb = new StringBuilder();
    	
    	 for (Map.Entry<String, Double> entry : solucion.entrySet()) {
    	        String nombre = entry.getKey();
    	        double valor = entry.getValue();

    	        if (valor == 0.0) {
    	            sb.append(nombre).append(" ha pagado exactamente lo que le corresponde.\n");
    	        } else if (valor > 0) {
    	            sb.append(nombre).append(" todavía debe ").append(valor).append(" euros.\n");
    	        } else {
    	            sb.append(nombre).append(" ha pagado lo que le correspondía y le han sobrado ").append(Math.abs(valor)).append(" euros.\n");
    	        }
    	    }
    	 return sb.toString();
    }

	@Override
	public String toString() {
		return "CuentaCompartida [listaPersonas=" + listaPersonas + ", porcentajes=" + porcentajes + "]";
	}
    
    
}
