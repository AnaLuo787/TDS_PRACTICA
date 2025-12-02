package umu.tds.vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorFiltrarGastos {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane categoriaScrollPane;

    @FXML
    private ToggleButton applicarToggleButton;

    @FXML
    private VBox botones;

    @FXML
    private CheckBox categoriaCheckBox;
    
    @FXML
    private VBox categoriaVBox;

    @FXML
    private CheckBox fechaCeckBox;
    
    @FXML
    private GridPane fechaGridPane;

    @FXML
    private Text filtrar;
    
    @FXML
    private DatePicker desdeDatePicker;
    
    @FXML
    private DatePicker hastaDatePicker;

    @FXML
    void initialize() {
        assert categoriaScrollPane != null : "fx:id=\"CategoriaScrollPane\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert applicarToggleButton != null : "fx:id=\"applicarToggleButton\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert botones != null : "fx:id=\"botones\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert categoriaCheckBox != null : "fx:id=\"categoriaCheckBox\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert fechaCeckBox != null : "fx:id=\"fechaCeckBox\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert filtrar != null : "fx:id=\"filtrar\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert fechaGridPane != null : "fx:id=\"fechaGridPane\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        
        // Inicialmente, el ScrollPane de categorías está oculto
        categoriaScrollPane.setVisible(false);
        categoriaScrollPane.setManaged(false);

        	// Añadir un listener al CheckBox para mostrar/ocultar el ScrollPane
        categoriaCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            categoriaScrollPane.setVisible(newVal);
            categoriaScrollPane.setManaged(newVal);
        });
        
        // Inicialmente, el GridPane de fecha está oculto
        fechaGridPane.setVisible(false);
        fechaGridPane.setManaged(false);
        
        // Añadir un listener al CheckBox para mostrar/ocultar el GridPane
        fechaCeckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
			fechaGridPane.setVisible(newVal);
			fechaGridPane.setManaged(newVal);
		});
        
        // ToggleButton soll ON gehen, wenn ein Filter verändert wird
        categoriaCheckBox.selectedProperty().addListener((o, ov, nv) -> applicarToggleButton.setSelected(false));
        fechaCeckBox.selectedProperty().addListener((o, ov, nv) -> applicarToggleButton.setSelected(false));
    }

}

