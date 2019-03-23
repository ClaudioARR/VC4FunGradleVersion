package window;

import acciones_entidades.Actions_Ejercicios;
import acciones_entidades.Actions_Tema;
import entidades.Ejercicios;
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

class AgregarEjercicio {

    static private Stage window = new Stage();
    static private TextArea taejercicio = new TextArea();
    static private ComboBox<String> cmbTemas;
    static private TextArea tapropiedadesejercicio = new TextArea();



    static private StackPane TOPSIDE(){
        // TOPSIDE -------------------------------

        Label title = new Label("AGREGAR EJERCICIO");
        title.setFont(new Font(20));
        title.setPadding(new Insets(10));

        StackPane topside = new StackPane();
        topside.getChildren().add(title);
        topside.setAlignment(Pos.CENTER);

        return topside;
    }


    static private GridPane CENTERSIDE(){

        // CENTERSIDE -----------------------------

        Label nombreejercicio = new Label("Ejercicio");
        nombreejercicio.setWrapText(true);

        Label temaejercicio = new Label("Tema Ejercicio");
        temaejercicio.setWrapText(true);

        Label propiedadesejercicio = new Label("Solucion Ejercicio");
        propiedadesejercicio.setWrapText(true);


        taejercicio.setWrapText(true);
        ObservableList<String> items = FXCollections.observableArrayList();


        window.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                Actions_Tema.getTemas();
                for (Tema t:Tema.Temas) {
                    items.add(t.getNombre_Tema());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        cmbTemas = new ComboBox<>(items);
        cmbTemas.setPromptText("Elija Tema");


        tapropiedadesejercicio.setWrapText(true);


        GridPane centerside = new GridPane();
        centerside.setPadding(new Insets(10,5,10,10));
        centerside.setVgap(5);
        centerside.setHgap(30);

        GridPane.setConstraints(temaejercicio, 0, 0);
        GridPane.setConstraints(nombreejercicio, 0, 1);
        GridPane.setConstraints(propiedadesejercicio, 0, 2);

        GridPane.setConstraints(cmbTemas, 1, 0);
        GridPane.setConstraints(taejercicio, 1, 1);
        GridPane.setConstraints(tapropiedadesejercicio, 1, 2);

        centerside.getChildren().addAll(temaejercicio, nombreejercicio, propiedadesejercicio , cmbTemas, taejercicio, tapropiedadesejercicio);


        return centerside;
    }

    static private StackPane BOTTOMSIDE(){

        // BOTTOMSIDE -----------------------------

        Button agregarE = new Button("Agregar Ejercicio");
        agregarE.setPadding(new Insets(10));
        agregarE.setFont(new Font(15));

        agregarE.setOnAction(event -> {
            try {
                int idtema = cmbTemas.getSelectionModel().getSelectedIndex();
                Ejercicios ejercicio = new Ejercicios(taejercicio.getText(), Tema.Temas.get(idtema).getIDTema() ,tapropiedadesejercicio.getText());
                Actions_Ejercicios.insertarEjercicios(ejercicio);
                Actions_Ejercicios.getEjercicios();
                Utilities.generatePageE(Ejercicios.EjerciciosArray.get(Ejercicios.EjerciciosArray.size() - 1));
                taejercicio.setText("");
                tapropiedadesejercicio.setText("");
                Window_Dialog.display("Exito!","Ejercicio agregado satisfactoriamente!");
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

        });

        StackPane botside = new StackPane();
        botside.setPadding(new Insets(5,5,15,5));
        botside.getChildren().add(agregarE);
        botside.setAlignment(Pos.CENTER);

        return botside;
    }

    static void display(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Agregar Ejercicio");


        // ROOT -----------------------------

        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE());
        root.setCenter(CENTERSIDE());
        root.setBottom(BOTTOMSIDE());


        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("style/Style.css");

        window.setScene(scene);
        window.show();
    }




}

