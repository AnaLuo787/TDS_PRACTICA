package umu.tds.vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorFiltrarGastos {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane CategoriaScrollPane;

    @FXML
    private ToggleButton applicarToggleButton;

    @FXML
    private VBox botones;

    @FXML
    private CheckBox categoriaCheckBox;

    @FXML
    private CheckBox fechaCeckBox;

    @FXML
    private Text filtrar;

    @FXML
    private Button volver;

    @FXML
    void initialize() {
        assert CategoriaScrollPane != null : "fx:id=\"CategoriaScrollPane\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert applicarToggleButton != null : "fx:id=\"applicarToggleButton\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert botones != null : "fx:id=\"botones\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert categoriaCheckBox != null : "fx:id=\"categoriaCheckBox\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert fechaCeckBox != null : "fx:id=\"fechaCeckBox\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert filtrar != null : "fx:id=\"filtrar\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'VentanaFiltrar.fxml'.";

    }

}

