package umu.tds;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

///Sólo hay una 'Stage' (ventana principal)
///Y la 'Scene' (escena) define el aspecto de las diferentes ventanas

public class AppGastos extends Application {
	@Override
	public void start(Stage ventanaPrincipal) throws Exception {
		/*Label etiqueta = new Label("Prueba de ventana principal");
		FlowPane contenedor = new FlowPane();
        contenedor.getChildren().add(etiqueta);*/
		
		Parent contenedor = FXMLLoader.load(getClass().getResource("VentanaPrincipalGastos.fxml"));
        Scene escena = new Scene(contenedor, 600, 300);	//tamaño de la mini ventana al ejecutar
        
        ventanaPrincipal.setTitle("Aplicación: Gestión de Gastos");
        ventanaPrincipal.setScene(escena);
        ventanaPrincipal.show();
	}
	
	public static void main( String[] args ) {
        launch(args);
    }
}