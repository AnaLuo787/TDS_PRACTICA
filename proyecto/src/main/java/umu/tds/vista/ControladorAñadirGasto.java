package umu.tds.vista;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class ControladorAñadirGasto {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button add;
    @FXML private Button cancel;
    @FXML private TextField cantidadGasto;
    @FXML private ComboBox<String> categoriasCreadas;
    @FXML private DatePicker fechaGasto;
    @FXML private Button newCategoria;
    @FXML private TextField nombreGasto;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    
    public void setControladorPrincipal(ControladorVentanaPrincipal controlador) {
        this.controladorVentanaPrincipal = controlador;
    }

    @FXML
    void addGasto(ActionEvent event) {
    	String nombre = nombreGasto.getText();
        String categoria = categoriasCreadas.getValue();
        String cantidadStr = cantidadGasto.getText();
        LocalDate fecha = fechaGasto.getValue();
        //Comprobamos que se han rellenado todos los campos correctamente
        if (nombre == null || nombre.isEmpty()) {
        	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: añade nombre al gasto");
        	return;
        }
        if (categoria == null) {
        	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: selecciona una categoría");
        	return;
        }
        if (fecha == null) {
        	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: selecciona una fecha");
        	return;
        }
        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadStr);
            if (cantidad <= 0) {
            	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: la cantidad debe ser positiva");
            	return;
            }
        } catch (NumberFormatException e) {
        	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: la cantidad es inválida (ej: 23.45)");
            return;
        }

        ///Aquí debe ir la persistencia
        controladorVentanaPrincipal.mostrarEnTerminal("Gasto añadido: " + nombre + " en categoría " + categoria + " por " + cantidad + " € el " + fecha);
        limpiarFormulario();
    }

    @FXML
    void cancelarAddGasto(ActionEvent event) {
    	limpiarFormulario();
    }
    
    private void limpiarFormulario() {
    	nombreGasto.clear();
    	categoriasCreadas.getSelectionModel().clearSelection();
    	cantidadGasto.clear();
    	fechaGasto.setValue(LocalDate.now());
    }
    
    @FXML
    void crearNuevaCategoria() {
    	TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nueva categoría");
        dialog.setHeaderText(null);
        dialog.setContentText("Nombre:");
        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.isEmpty()) {
            	categoriasCreadas.getItems().add(nombre);
            	categoriasCreadas.getSelectionModel().select(nombre);
            	controladorVentanaPrincipal.mostrarEnTerminal("Categoría añadida: " + nombre);
            } else {
            	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: La categoría no puede estar vacía");
            }
        });
    }

    @FXML
    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert cantidadGasto != null : "fx:id=\"cantidadGasto\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert categoriasCreadas != null : "fx:id=\"categoriasCreadas\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert fechaGasto != null : "fx:id=\"fechaGasto\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert newCategoria != null : "fx:id=\"newCategoria\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";
        assert nombreGasto != null : "fx:id=\"nombreGasto\" was not injected: check your FXML file 'VentanaAñadirGasto.fxml'.";

        //Categorías predefinidas
        categoriasCreadas.getItems().addAll("Alimentación", "Transporte", "Entretenimiento");
        //Determinamos la fecha a la actual, por si el gasto se ha producido hoy
        fechaGasto.setValue(LocalDate.now());
    }
}
