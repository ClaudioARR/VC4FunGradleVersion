package window;

import acciones_entidades.Actions_Materia;
import acciones_entidades.Actions_Tema;
import entidades.Materia;
import entidades.Tema;
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

class EliminarMateria {

    static private Stage window = new Stage();
    static private TableView<Materia> table_materia;


    static private StackPane  CENTERSIDE(){
        // CENTERSIDE -----------------------------

        table_materia = new TableView<>();

        TableColumn<Materia, Integer> colID = new TableColumn<>("Materia ID");
        TableColumn<Materia, String> colNombre = new TableColumn<>("Nombre Materia");

        table_materia.getColumns().addAll(colID, colNombre);

        window.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                Actions_Materia.getMaterias();
                colID.setCellValueFactory(new PropertyValueFactory<>("IDMateria"));
                colID.setPrefWidth(320);

                colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre_Materia"));
                colNombre.setPrefWidth(320);

                table_materia.getItems().addAll(Materia.Materias);

            }catch (SQLException ex){
                Window_Dialog.display("Error al cargar los datos", ex.getMessage());
            }

        });

        StackPane centerside = new StackPane();
        centerside.setPadding(new Insets(5));
        centerside.setAlignment(Pos.CENTER);
        centerside.getChildren().add(table_materia);

        return centerside;

    }

    static private StackPane BOTTOMSIDE(){
        // BOTTOMSIDE -----------------------------

        Button eliminarM = new Button("Eliminar Materia Seleccionada");
        eliminarM.setPadding(new Insets(10));
        eliminarM.setFont(new Font(15));

        eliminarM.setOnAction(event -> {
            try
            {
                Materia materia  = table_materia.getSelectionModel().getSelectedItem();
                Actions_Materia.deleteMateria(materia);

                Window_Dialog.display("Elminar Materia", "Materia borrada exitosamente");
            }catch (SQLException ex){
                Window_Dialog.display("Elminar Materia", "Error al borrar materia " + ex.getMessage());
            }
        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(eliminarM);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    static void display(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Eliminar Materia");

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
