package window;

import acciones_entidades.Actions_Ejercicios;
import entidades.Ejercicios;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.Utilities;
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class ModificarEjercicioWindow extends Stage{
    private  TextField idejercicio = new TextField();
    private  TextField nombreejercicio = new TextField();
    private  TextField temaejercicio = new TextField();
    private  TextArea respuestaejercicio = new TextArea();

    private GridPane TOPSIDE(Ejercicios ejercicio){
        Label lblidejercicio = new Label("ID Ejercicio");
        Label lblnombreejercicio = new Label("Ejercicio");
        Label lbltemaejercicio = new Label("Ejercicio Tema");



        idejercicio.setText(String.valueOf(ejercicio.getIDEjercicio()));
        idejercicio.setEditable(false);


        nombreejercicio.setText(ejercicio.getNombre_Ejercicio());


        temaejercicio.setText(String.valueOf(ejercicio.getTema()));

        GridPane topside = new GridPane();

        topside.setPadding(new Insets(10,5,10,10));
        topside.setVgap(5);
        topside.setHgap(30);

        GridPane.setConstraints(lblidejercicio, 0, 0);
        GridPane.setConstraints(lblnombreejercicio, 1, 0);
        GridPane.setConstraints(lbltemaejercicio, 2, 0);

        GridPane.setConstraints(idejercicio, 0, 1);
        GridPane.setConstraints(nombreejercicio, 1, 1);
        GridPane.setConstraints(temaejercicio, 2, 1);

        topside.getChildren().addAll(lblidejercicio,lblnombreejercicio,lbltemaejercicio,idejercicio,nombreejercicio,temaejercicio);

        return topside;
    }

    private VBox CENTERSIDE(Ejercicios ejercicio){
        Label lblrespuestaejercicio = new Label("Respuesta");


        respuestaejercicio.setText(ejercicio.getPropiedades_Ejercicio());
        respuestaejercicio.setWrapText(true);

        VBox centerside = new VBox();
        centerside.setAlignment(Pos.CENTER);
        centerside.setPadding(new Insets(10));

        centerside.getChildren().addAll(lblrespuestaejercicio,respuestaejercicio);

        return centerside;
    }

    private StackPane BOTSIDE(){
        Button btnModificarEjercicio = new Button("Modificar");
        btnModificarEjercicio.setPadding(new Insets(10));
        btnModificarEjercicio.setFont(new Font(15));

        btnModificarEjercicio.setOnAction(event -> {
            try{
                int idE = Integer.parseInt(idejercicio.getText());
                String nombreE = nombreejercicio.getText();
                int temaE = Integer.parseInt(temaejercicio.getText());
                String respuestaE = respuestaejercicio.getText();

                Ejercicios ejercicio = new Ejercicios();
                ejercicio.setIDEjercicio(idE);
                ejercicio.setNombre_Ejercicio(nombreE);
                ejercicio.setTema(temaE);
                ejercicio.setPropiedades_Ejercicio(respuestaE);

                Actions_Ejercicios.updateEjercicio(ejercicio);
                Utilities.generatePageE(ejercicio);

                Window_Dialog.display("Modificar Ejercicio", "Ejercicio Modificado Exitosamente");

                close();

            }catch (SQLException ex){
                Window_Dialog.display("Modificar Ejercicio", "Error al modificar ejercicio " + ex.getMessage());
            }
        });

        StackPane botside = new StackPane();
        botside.setAlignment(Pos.CENTER);
        botside.setPadding(new Insets(5,5,15,5));

        botside.getChildren().add(btnModificarEjercicio);

        return botside;
    }

    ModificarEjercicioWindow(Ejercicios ejercicio, Stage owner){
        super();
        initOwner(owner);

        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE(ejercicio));
        root.setCenter(CENTERSIDE(ejercicio));
        root.setBottom(BOTSIDE());


        initModality(Modality.APPLICATION_MODAL);
        setTitle("Modificar Ejercicio");

        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 350);
        scene.getStylesheets().add("style/Style.css");

        setScene(scene);

    }
}
