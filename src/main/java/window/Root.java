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

    private VBox LEFTSIDE(Stage owner){
        //  LEFTSIDE -----------------------------------------------------

        Button agregar_materia = new Button("Agregar Materia");
        agregar_materia.setFont(new Font(15));
        agregar_materia.setOnAction(event -> {
            AgregarMateria agregarMateriaW = new AgregarMateria(owner);
            agregarMateriaW.sizeToScene();
            agregarMateriaW.show();
        });


        Button agregar_tema = new Button("Agregar Tema");
        agregar_tema.setFont(new Font(15));
        agregar_tema.setOnAction(event -> {
            AgregarTema agregarTemaW = new AgregarTema(owner);
            agregarTemaW.sizeToScene();
            agregarTemaW.show();
        });

        Button agregar_ejercicio = new Button("Agregar Ejercicio");
        agregar_ejercicio.setFont(new Font(15));
        agregar_ejercicio.setOnAction(event -> {
            AgregarEjercicio agregarEjercicioW = new AgregarEjercicio(owner);
            agregarEjercicioW.sizeToScene();
            agregarEjercicioW.show();
        });

        VBox leftside = new VBox();
        leftside.setSpacing(10);
        leftside.getChildren().addAll(agregar_materia, agregar_tema, agregar_ejercicio);
        leftside.setPadding(new Insets(10,10,10,15));

        return leftside;
    }

    private VBox CENTERSIDE(Stage owner){
        // CENTERSIDE

        Button modificar_tema = new Button("Modificar Tema");
        modificar_tema.setFont(new Font(15));
        modificar_tema.setOnAction(event -> {
            ModificarTema modificarTemaW = new ModificarTema(owner);
        });

        Button modificar_ejercicio = new Button("Modificar Ejercicio");
        modificar_ejercicio.setFont(new Font(15));
        modificar_ejercicio.setOnAction(event -> {
            ModificarEjercicio modificarEjercicioW = new ModificarEjercicio(owner);
        });

        VBox centerside = new VBox();
        centerside.setSpacing(10);
        centerside.getChildren().addAll(modificar_tema, modificar_ejercicio);
        centerside.setPadding(new Insets(10,10,10,15));

        return centerside;
    }

    private VBox RIGHTSIDE(Stage owner){
        // RIGHTSIDE

        Button eliminar_materia = new Button("Eliminar Materia");
        eliminar_materia.setFont(new Font(15));
        eliminar_materia.setOnAction(event -> {
            EliminarMateria eliminarMateriaW = new EliminarMateria(owner);
            eliminarMateriaW.sizeToScene();
            eliminarMateriaW.show();
        });

        Button eliminar_tema = new Button("Eliminar Tema");
        eliminar_tema.setFont(new Font(15));
        eliminar_tema.setOnAction(event -> {

            EliminarTema eliminarTemaW = new EliminarTema(owner);
            eliminarTemaW.sizeToScene();
            eliminarTemaW.show();
        });

        Button eliminar_ejercicio = new Button("Eliminar Ejercicio");
        eliminar_ejercicio.setFont(new Font(15));
        eliminar_ejercicio.setOnAction(event -> {

            EliminarEjercicio eliminarEjercicioW = new EliminarEjercicio(owner);
            eliminarEjercicioW.sizeToScene();
            eliminarEjercicioW.show();
        });

        VBox rightside = new VBox();
        rightside.setSpacing(10);
        rightside.getChildren().addAll(eliminar_materia,eliminar_tema, eliminar_ejercicio);
        rightside.setPadding(new Insets(10,10,10,15));

        return rightside;

    }

    private StackPane BOTSIDE(Stage owner){
        Button btnEstudiar = new Button("ESTUDIAR!");
        btnEstudiar.setFont(new Font(15));
        btnEstudiar.setOnAction(event -> {

            EstudiarWindow estudiarWindowW = new EstudiarWindow(owner);
            estudiarWindowW.sizeToScene();
            estudiarWindowW.show();
        });

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
        root.setLeft(LEFTSIDE(primaryStage));
        root.setCenter(CENTERSIDE(primaryStage));
        root.setRight(RIGHTSIDE(primaryStage));
        root.setBottom(BOTSIDE(primaryStage));

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("style/Style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
