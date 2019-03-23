package windows_helpers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Window_Dialog {
    public static void display(String title, String message){

        Stage dialog;
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        StackPane center = new StackPane();
        StackPane bottom = new StackPane();
        BorderPane root = new BorderPane();

        Label mensaje = new Label(message);
        mensaje.setFont(new Font(15));
        center.getChildren().add(mensaje);
        center.setAlignment(Pos.CENTER);

        Button cerrar = new Button("Cerrar");
        cerrar.setFont(new Font(15));
        cerrar.setPadding(new Insets(10));
        cerrar.setOnAction(event -> dialog.close());
        bottom.getChildren().add(cerrar);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(5,5,15,5));


        root.setCenter(center);

        root.setBottom(bottom);



        Scene scene = new Scene(root, 300, 100);
        scene.getStylesheets().add("style/Style.css");

        dialog.setScene(scene);
        dialog.show();
    }
}
