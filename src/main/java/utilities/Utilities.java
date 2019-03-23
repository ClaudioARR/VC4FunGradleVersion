package utilities;

import entidades.Ejercicios;
import entidades.Tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utilities {

    static  public String getFileName(String name){
        char[] fileName = name.toCharArray();
        for(int i = 0; i < fileName.length; i++){
            if (fileName[i] == ' '){
                fileName[i] = '_';
            }
        }

        return new String(fileName);
    }

    // TEMA

    static public void generatePageT(Tema tema){
        String explicacionlatex = "<!DOCTYPE html>\n" +
                " <html>\n" +
                " <head>\n" +
                "  <script type=\"text/x-mathjax-config\">\n" +
                "   MathJax.Hub.Config({     tex2jax: {inlineMath: [[\"$\",\"$\"],[\"\\\\(\",\"\\\\)\"]]}   });\n" +
                " </script>\n" +
                " <script type=\"text/javascript\" src=\"../MathJax/MathJax.js?config=TeX-AMS_HTML-full\">\n" +
                "</script>\n" +
                "  </head>\n" +
                " <body>\n" +
                tema.getExplicacion_Tema() + "\n" +
                "</body>\n" +
                "</html>";


        String namePage = getFileName(tema.getNombre_Tema());
        FileOutputStream archivo;
        PrintStream p;

        try {
            archivo = new FileOutputStream("pages/" + namePage + ".html");

            p = new PrintStream(archivo);
            p.println(explicacionlatex);
            p.close();
        } catch (FileNotFoundException ignored) {
        }
    }

    static public boolean eliminarPageT(Tema tema){
        String page = getFileName(tema.getNombre_Tema());
        File file = new File("pages/" + page + ".html");
        return file.delete();
    }


    // EJERCICIOS

    static public void generatePageE(Ejercicios ejercicio){
        String explicacionlatex = "<!DOCTYPE html>\n" +
                " <html>\n" +
                " <head>\n" +
                "  <script type=\"text/x-mathjax-config\">\n" +
                "   MathJax.Hub.Config({     tex2jax: {inlineMath: [[\"$\",\"$\"],[\"\\\\(\",\"\\\\)\"]]}   });\n" +
                " </script>\n" +
                " <script type=\"text/javascript\" src=\"../../MathJax/MathJax.js?config=TeX-AMS_HTML-full\">\n" +
                "</script>\n" +
                "  </head>\n" +
                " <body>\n" +
                ejercicio.getPropiedades_Ejercicio() + "\n" +
                "</body>\n" +
                "</html>";


        String idPage = String.valueOf(ejercicio.getIDEjercicio());
        FileOutputStream archivo;
        PrintStream p;

        try {
            archivo = new FileOutputStream("pages/exercises/" + idPage + ".html");

            p = new PrintStream(archivo);
            p.println(explicacionlatex);
            p.close();
        } catch (FileNotFoundException ignored) {
        }
    }

    static public boolean eliminarPageE(Ejercicios ejercicio){
        String page = String.valueOf(ejercicio.getIDEjercicio());
        File file = new File("pages/exercises/" + page + ".html");
        return file.delete();
    }
}
