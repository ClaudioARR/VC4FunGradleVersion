package window;

import acciones_entidades.Actions_Tema;
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
import utilities.Utilities;
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class EliminarTema extends Stage {
    private TableView<Tema> table_tema;


    private StackPane  CENTERSIDE(){
        // CENTERSIDE -----------------------------

        table_tema = new TableView<>();

        TableColumn<Tema, Integer> colID = new TableColumn<>("Tema ID");
        TableColumn<Tema, String> colNombre = new TableColumn<>("Nombre Tema");
        TableColumn<Tema, Integer> colMateria = new TableColumn<>("Materia Tema");
        TableColumn<Tema, String> colExplicacion = new TableColumn<>("Explicacion Tema");

        table_tema.getColumns().addAll(colID, colNombre,colMateria, colExplicacion);

        addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                Actions_Tema.getTemas();
                colID.setCellValueFactory(new PropertyValueFactory<>("IDTema"));
                colID.setPrefWidth(70);

                colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre_Tema"));
                colNombre.setPrefWidth(200);

                colMateria.setCellValueFactory(new PropertyValueFactory<>("Materia"));
                colMateria.setPrefWidth(90);

                colExplicacion.setCellValueFactory(new PropertyValueFactory<>("Explicacion_Tema"));
                colExplicacion.setPrefWidth(280);

                table_tema.getItems().addAll(Tema.Temas);

            }catch (SQLException ex){
                Window_Dialog.display("Error al cargar los datos", ex.getMessage());
            }

        });

        StackPane centerside = new StackPane();
        centerside.setPadding(new Insets(5));
        centerside.setAlignment(Pos.CENTER);
        centerside.getChildren().add(table_tema);

        return centerside;

    }

    private StackPane BOTTOMSIDE(){
        // BOTTOMSIDE -----------------------------

        Button eliminarT = new Button("Eliminar Tema Seleccionado");
        eliminarT.setPadding(new Insets(10));
        eliminarT.setFont(new Font(15));

        eliminarT.setOnAction(event -> {
            try
            {
                Tema tema  = table_tema.getSelectionModel().getSelectedItem();
                Utilities.eliminarPageT(tema);
                Actions_Tema.deleteTema(tema);

                Window_Dialog.display("Elminar Tema", "Tema borrado exitosamente");
            }catch (SQLException ex){
                Window_Dialog.display("Elminar Tema", "Error al borrar tema " + ex.getMessage());
            }
        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(eliminarT);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    EliminarTema(Stage owner){
        super();
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Eliminar Tema");

        // ROOT -----------------------------

        BorderPane root = new BorderPane();
        root.setCenter(CENTERSIDE());
        root.setBottom(BOTTOMSIDE());


        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("style/Style.css");

        setScene(scene);
    }




}
