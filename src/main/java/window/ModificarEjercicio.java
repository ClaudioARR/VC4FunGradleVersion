package window;

import acciones_entidades.Actions_Ejercicios;
import entidades.Ejercicios;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class ModificarEjercicio extends Stage {

    private Stage window = new Stage();
    private TableView<Ejercicios> table_ejercicio;


    private StackPane  CENTERSIDE(){
        // CENTERSIDE -----------------------------

        table_ejercicio = new TableView<>();

        TableColumn<Ejercicios, Integer> colID = new TableColumn<>("Ejercicio ID");
        TableColumn<Ejercicios, String> colNombre = new TableColumn<>("Ejercicio");
        TableColumn<Ejercicios, Integer> colTema = new TableColumn<>("Ejercicio Tema");
        TableColumn<Ejercicios, String> colRespuesta = new TableColumn<>("Respuesta Ejercicio");

        table_ejercicio.getColumns().addAll(colID, colNombre,colTema, colRespuesta);

        window.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                Actions_Ejercicios.getEjercicios();
                colID.setCellValueFactory(new PropertyValueFactory<>("IDEjercicio"));
                colID.setPrefWidth(70);

                colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre_Ejercicio"));
                colNombre.setPrefWidth(200);

                colTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));
                colTema.setPrefWidth(90);

                colRespuesta.setCellValueFactory(new PropertyValueFactory<>("Propiedades_Ejercicio"));
                colRespuesta.setPrefWidth(280);

                table_ejercicio.getItems().addAll(Ejercicios.EjerciciosArray);

            }catch (SQLException ex){
                Window_Dialog.display("Error al cargar los datos", ex.getMessage());
            }

        });

        StackPane centerside = new StackPane();
        centerside.setPadding(new Insets(5));
        centerside.setAlignment(Pos.CENTER);
        centerside.getChildren().add(table_ejercicio);

        return centerside;

    }

    private StackPane BOTTOMSIDE(){
        // BOTTOMSIDE -----------------------------

        Button modificarE = new Button("Modificar Ejercicio Seleccionado");
        modificarE.setPadding(new Insets(10));
        modificarE.setFont(new Font(15));

        modificarE.setOnAction(event -> {

            Ejercicios ejercicio  = table_ejercicio.getSelectionModel().getSelectedItem();
            ModificarEjercicioWindow modificarEjercicioWindow = new ModificarEjercicioWindow(ejercicio, window);
            modificarEjercicioWindow.sizeToScene();
            modificarEjercicioWindow.show();
        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(modificarE);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    ModificarEjercicio(Stage owner){
        super();
        window.initOwner(owner);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modificar Ejercicio");

        // ROOT -----------------------------

        BorderPane root = new BorderPane();
        root.setCenter(CENTERSIDE());
        root.setBottom(BOTTOMSIDE());


        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("style/Style.css");

        window.setScene(scene);
        window.show();
    }




}
