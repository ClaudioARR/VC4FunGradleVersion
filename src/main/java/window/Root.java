package window;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Root extends Application {

    private VBox TOPSIDE(){
        //  TOPSIDE ---------------------------------------------------------

        Label title = new Label("¡BIENVENIDO A LA CARPETA VIRTUAL!");
        title.setFont(new Font("monospace", 25));
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
        agregar_ejercicio.setOnAction(event -> AgregarEjercicio.display());

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
        modificar_tema.setOnAction(event -> ModificarTema.display());

        Button modificar_ejercicio = new Button("Modificar Ejercicio");
        modificar_ejercicio.setFont(new Font(15));
        modificar_ejercicio.setOnAction(event -> ModificarEjercicio.display());

        VBox centerside = new VBox();
        centerside.setSpacing(10);
        centerside.getChildren().addAll(modificar_tema, modificar_ejercicio);
        centerside.setPadding(new Insets(10,10,10,15));

        return centerside;
    }

    private VBox RIGHTSIDE(){
        // RIGHTSIDE

        Button eliminar_materia = new Button("Eliminar Materia");
        eliminar_materia.setFont(new Font(15));
        eliminar_materia.setOnAction(event -> EliminarMateria.display());

        Button eliminar_tema = new Button("Eliminar Tema");
        eliminar_tema.setFont(new Font(15));
        eliminar_tema.setOnAction(event -> EliminarTema.display());

        Button eliminar_ejercicio = new Button("Eliminar Ejercicio");
        eliminar_ejercicio.setFont(new Font(15));
        eliminar_ejercicio.setOnAction(event -> EliminarEjercicio.display());

        VBox rightside = new VBox();
        rightside.setSpacing(10);
        rightside.getChildren().addAll(eliminar_materia,eliminar_tema, eliminar_ejercicio);
        rightside.setPadding(new Insets(10,10,10,15));

        return rightside;

    }

    private StackPane BOTSIDE(){
        Button btnEstudiar = new Button("ESTUDIAR!");
        btnEstudiar.setFont(new Font(15));
        btnEstudiar.setOnAction(event -> EstudiarWindow.display());

        StackPane botside = new StackPane();
        botside.setAlignment(Pos.CENTER);
        botside.setPadding(new Insets(10));

        botside.getChildren().add(btnEstudiar);

        return botside;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Carpeta Virtual");

        // ROOT -----------------------------------------------------------

        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE());
        root.setLeft(LEFTSIDE());
        root.setCenter(CENTERSIDE());
        root.setRight(RIGHTSIDE());
        root.setBottom(BOTSIDE());

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("style/Style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
