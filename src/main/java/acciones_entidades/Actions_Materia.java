package acciones_entidades;

import dao.SQLite_Connection;
import entidades.Materia;

import java.sql.SQLException;

public  class  Actions_Materia {
    static private SQLite_Connection sqlite;


     static {
         sqlite = new SQLite_Connection();
     }

     // CRUD

    public static  void insertarMateria(Materia materia) throws SQLException {
        sqlite.Insertar("INSERT INTO Materia (nombreMateria) VALUES ('" + materia.getNombre_Materia() + "');");
    }

    public static void getMaterias() throws SQLException {
         String query = "SELECT idmateria, nombreMateria FROM Materia";
         sqlite.obtenerMaterias(query);
    }

    public static void deleteMateria(Materia materia) throws SQLException {
        String query = "DELETE FROM Materia WHERE idmateria = ?";
        sqlite.deleteMateria(materia, query);
    }


}
