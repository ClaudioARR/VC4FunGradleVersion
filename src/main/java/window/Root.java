package window;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Root extends Application {

    private VBox TOPSIDE(){
        //  TOPSIDE ---------------------------------------------------------

        Label title = new Label("¡BIENVENIDO A LA CARPETA VIRTUAL!");
        title.setFont(new Font("monospace", 30));
        title.setPadding(new Insets(10));

        Label subtitle = new Label("¿Que desea hacer?");
        subtitle.setFont(new Font("monospace", 20));
        subtitle.setPadding(new Insets(10));

        VBox topside = new VBox();
        topside.getChildren().addAll(title, subtitle);

        return topside;
    }

    private VBox LEFTSIDE(){
        //  LEFTSIDE -----------------------------------------------------

        Button agregar_materia = new Button("Agregar Materia");
        agregar_materia.setFont(new Font(15));
        agregar_materia.setOnAction(event -> AgregarMateria.display());


        Button agregar_tema = new Button("Agregar Tema");
        agregar_tema.setFont(new Font(15));
        agregar_tema.setOnAction(event -> AgregarTema.display());

        Button agregar_ejercicio = new Button("Agregar Ejercicio");
        agregar_ejercicio.setFont(new Font(15));
        agregar_ejercicio.setOnAction(event -> {
            AgregarEjercicio.display();
        });

        VBox leftside = new VBox();
        leftside.setSpacing(10);
        leftside.getChildren().addAll(agregar_materia, agregar_tema, agregar_ejercicio);
        leftside.setPadding(new Insets(10,10,10,15));

        return leftside;
    }

    private VBox CENTERSIDE(){
        // CENTERSIDE

        Button modificar_tema = new Button("Modificar Tema");
        modificar_tema.setFont(new Font(15));
        modificar_tema.setOnAction(event -> {
            ModificarTema.display();
        });

        Button modificar_ejercicio = new Button("Modificar Ejercicio");
        modificar_ejercicio.setFont(new Font(15));
        modificar_ejercicio.setOnAction(event -> {});

        VBox centerside = new VBox();
        centerside.setSpacing(10);
        centerside.getChildren().addAll(modificar_tema, modificar_ejercicio);
        centerside.setPadding(new Insets(10,10,10,15));

        return centerside;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Carpeta Virtual");

        // ROOT -----------------------------------------------------------

        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE());
        root.setLeft(LEFTSIDE());
        root.setCenter(CENTERSIDE());

        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add("style/Style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
