package umu.tds.vista;

import umu.tds.modelo.Gasto;
import umu.tds.repository.RepositorioGasto;
import umu.tds.repository.impl.RepositorioGastoJSON;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

public class ControladorEliminarGasto {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button cancelarOperacion;
    @FXML private Button eliminarGasto;
    @FXML private ListView<Gasto> listaGastos;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;

    public void setControladorPrincipal(ControladorVentanaPrincipal controlador) {
        this.controladorVentanaPrincipal = controlador;
    }
    
    private final RepositorioGasto repositorio = new RepositorioGastoJSON().getInstance();
    
    @FXML
    void eliminarGasto(ActionEvent event) {
        Gasto gastoSeleccionado = listaGastos.getSelectionModel().getSelectedItem();
        if (gastoSeleccionado == null) {
        	repositorio.delete(gastoSeleccionado);		//persistencia
        	controladorVentanaPrincipal.mostrarEnTerminal("ERROR: Selecciona un gasto para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(AlertType.CONFIRMATION, "¿Está segura de eliminar \"" + gastoSeleccionado + "\"?", ButtonType.YES, ButtonType.NO);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                listaGastos.getItems().remove(gastoSeleccionado);
                controladorVentanaPrincipal.mostrarEnTerminal("Gasto eliminado: " + gastoSeleccionado);
            } else {
            	controladorVentanaPrincipal.mostrarEnTerminal("Eliminación cancelada.");
            }
        });
    }
    
    @FXML
    void cancelarOperacion(ActionEvent event) {
    	listaGastos.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        assert cancelarOperacion != null : "fx:id=\"cancelarOperacion\" was not injected: check your FXML file 'VentanaEliminarGasto.fxml'.";
        assert eliminarGasto != null : "fx:id=\"eliminarGasto\" was not injected: check your FXML file 'VentanaEliminarGasto.fxml'.";
        assert listaGastos != null : "fx:id=\"listaGastos\" was not injected: check your FXML file 'VentanaEliminarGasto.fxml'.";
        
        listaGastos.setItems((ObservableList<Gasto>) repositorio.findAll()); 	//se vincula directamente
    }
}