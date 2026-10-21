package com.salesianas.manageralumnos.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public final class AlumnosRepository {

 private static final ObservableList<Alumno> ALUMNOS=FXCollections.observableArrayList();
 private static int siguienteId=1;
 private AlumnosRepository(){}
 public static ObservableList<Alumno> todos(){return ALUMNOS;}
 public static void agregar(String nombre,String apellidos,String correo,String curso){
  ALUMNOS.add(new Alumno(siguienteId++,nombre,apellidos,correo,curso));
 }
 public static void eliminar(Alumno alumno){
  ALUMNOS.remove(alumno);
 }
}
