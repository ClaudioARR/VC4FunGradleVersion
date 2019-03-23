package dao;

import entidades.Ejercicios;
import entidades.Materia;
import entidades.Tema;

import java.sql.*;

public class SQLite_Connection {

    private Connection conexion = null;
    private Statement sql = null;
    private ResultSet results = null;

    public SQLite_Connection() {}


    private void Open() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:db//vc.db");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
    }

    private void Cerrar() throws SQLException {
        conexion.close();
    }


    public void Insertar(String query) throws SQLException {
        this.Open();
        sql = conexion.createStatement();
        sql.execute(query);
        this.Cerrar();
    }

    public void obtenerMaterias(String queryMateria) throws SQLException {
        this.Open();
        Materia.Materias.clear();
        Materia materia;
        sql = conexion.createStatement();
        results = sql.executeQuery(queryMateria);
        while (results.next()) {
            materia = new Materia();
            materia.setIDMateria(results.getInt(1));
            materia.setNombre_Materia(results.getString(2));
            Materia.Materias.add(materia);
        }
        this.Cerrar();
    }

    public void deleteMateria(Materia materia, String query) throws SQLException {
        this.Open();

        PreparedStatement pdst = conexion.prepareStatement(query);

        pdst.setInt(1, materia.getIDMateria());

        pdst.executeUpdate();

        this.Cerrar();
    }

    public void obtenerTemas(String queryTema) throws SQLException {
        this.Open();
        Tema.Temas.clear();
        Tema tema;
        sql = conexion.createStatement();
        results = sql.executeQuery(queryTema);
        while (results.next()) {
            tema = new Tema();
            tema.setIDTema(results.getInt(1));
            tema.setNombre_Tema(results.getString(2));
            tema.setMateria(results.getInt(3));
            tema.setExplicacion_Tema(results.getString(4));
            Tema.Temas.add(tema);
        }
        this.Cerrar();
    }

    public void updateTema(Tema tema, String query) throws SQLException {
        this.Open();

        PreparedStatement pdst = conexion.prepareStatement(query);

        pdst.setString(1, tema.getNombre_Tema());
        pdst.setInt(2, tema.getMateria());
        pdst.setString(3, tema.getExplicacion_Tema());
        pdst.setInt(4, tema.getIDTema());

        pdst.executeUpdate();

        this.Cerrar();
    }

    public void deleteTema(Tema tema, String query) throws SQLException {
        this.Open();

        PreparedStatement pdst = conexion.prepareStatement(query);

        pdst.setInt(1, tema.getIDTema());

        pdst.executeUpdate();

        this.Cerrar();
    }

    public void obtenerEjercicios(String queryEjercicio) throws SQLException {
        this.Open();
        Ejercicios.EjerciciosArray.clear();
        Ejercicios ejercicios;
        sql = conexion.createStatement();
        results = sql.executeQuery(queryEjercicio);
        while (results.next()) {
            ejercicios = new Ejercicios();
            ejercicios.setIDEjercicio(results.getInt(1));
            ejercicios.setNombre_Ejercicio(results.getString(2));
            ejercicios.setTema(results.getInt(3));
            ejercicios.setPropiedades_Ejercicio(results.getString(4));
            Ejercicios.EjerciciosArray.add(ejercicios);
        }
        this.Cerrar();
    }

    public void updateEjercicio(Ejercicios ejercicio, String query) throws SQLException {
        this.Open();

        PreparedStatement pdst = conexion.prepareStatement(query);

        pdst.setString(1, ejercicio.getNombre_Ejercicio());
        pdst.setInt(2, ejercicio.getTema());
        pdst.setString(3, ejercicio.getPropiedades_Ejercicio());
        pdst.setInt(4, ejercicio.getIDEjercicio());

        pdst.executeUpdate();

        this.Cerrar();
    }

    public void deleteEjercicio(Ejercicios ejercicio, String query) throws SQLException {
        this.Open();

        PreparedStatement pdst = conexion.prepareStatement(query);

        pdst.setInt(1, ejercicio.getIDEjercicio());

        pdst.executeUpdate();

        this.Cerrar();
    }

}


