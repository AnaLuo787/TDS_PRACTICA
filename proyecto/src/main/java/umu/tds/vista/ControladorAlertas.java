package umu.tds.vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;

public class ControladorAlertas {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckMenuItem anual;

    @FXML
    private VBox botones;

    @FXML
    private MenuButton categoriaBottom;

    @FXML
    private MenuButton frecuenciaBotton;

    @FXML
    private CheckMenuItem mensual;

    @FXML
    private CheckMenuItem semanal;

    @FXML
    private Button volver;

    @FXML
    void initialize() {
        assert anual != null : "fx:id=\"anual\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert botones != null : "fx:id=\"botones\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert categoriaBottom != null : "fx:id=\"categoriaBottom\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert frecuenciaBotton != null : "fx:id=\"frecuenciaBotton\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert mensual != null : "fx:id=\"mensual\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert semanal != null : "fx:id=\"semanal\" was not injected: check your FXML file 'Descargas.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'Descargas.fxml'.";

    }

}
