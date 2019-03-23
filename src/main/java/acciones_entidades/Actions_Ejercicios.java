package acciones_entidades;

import dao.SQLite_Connection;
import entidades.Ejercicios;

import java.sql.SQLException;

public class Actions_Ejercicios {
    static private SQLite_Connection sqlite;

    static {
        sqlite = new SQLite_Connection();
    }

    // CRUD

    public static  void insertarEjercicios(Ejercicios ejercicio) throws SQLException {
        sqlite.Insertar("INSERT INTO Ejercicios (ejercicio,Tema,propiedades) VALUES ('" +
                ejercicio.getNombre_Ejercicio() +"'," +
                ejercicio.getTema() +",'" +
                ejercicio.getPropiedades_Ejercicio() +"');");
    }

    public static void getEjercicios() throws SQLException {
        String query = "SELECT idejercicio, ejercicio, Tema, propiedades FROM Ejercicios";
        sqlite.obtenerEjercicios(query);
    }

    public static void getEjercicioPorTema(int idtema) throws SQLException {
        String query = "SELECT idejercicio, ejercicio, Tema, propiedades FROM Ejercicios WHERE Tema = " + idtema;
        sqlite.obtenerEjercicios(query);
    }

    public static void updateEjercicio(Ejercicios ejercicio) throws SQLException {
        String query = "UPDATE Ejercicios SET ejercicio = ? , "
                + "Tema = ? ,"
                + "propiedades = ? "
                + "WHERE idejercicio = ?";
        sqlite.updateEjercicio(ejercicio, query);
    }

    public static void deleteEjercicio(Ejercicios ejercicio) throws SQLException {
        String query = "DELETE FROM Ejercicios WHERE idejercicio = ?";
        sqlite.deleteEjercicio(ejercicio, query);
    }
}
