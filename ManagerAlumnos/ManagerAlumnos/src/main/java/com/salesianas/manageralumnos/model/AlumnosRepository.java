package com.salesianas.manageralumnos.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlumnosRepository {

    // Lista observable de alumnos
    private static final ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
    private static int contadorId = 1;

    static {
        // Carga inicial de datos de prueba
        agregar("Ana", "López", "ana@ejemplo.es", "2.º DAM");
        agregar("Carlos", "Gómez", "carlos@ejemplo.es", "1.º DAM");
    }

    public static ObservableList<Alumno> todos() {
        return listaAlumnos;
    }

    public static void agregar(String nombre, String apellidos, String email, String curso) {
        listaAlumnos.add(new Alumno(contadorId++, nombre, apellidos, email, curso));
    }

    public static void eliminar(Alumno alumno) {
        if (alumno != null) {
            listaAlumnos.remove(alumno);
        }
    }
}