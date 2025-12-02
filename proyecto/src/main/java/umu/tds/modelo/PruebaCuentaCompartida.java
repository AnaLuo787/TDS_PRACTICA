package umu.tds.modelo;

import java.util.HashMap;
import java.util.Map;

//ESTO SE QUITA ANTES DE ENTREGARLO EHH
//PERO PARA VER QUE HACE LO QUE QUEREMOS
//TODO
public class PruebaCuentaCompartida {
	public static void main(String[] args) {
	    CuentaCompartida cuenta = new CuentaCompartida();
	    cuenta.addPersonasCuentaCompartida("Ana", 20);
	    cuenta.addPersonasCuentaCompartida("Luis", 5);
	    //

	    //gastoTotal/numPersonas
	    System.out.println("PORCENTAJES POR DEFECTO");
	    //System.out.println(cuenta.dineroPendientePorPersona(20));
	    System.out.println(cuenta.explicacionDineroPendiente(20));
	    
	    /*
		   * PORCENTAJES POR DEFECTO
			Lo que debe cada persona
			{Ana=0.0, Luis=5.0}
			Por ejemplo Ana ha pagado justo pero
			Luis le han faltado 5 euros 
			*/

	    System.out.println("ASIGNANDO PORCENTAJES");
	    Map<String, Double> porcentajes = new HashMap<>();
	    porcentajes.put("Ana", 0.5);   // 50%
	    porcentajes.put("Luis", 0.3);  // 30%
	    cuenta.addPersonasCuentaCompartida("Marta", 5);
	    porcentajes.put("Marta", 0.2); // 20%
	    cuenta.asignarPorcentaje(porcentajes);

	    //System.out.println(cuenta.dineroPendientePorPersona(20));
	  
	    System.out.println(cuenta.explicacionDineroPendiente(20));

	    }

}
