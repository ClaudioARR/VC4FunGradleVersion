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
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class ModificarTema {

    static private Stage window = new Stage();
    static private TableView<Tema> table_tema;


    static private StackPane  CENTERSIDE(){
        // CENTERSIDE -----------------------------

        table_tema = new TableView<>();

        TableColumn<Tema, Integer> colID = new TableColumn<>("Tema ID");
        TableColumn<Tema, String> colNombre = new TableColumn<>("Nombre Tema");
        TableColumn<Tema, Integer> colMateria = new TableColumn<>("Materia Tema");
        TableColumn<Tema, String> colExplicacion = new TableColumn<>("Explicacion Tema");

        table_tema.getColumns().addAll(colID, colNombre,colMateria, colExplicacion);

        window.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
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

    static private StackPane BOTTOMSIDE(){
        // BOTTOMSIDE -----------------------------

        Button modificarT = new Button("Modificar Tema Seleccionado");
        modificarT.setPadding(new Insets(10));
        modificarT.setFont(new Font(15));

        modificarT.setOnAction(event -> {
            Tema tema  = table_tema.getSelectionModel().getSelectedItem();
            ModificarTemaWindow.display(tema);
        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(modificarT);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    static void display(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modificar Tema");

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
