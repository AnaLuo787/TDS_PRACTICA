package umu.tds.vista;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class ControladorAlertas {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton categoriaBottom;


    @FXML
    private MenuButton frecuenciaBotton;

    @FXML
    private CheckMenuItem mensual;

    @FXML
    private CheckMenuItem semanal;

    @FXML
    private CheckMenuItem anual;
    
    @FXML
    private TextField limiteTextField;
    
    @FXML
    private Button añadirButton;
    
    @FXML
    private Button cancelarButton;
    
    @FXML
    private ListView<CheckBox> reglasListView;
    
    @FXML
    private Button borrarButton;
    
    @FXML
    private Button seleccionarButton;
    
    @FXML
    private CheckBox seleccionarTodoCheckBox;

    @FXML
    void initialize() {
        assert anual != null : "fx:id=\"anual\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert categoriaBottom != null : "fx:id=\"categoriaBottom\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert frecuenciaBotton != null : "fx:id=\"frecuenciaBotton\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert mensual != null : "fx:id=\"mensual\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert semanal != null : "fx:id=\"semanal\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert reglasListView != null : "fx:id=\"reglasListView\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert borrarButton != null : "fx:id=\"borrarButton\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        
        
        // Texto del Button de Frecuencia
        semanal.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                frecuenciaBotton.setText("semanal");
                mensual.setSelected(false);
                anual.setSelected(false);
            }
        });

        mensual.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                frecuenciaBotton.setText("mensual");
                semanal.setSelected(false);
                anual.setSelected(false);
            }
        });

        anual.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                frecuenciaBotton.setText("anual");
                semanal.setSelected(false);
                mensual.setSelected(false);
            }
        });
        
        // Texto del Button de Categoría
        setupCategoriaMenu();
        
        seleccionarTodoCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            for (CheckBox regla : reglasListView.getItems()) {
                regla.setSelected(newVal);
            }
        });

        
    }
    
    private void updateCategoriaText() {

        List<String> seleccionados = new ArrayList<>();

        for (Object item : categoriaBottom.getItems()) {
            if (item instanceof CheckMenuItem) {
                CheckMenuItem checkItem = (CheckMenuItem) item;
                if (checkItem.isSelected()) {
                    seleccionados.add(checkItem.getText());
                }
            }
        }

        if (seleccionados.isEmpty()) {
            categoriaBottom.setText("");
        } else {
            categoriaBottom.setText(String.join(", ", seleccionados));
        }
    }

    private void setupCategoriaMenu() {
    	for (Object item : categoriaBottom.getItems()) {
    	    if (item instanceof CheckMenuItem) {
    	        CheckMenuItem checkItem = (CheckMenuItem) item;

                checkItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    updateCategoriaText();
                });
            }
        }

        updateCategoriaText();

    }
    
    
    // Añadir regla a la lista
    @FXML
    private void onAñadirRegla() {
        String regla = frecuenciaBotton.getText() + ", " + categoriaBottom.getText() + ", " + limiteTextField.getText() + "€";
        if (!frecuenciaBotton.getText().isEmpty() && !categoriaBottom.getText().isEmpty() && !limiteTextField.getText().isEmpty()) {
			reglasListView.getItems().add(new CheckBox(regla));
	        onCancelarRegla(); // Limpiar campos después de añadir

        }
    }
    
    // Cancelar regla y limpiar campos
    @FXML
    private void onCancelarRegla() {
    	semanal.setSelected(false);
		mensual.setSelected(false);
		anual.setSelected(false);
		for (Object item : categoriaBottom.getItems()) {
			if (item instanceof CheckMenuItem) {
				((CheckMenuItem) item).setSelected(false);
			}
		}
		limiteTextField.clear();
		// Actualizar textos y reglas
		frecuenciaBotton.setText("");
		updateCategoriaText();
    }
    
    // Borrar reglas seleccionadas
    @FXML
    private void onBorrarReglas() {
    			reglasListView.getItems().removeIf(CheckBox::isSelected);
    }
    
    


}

