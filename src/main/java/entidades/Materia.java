package entidades;

import dao.SQLite_Connection;

import java.util.ArrayList;


public class Materia {

    SQLite_Connection sqlite;

    private int IDMateria;
    private String Nombre_Materia;

    public static ArrayList<Materia> Materias = new ArrayList<>();

    public Materia(){}

    public Materia(String nombre_materia){
        this.Nombre_Materia = nombre_materia;
    }

    public int getIDMateria() {
        return IDMateria;
    }

    public void setIDMateria(int IDMateria) {
        this.IDMateria = IDMateria;
    }


    public String getNombre_Materia() {
        return Nombre_Materia;
    }

    public void setNombre_Materia(String nombre_Materia) {
        Nombre_Materia = nombre_Materia;
    }



    @Override
    public String toString() {
        return "Materia{" +
                "IDMateria=" + IDMateria +
                ", Nombre_Materia='" + Nombre_Materia + '\'' +
                '}';
    }

}
