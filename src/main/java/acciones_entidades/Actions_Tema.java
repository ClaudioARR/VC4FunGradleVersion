package acciones_entidades;

import dao.SQLite_Connection;
import entidades.Tema;

import java.sql.SQLException;

public  class  Actions_Tema {
    static private SQLite_Connection sqlite;

    static {
        sqlite = new SQLite_Connection();
    }

    // CRUD

    public static  void insertarTema(Tema tema) throws SQLException {
        sqlite.Insertar("INSERT INTO Tema (nombreTema,Materia,explicacionTema) VALUES ('" +
                 tema.getNombre_Tema() +"'," +
                 tema.getMateria() +",'" +
                 tema.getExplicacion_Tema() +"');");
    }

    public static void getTemas() throws SQLException {
        String query = "SELECT idtema, nombreTema, Materia, explicacionTema FROM Tema";
        sqlite.obtenerTemas(query);
    }

    public static void getTemasForMaterias(int Materia) throws SQLException {
        String query = "SELECT idtema, nombreTema, Materia, explicacionTema" +
                " FROM Tema" +
                " WHERE Materia = " + Materia;
        sqlite.obtenerTemas(query);
    }



    public static void updateTema(Tema tema) throws SQLException {
        String query = "UPDATE Tema SET nombreTema = ? , "
                + "Materia = ? ,"
                + "explicacionTema = ? "
                + "WHERE idtema = ?";
        sqlite.updateTema(tema, query);
    }

    public static void deleteTema(Tema tema) throws SQLException {
        String query = "DELETE FROM Tema WHERE idtema = ?";
        sqlite.deleteTema(tema, query);
    }
}
