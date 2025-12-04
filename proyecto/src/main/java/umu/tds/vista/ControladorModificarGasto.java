package umu.tds.vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import umu.tds.modelo.Categoria;
import umu.tds.modelo.Gasto;
import umu.tds.repository.impl.RepositorioGastoJSON;
import javafx.scene.control.cell.PropertyValueFactory; // Necesario para TableView

public class ControladorModificarGasto {
	
	// Referencia al controlador principal (para la terminal)
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
    
    // Referencia al repositorio
	private final RepositorioGastoJSON repositorio = RepositorioGastoJSON.getInstance();

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    
    // ATENCIÓN: listaGastos no está en tu FXML, pero la dejamos si se usa internamente
    // Si no se usa para nada más, considera eliminarla para evitar confusiones.
    @FXML private ListView<Gasto> listaGastos; 

    @FXML private Button BotonCancelar;
    @FXML private Button BotonModificar;
    // Elementos del TableView de tu FXML
    @FXML private TableView<Gasto> tablaModificarGastos;
    @FXML private TableColumn<Gasto, String> TablaNombre;
    @FXML private TableColumn<Gasto, String> TablaCantidad;
    @FXML private TableColumn<Gasto, String> TablaCategoria;
    @FXML private TableColumn<Gasto, String> TablaFecha;

    private Categoria categoria;
    // Debes añadir las otras columnas aquí si las usas en el controlador:
    // @FXML private TableColumn<Gasto, String> TablaNombre1; // Categoría
    // @FXML private TableColumn<Gasto, String> TablaNombre11; // Fecha

    @FXML private TextArea terminal;
    
    // Referencia a ControladorModificarGasto_2 (Mantenida por si acaso, aunque opcional)
	private ControladorModificarGasto_2 controladorModif_2;
    
    // --- SETTERS ---

    public void setControladorPrincipal(ControladorVentanaPrincipal controlador) {
    	this.controladorVentanaPrincipal = controlador;
    }
    
    public void setControladorModif_2(ControladorModificarGasto_2 controladorMod) {
    	this.controladorModif_2 = controladorMod;
    }
    
    // --- TERMINAL ---

    public void mostrarEnTerminal(String texto) {
        if (controladorVentanaPrincipal != null) {
            controladorVentanaPrincipal.mostrarEnTerminal(texto);
        } else if (terminal != null) {
            terminal.appendText(texto + "\n");
        }
    }
    
    // --- FLUJO DE PESTAÑAS (DEJADO COMO DUMMY) ---
    // (Este método se mantiene, pero no se usa en el flujo de ventanas modales)
    private void abrirPestaña(String rutaFXML) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent contenido = loader.load();

            Object controlador = loader.getController();
            if (controlador instanceof ControladorModificarGasto_2) {
                ((ControladorModificarGasto_2) controlador).setControladorModif(this);
            }
    	}
    	catch (IOException e) {
            e.printStackTrace();
            mostrarEnTerminal("Error al cargar la ventana: " + rutaFXML);
        }
    }
    
    // --- EVENTOS ---

    @FXML
    void cancelarOperacion(ActionEvent event) {
        // Asumiendo que quieres limpiar la selección de la tabla, no de la lista
        tablaModificarGastos.getSelectionModel().clearSelection();
        mostrarEnTerminal("Operación de selección cancelada.");
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        // Si este botón está en la vista, puede usarse para limpiar la selección o cerrar la pestaña
        cancelarOperacion(event);
    }
    
    @FXML
    void modificarGasto(ActionEvent event) {
        // CRÍTICO: Usamos el TableView para obtener la selección.
        Gasto gastoSeleccionado = tablaModificarGastos.getSelectionModel().getSelectedItem();

        if (gastoSeleccionado != null) {
            try {
                Stage stageActual = (Stage) BotonModificar.getScene().getWindow();

                // 3. Cargar la vista de modificación
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/umu/tds/VentanaModificarGasto_2.fxml"));
                Parent root = loader.load(); // Usa la ruta completa para evitar errores de FXML

                ControladorModificarGasto_2 controladorModificar = loader.getController();
                
                // Pasar referencias y datos
                controladorModificar.initData(gastoSeleccionado);
                //controladorModificar.setControladorModif(controladorModif_2); // Para la terminal

                // Configurar y mostrar la nueva ventana MODAL
                Stage stageModificar = new Stage();
                stageModificar.setTitle("Modificar Gasto: " + gastoSeleccionado.getNombre());
                stageModificar.setScene(new Scene(root));
                
                stageModificar.initModality(Modality.APPLICATION_MODAL);
                stageModificar.initOwner(stageActual); 

                mostrarEnTerminal("Abriendo ventana de edición...");
                stageModificar.showAndWait(); // Ejecución pausada aquí.
                
                // 4. Recargar la tabla al volver (tras guardar o cancelar)
                recargarTabla();
                mostrarEnTerminal("Operación de edición finalizada. Lista de gastos actualizada.");
                
            } catch (IOException e) {
                e.printStackTrace();
                mostrarEnTerminal("ERROR: Fallo al cargar la ventana de modificación. " + e.getMessage());
            }
        } else {
            mostrarEnTerminal("ADVERTENCIA: Selecciona un gasto para modificar.");
        }
    }
    
    public void guardarGastoModificado(Gasto gastoModificado) {
        
        // 1. Persistir el gasto modificado en el repositorio
        // NOTA: Asumimos que el repositorio tiene un método 'update' o 'save'
        repositorio.save(gastoModificado); 
        
        // 2. Recargar la tabla para mostrar los cambios
        recargarTabla();
        
        mostrarEnTerminal("Gasto '" + gastoModificado.getNombre() + "' modificado y guardado.");
    }
    /** Recarga los datos en el TableView. */
    public void recargarTabla() {
        // Asegúrate de usar el método setItems del TableView
        tablaModificarGastos.setItems((ObservableList<Gasto>) repositorio.findAll());
    }

    // --- INICIALIZACIÓN ---

    @FXML
    void initialize() {
        assert BotonCancelar != null : "fx:id=\"BotonCancelar\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert BotonModificar != null : "fx:id=\"BotonModificar\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert TablaCantidad != null : "fx:id=\"TablaCantidad\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert TablaNombre != null : "fx:id=\"TablaNombre\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert tablaModificarGastos != null : "fx:id=\"tablaModificarGastos\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert TablaCategoria != null : "fx:id=\"TablaCategoria\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";
        assert TablaFecha != null : "fx:id=\"TablaFecha\" was not injected: check your FXML file 'VentanaModificarGasto.fxml'.";

        // listaGastos no es requerido si se usa TableView, pero si existe en el FXML, el assert es válido.

        // Configurar cómo se llenan las columnas del TableView
        // Debes asegurar que "nombre" y "cantidad" coincidan con los nombres de las propiedades en la clase Gasto
        TablaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TablaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TablaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria.nombre")); 
        TablaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        //listaGastos.setItems((ObservableList<Gasto>) repositorio.findAll()); 	//se vincula directamente ESTA LINEA DA ERROR
        // Carga inicial de datos: usando la tabla, no la lista
        recargarTabla(); 	
    }

}