package umu.tds.vista;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import umu.tds.modelo.Categoria;
import umu.tds.repository.Repositorio;
import umu.tds.repository.impl.RepositorioCategoriaJSON;

public class ControladorAlertas {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private MenuButton categoriaBottom;
    @FXML private MenuButton frecuenciaBotton;
    @FXML private CheckMenuItem mensual;
    @FXML private CheckMenuItem semanal;
    @FXML private CheckMenuItem anual;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;

    public void setControladorPrincipal(ControladorVentanaPrincipal controlador) {
        this.controladorVentanaPrincipal = controlador;
    }
    
    private final Repositorio<Categoria> repositorioCategorias = RepositorioCategoriaJSON.getInstance();
    
    //Refleja las opciones marcadas por el usuario
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
            categoriaBottom.setText("Selecciona una o varias categorías.");
        } else {
            categoriaBottom.setText(String.join(", ", seleccionados));
        }
    }

    @FXML
    void initialize() {
        assert anual != null : "fx:id=\"anual\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert categoriaBottom != null : "fx:id=\"categoriaBottom\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert frecuenciaBotton != null : "fx:id=\"frecuenciaBotton\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert mensual != null : "fx:id=\"mensual\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        assert semanal != null : "fx:id=\"semanal\" was not injected: check your FXML file 'VentanaAlertas.fxml'.";
        
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
        
        String[] predefinidas = {"Alimentación", "Transporte", "Entretenimiento"};
        for (String nombre : predefinidas) {
            CheckMenuItem item = new CheckMenuItem(nombre);
            item.selectedProperty().addListener((obs, oldVal, newVal) -> updateCategoriaText());
            categoriaBottom.getItems().add(item);
        }
        for (Categoria categoria : repositorioCategorias.findAll()) {
            CheckMenuItem item = new CheckMenuItem(categoria.getNombre());
            item.selectedProperty().addListener((obs, oldVal, newVal) -> updateCategoriaText());
            categoriaBottom.getItems().add(item);
        }
        // Inicializar el texto del botón
        updateCategoriaText();
    }
}