package window;

import acciones_entidades.Actions_Materia;
import entidades.Materia;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import windows_helpers.Window_Dialog;

import java.sql.SQLException;

class AgregarMateria extends Stage {
    private TextField txtnombremateria = new TextField();

    private StackPane TOPSIDE(){
       // TOPSIDE -------------------------------

       Label title = new Label("AGREGAR MATERIA");
       title.setFont(new Font(20));
       title.setPadding(new Insets(10));

       StackPane topside = new StackPane();
       topside.getChildren().add(title);
       topside.setAlignment(Pos.CENTER);

       return topside;
   }

   private GridPane CENTERSIDE(){
       // CENTERSIDE -----------------------------

       Label nombremateria = new Label("Nombre Materia");
       nombremateria.setWrapText(true);



       GridPane centerside = new GridPane();
       centerside.setPadding(new Insets(10,5,10,10));
       centerside.setVgap(5);
       centerside.setHgap(30);

       GridPane.setConstraints(nombremateria, 0, 0);

       GridPane.setConstraints(txtnombremateria, 1, 0);

       centerside.getChildren().addAll(nombremateria, txtnombremateria);

       return centerside;
   }

   private StackPane BOTSIDE(){
       // BOTTOMSIDE -----------------------------

       Button agregarM = new Button("Agregar Materia");
       agregarM.setPadding(new Insets(10));
       agregarM.setFont(new Font(15));

       agregarM.setOnAction(event -> {
           try {
               Materia materia = new Materia(txtnombremateria.getText());
               Actions_Materia.insertarMateria(materia);
               txtnombremateria.setText("");
               Window_Dialog.display("Exito!","Materia agregada satisfactoriamente!");
           }catch (SQLException ex){
               System.out.println(ex.getMessage());
           }

       });

       StackPane botside = new StackPane();
       botside.setPadding(new Insets(5,5,15,5));
       botside.getChildren().add(agregarM);
       botside.setAlignment(Pos.CENTER);

       return  botside;
   }

    AgregarMateria(Stage owner){
       super();
       initOwner(owner);
       initModality(Modality.APPLICATION_MODAL);
       setTitle("Agregar Materia");

       // ROOT -----------------------------

       BorderPane root = new BorderPane();
       root.setTop(TOPSIDE());
       root.setCenter(CENTERSIDE());
       root.setBottom(BOTSIDE());


       // SCENE ----------------------------

       Scene scene = new Scene(root, 300, 150);
       scene.getStylesheets().add("style/Style.css");

       setScene(scene);

   }




}
