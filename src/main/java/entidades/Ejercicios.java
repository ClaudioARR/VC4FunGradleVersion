package entidades;

public class Ejercicios {

    private int IDEjercicio;
    private String Nombre_Ejercicio;
    private int Tema;
    private String Propiedades_Ejercicio;

    public Ejercicios() {
    }

    public Ejercicios(String nombre_Ejercicio, int tema, String propiedades_Tema) {
        Nombre_Ejercicio = nombre_Ejercicio;
        Tema = tema;
        Propiedades_Ejercicio = propiedades_Tema;
    }

    public int getIDEjercicio() {
        return IDEjercicio;
    }

    public void setIDEjercicio(int IDEjercicio) {
        this.IDEjercicio = IDEjercicio;
    }

    public String getNombre_Ejercicio() {
        return Nombre_Ejercicio;
    }

    public void setNombre_Ejercicio(String nombre_Ejercicio) {
        Nombre_Ejercicio = nombre_Ejercicio;
    }

    public int getTema() {
        return Tema;
    }

    public void setTema(int tema) {
        Tema = tema;
    }

    public String getPropiedades_Ejercicio() {
        return Propiedades_Ejercicio;
    }

    public void setPropiedades_Ejercicio(String propiedades_Ejercicio) {
        Propiedades_Ejercicio = propiedades_Ejercicio;
    }

    @Override
    public String toString() {
        return "Ejercicios{" +
                "IDEjercicio=" + IDEjercicio +
                ", Nombre_Ejercicio='" + Nombre_Ejercicio + '\'' +
                ", Tema=" + Tema +
                ", Propiedades_Tema='" + Propiedades_Ejercicio + '\'' +
                '}';
    }
}
