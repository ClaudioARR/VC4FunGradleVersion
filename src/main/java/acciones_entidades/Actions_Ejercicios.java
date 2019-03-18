package acciones_entidades;

import dao.SQLite_Connection;
import entidades.Ejercicios;

import java.sql.SQLException;

public class Actions_Ejercicios {
    static private SQLite_Connection sqlite;

    static {
        try {
            sqlite = new SQLite_Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD

    public static  void insertarEjercicios(Ejercicios ejercicio) throws SQLException {
        sqlite.Insertar("INSERT INTO Ejercicios (ejercicio,Tema,propiedades) VALUES ('" +
                ejercicio.getNombre_Ejercicio() +"'," +
                ejercicio.getTema() +",'" +
                ejercicio.getPropiedades_Ejercicio() +"');");
    }
}
