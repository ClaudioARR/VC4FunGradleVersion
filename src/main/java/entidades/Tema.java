package entidades;

import java.util.ArrayList;

public class Tema {

    private int IDTema;
    private String Nombre_Tema;
    private int Materia;
    private String Explicacion_Tema;

    public static ArrayList<Tema> Temas = new ArrayList<>();

    public Tema(){}

    public Tema(String nombre_tema, int materia, String explicacion_tema){
        this.Nombre_Tema = nombre_tema;
        this.Materia = materia;
        this.Explicacion_Tema = explicacion_tema;
    }


    public int getIDTema() {
        return IDTema;
    }

    public void setIDTema(int IDTema) {
        this.IDTema = IDTema;
    }

    public String getNombre_Tema() {
        return Nombre_Tema;
    }

    public void setNombre_Tema(String nombre_Tema) {
        Nombre_Tema = nombre_Tema;
    }

    public int getMateria() {
        return Materia;
    }

    public void setMateria(int materia) {
        Materia = materia;
    }

    public String getExplicacion_Tema() {
        return Explicacion_Tema;
    }

    public void setExplicacion_Tema(String explicacion_Tema) {
        Explicacion_Tema = explicacion_Tema;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "IDTema=" + IDTema +
                ", Nombre_Tema='" + Nombre_Tema + '\'' +
                ", Materia=" + Materia +
                ", Explicacion_Tema='" + Explicacion_Tema + '\'' +
                '}';
    }
}
