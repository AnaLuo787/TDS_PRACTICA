package umu.tds.vista;

import umu.tds.modelo.Categoria;
import umu.tds.modelo.Gasto;
import umu.tds.repository.Repositorio;
import umu.tds.repository.impl.RepositorioCategoriaJSON;
import umu.tds.repository.impl.RepositorioGastoJSON;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorFiltrarGastos {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ScrollPane categoriaScrollPane;
    @FXML private ToggleButton filtrarGastos;
    @FXML private VBox botones;
    @FXML private CheckBox categoriaCheckBox;
    @FXML private VBox categoriaVBox;
    @FXML private CheckBox fechaCheckBox;
    @FXML private GridPane fechaGridPane;
    @FXML private Text filtrar;
    @FXML private DatePicker desdeDatePicker;
    @FXML private DatePicker hastaDatePicker;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;

    public void setControladorPrincipal(ControladorVentanaPrincipal controlador) {
        this.controladorVentanaPrincipal = controlador;
    }
    
    private final Repositorio<Categoria> repositorioCategorias = RepositorioCategoriaJSON.getInstance();
    
    @FXML
    private void aplicarFiltradoDeGastos(ActionEvent event) {
    	
    }
    
    @FXML
    void initialize() {
        assert categoriaScrollPane != null : "fx:id=\"CategoriaScrollPane\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert filtrarGastos != null : "fx:id=\"applicarToggleButton\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert botones != null : "fx:id=\"botones\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert categoriaCheckBox != null : "fx:id=\"categoriaCheckBox\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert fechaCheckBox != null : "fx:id=\"fechaCeckBox\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert filtrar != null : "fx:id=\"filtrar\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        assert fechaGridPane != null : "fx:id=\"fechaGridPane\" was not injected: check your FXML file 'VentanaFiltrarGastos.fxml'.";
        
        //Inicialmente, el ScrollPane de categorías y el GridPane de fecha están ocultos
        categoriaScrollPane.setVisible(false);
        categoriaScrollPane.setManaged(false);
        fechaGridPane.setVisible(false);
        fechaGridPane.setManaged(false);

        //Añadir un listener al CheckBox para mostrar/ocultar el ScrollPane / GridPane
        categoriaCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            categoriaScrollPane.setVisible(newVal);
            categoriaScrollPane.setManaged(newVal);
        });
        fechaCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
			fechaGridPane.setVisible(newVal);
			fechaGridPane.setManaged(newVal);
		});
        
        //El botón debe reflejar visualmente que hay un filtro aplicado y cuál
        categoriaCheckBox.selectedProperty().addListener((o, ov, nv) -> filtrarGastos.setSelected(false));
        fechaCheckBox.selectedProperty().addListener((o, ov, nv) -> filtrarGastos.setSelected(false));
        
        //Si se activa la opción de Categoría, se mostrarán las categorías registradas en el sistema (predefinidas + creadas)
        String[] predefinidas = {"Alimentación", "Transporte", "Entretenimiento"};
        for (String nombre : predefinidas) {
            CheckBox check = new CheckBox(nombre);
            check.selectedProperty().addListener((obs, oldVal, newVal) -> filtrarGastos.setSelected(false));
            categoriaVBox.getChildren().add(check);
        }
        for (Categoria categoria : repositorioCategorias.findAll()) {
            CheckBox check = new CheckBox(categoria.getNombre());
            check.selectedProperty().addListener((obs, oldVal, newVal) -> filtrarGastos.setSelected(false));
            categoriaVBox.getChildren().add(check);
        }
    }
}

