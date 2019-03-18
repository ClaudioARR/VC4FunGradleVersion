package window;

import acciones_entidades.Actions_Tema;
import entidades.Tema;
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
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class ModificarTemaWindow {

    static private  Stage window = new Stage();
    static private  TextField idtema = new TextField();
    static private  TextField nombretema = new TextField();
    static private  TextField materiatema = new TextField();
    static private  TextArea explicaciontema = new TextArea();

    static private GridPane TOPSIDE(Tema tema){
        Label lblidtema = new Label("ID Tema");
        Label lblnombretema = new Label("Nombre Tema");
        Label lblmateriatema = new Label("Materia Tema");



        idtema.setText(String.valueOf(tema.getIDTema()));
        idtema.setEditable(false);


        nombretema.setText(tema.getNombre_Tema());


        materiatema.setText(String.valueOf(tema.getMateria()));

        GridPane topside = new GridPane();

        topside.setPadding(new Insets(10,5,10,10));
        topside.setVgap(5);
        topside.setHgap(30);

        GridPane.setConstraints(lblidtema, 0, 0);
        GridPane.setConstraints(lblnombretema, 1, 0);
        GridPane.setConstraints(lblmateriatema, 2, 0);

        GridPane.setConstraints(idtema, 0, 1);
        GridPane.setConstraints(nombretema, 1, 1);
        GridPane.setConstraints(materiatema, 2, 1);

        topside.getChildren().addAll(lblidtema,lblnombretema,lblmateriatema,idtema,nombretema,materiatema);

        return topside;
    }

    static private VBox CENTERSIDE(Tema tema){
        Label lblexplicaciontema = new Label("Explicacion Tema");


        explicaciontema.setText(tema.getExplicacion_Tema());
        explicaciontema.setWrapText(true);

        VBox centerside = new VBox();
        centerside.setAlignment(Pos.CENTER);
        centerside.setPadding(new Insets(10));

        centerside.getChildren().addAll(lblexplicaciontema,explicaciontema);

        return centerside;
    }

    static private StackPane BOTSIDE(){
        Button btnModificarTema = new Button("Modificar");
        btnModificarTema.setPadding(new Insets(10));
        btnModificarTema.setFont(new Font(15));

        btnModificarTema.setOnAction(event -> {
            try{
                int idT = Integer.parseInt(idtema.getText());
                String nombreT = nombretema.getText();
                int materiaT = Integer.parseInt(materiatema.getText());
                String explicacionT = explicaciontema.getText();

                Tema tema = new Tema();
                tema.setIDTema(idT);
                tema.setNombre_Tema(nombreT);
                tema.setMateria(materiaT);
                tema.setExplicacion_Tema(explicacionT);

                Actions_Tema.updateTema(tema);

                Window_Dialog.display("Modificar Tema", "Tema Modificado Exitosamente");

                window.close();

            }catch (SQLException ex){
                Window_Dialog.display("Modificar Tema", "Error al modificar tema " + ex.getMessage());
            }
        });

        StackPane botside = new StackPane();
        botside.setAlignment(Pos.CENTER);
        botside.setPadding(new Insets(5,5,15,5));

        botside.getChildren().add(btnModificarTema);

        return botside;
    }

    static void display(Tema tema){
        BorderPane root = new BorderPane();
        root.setTop(TOPSIDE(tema));
        root.setCenter(CENTERSIDE(tema));
        root.setBottom(BOTSIDE());


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modificar Tema");

        // SCENE ----------------------------

        Scene scene = new Scene(root, 650, 350);
        scene.getStylesheets().add("style/Style.css");

        window.setScene(scene);
        window.show();

    }
}
