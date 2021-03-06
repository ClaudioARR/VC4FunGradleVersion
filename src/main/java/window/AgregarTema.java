package window;

import acciones_entidades.Actions_Materia;
import acciones_entidades.Actions_Tema;
import entidades.Materia;
import entidades.Tema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilities.Utilities;
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class AgregarTema extends Stage {
    private TextField txtnombretema = new TextField();
    private ComboBox<String> cmbMaterias;
    private TextArea taexplicaciontema = new TextArea();


    private StackPane TOPSIDE(){
        // TOPSIDE -------------------------------

        Label title = new Label("AGREGAR TEMA");
        title.setFont(new Font(20));
        title.setPadding(new Insets(10));

        StackPane topside = new StackPane();
        topside.getChildren().add(title);
        topside.setAlignment(Pos.CENTER);

        return topside;

    }

    private GridPane CENTERSIDE(){
        // CENTERSIDE -----------------------------

        Label nombretema = new Label("Nombre Tema");
        nombretema.setWrapText(true);

        Label materiatema = new Label("Materia Tema");
        materiatema.setWrapText(true);

        Label explicaciontema = new Label("Explicacion Tema");
        explicaciontema.setWrapText(true);


        ObservableList<String> items = FXCollections.observableArrayList();


        addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                Actions_Materia.getMaterias();
                for (Materia m:Materia.Materias) {
                    items.add(m.getNombre_Materia());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
         cmbMaterias = new ComboBox<>(items);
         cmbMaterias.setPromptText("Elija Materia");


        taexplicaciontema.setWrapText(true);


        GridPane centerside = new GridPane();
        centerside.setPadding(new Insets(10,5,10,10));
        centerside.setVgap(5);
        centerside.setHgap(30);

        GridPane.setConstraints(nombretema, 0, 0);
        GridPane.setConstraints(materiatema, 0, 1);
        GridPane.setConstraints(explicaciontema, 0, 2);

        GridPane.setConstraints(txtnombretema, 1, 0);
        GridPane.setConstraints(cmbMaterias, 1, 1);
        GridPane.setConstraints(taexplicaciontema, 1, 2);

        centerside.getChildren().addAll(nombretema, materiatema, explicaciontema , txtnombretema, cmbMaterias, taexplicaciontema);

        return centerside;
    }

    private StackPane BOTTOMSIDE(){
        // BOTTOMSIDE -----------------------------

        Button agregarT = new Button("Agregar Tema");
        agregarT.setPadding(new Insets(10));
        agregarT.setFont(new Font(15));

        agregarT.setOnAction(event -> {
            try {
                int idmateria = cmbMaterias.getSelectionModel().getSelectedIndex();
                Tema tema = new Tema(txtnombretema.getText(), Materia.Materias.get(idmateria).getIDMateria() ,taexplicaciontema.getText());
                Actions_Tema.insertarTema(tema);
                Utilities.generatePageT(tema);
                txtnombretema.setText("");
                taexplicaciontema.setText("");
                Window_Dialog.display("Exito!","Tema agregado satisfactoriamente!");
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(agregarT);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    AgregarTema(Stage owner){
        super();
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Agregar Tema");

        // ROOT -----------------------------

        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE());
        root.setCenter(CENTERSIDE());
        root.setBottom(BOTTOMSIDE());


        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("style/Style.css");

        setScene(scene);
    }




}
