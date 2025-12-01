package umu.tds.vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class ControladorEliminarGasto {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelarOperacion;

    @FXML
    private Button eliminarGasto;

    @FXML
    private TableView<?> tablaGastosEliminar;

    @FXML
    void cancelarOperacion(ActionEvent event) {

    }

    @FXML
    void eliminarGasto(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cancelarOperacion != null : "fx:id=\"cancelarOperacion\" was not injected: check your FXML file 'ControladorEliminarGasto.fxml'.";
        assert eliminarGasto != null : "fx:id=\"eliminarGasto\" was not injected: check your FXML file 'ControladorEliminarGasto.fxml'.";
        assert tablaGastosEliminar != null : "fx:id=\"tablaGastosEliminar\" was not injected: check your FXML file 'ControladorEliminarGasto.fxml'.";

    }

}
