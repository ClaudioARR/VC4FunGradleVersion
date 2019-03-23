package window;

import acciones_entidades.Actions_Ejercicios;
import acciones_entidades.Actions_Materia;
import acciones_entidades.Actions_Tema;
import entidades.Ejercicios;
import entidades.Materia;
import entidades.Tema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilities.Utilities;

import java.io.File;
import java.sql.SQLException;

class EstudiarWindow {
    static private Stage window = new Stage();
    static private WebEngine weLatex;

    private static VBox LEFTSIDE(){
        ObservableList<String> itemsM = FXCollections.observableArrayList();
        ObservableList<String> itemsT = FXCollections.observableArrayList();
        ObservableList<String> itemsE = FXCollections.observableArrayList();


        window.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            try {
                itemsM.clear();
                Actions_Materia.getMaterias();
                for (Materia m:Materia.Materias) {
                    itemsM.add(m.getNombre_Materia());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        ComboBox cmbMaterias = new ComboBox<>(itemsM);
        cmbMaterias.setPromptText("Elija Materia");
        cmbMaterias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                itemsT.clear();
                int idmateria = cmbMaterias.getSelectionModel().getSelectedIndex();
                int idmateriatema = Materia.Materias.get(idmateria).getIDMateria();
                Actions_Tema.getTemasForMaterias(idmateriatema);
                for (Tema t: Tema.Temas){
                    itemsT.add(t.getNombre_Tema());
                }
            }catch (SQLException ignored){

            }
        });

        ComboBox cmbTemas = new ComboBox<>(itemsT);
        cmbTemas.setPromptText("Elija Tema");

        cmbTemas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                itemsE.clear();
                String pageName = Utilities.getFileName(cmbTemas.getSelectionModel().getSelectedItem().toString());
                File file = new File("pages\\" + pageName + ".html");
                weLatex.load(file.toURI().toString());

                int tema = cmbTemas.getSelectionModel().getSelectedIndex();
                int idtema = Tema.Temas.get(tema).getIDTema();
                Actions_Ejercicios.getEjercicioPorTema(idtema);
                for (Ejercicios e: Ejercicios.EjerciciosArray){
                    itemsE.add(String.valueOf(e.getIDEjercicio()));
                }
            }catch (Exception ignored){

            }
        });

        ComboBox cmbEjercicios = new ComboBox(itemsE);
        cmbEjercicios.setPromptText("Elija Ejercicio");

        cmbEjercicios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                String exerciseName = String.valueOf(cmbEjercicios.getSelectionModel().getSelectedItem());
                File file = new File("pages\\exercises\\" + exerciseName + ".html");
                weLatex.load(file.toURI().toString());
            }catch (Exception ignored){

            }
        });


        VBox leftside = new VBox(15);
        leftside.setPadding(new Insets(10));
        leftside.setAlignment(Pos.TOP_LEFT);
        leftside.getChildren().addAll(cmbMaterias, cmbTemas, cmbEjercicios);

        return leftside;

    }

    static StackPane CENTERSIDE(){
        WebView wvLatex = new WebView();
        weLatex = wvLatex.getEngine();

        StackPane centerside = new StackPane();
        centerside.setPadding(new Insets(10));
        centerside.setAlignment(Pos.CENTER);

        centerside.getChildren().add(wvLatex);

        return centerside;

    }

    static void display(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Estudiar");

        // ROOT -----------------------------

        BorderPane root = new BorderPane();
        root.setLeft(LEFTSIDE());
        root.setCenter(CENTERSIDE());


        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 400);
        scene.getStylesheets().add("style/Style.css");

        window.setScene(scene);
        window.show();
    }
}
