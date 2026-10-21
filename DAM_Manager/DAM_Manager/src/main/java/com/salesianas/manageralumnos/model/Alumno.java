package com.salesianas.manageralumnos.model;
import javafx.beans.property.*;
public class Alumno {
 private final IntegerProperty id = new SimpleIntegerProperty();
 private final StringProperty nombre = new SimpleStringProperty();
 private final StringProperty apellidos = new SimpleStringProperty();
 private final StringProperty correo = new SimpleStringProperty();
 private final StringProperty curso = new SimpleStringProperty();

 public Alumno(int id,String nombre,String apellidos,String correo,String curso){
  setId(id);
  setNombre(nombre);
  setApellidos(apellidos);
  setCorreo(correo);
  setCurso(curso);
 }
 public int getId(){return id.get();}
 public void setId(int v){id.set(v);}
 public IntegerProperty idProperty(){return id;}
 public String getNombre(){return nombre.get();}
 public void setNombre(String v){nombre.set(v);}
 public StringProperty nombreProperty(){return nombre;}
 public String getApellidos(){return apellidos.get();}
 public void setApellidos(String v){apellidos.set(v);}
 public StringProperty apellidosProperty(){return apellidos;}
 public String getCorreo(){return correo.get();}
 public void setCorreo(String v){correo.set(v);}
 public StringProperty correoProperty(){return correo;}
 public String getCurso(){return curso.get();}
 public void setCurso(String v){curso.set(v);}
 public StringProperty cursoProperty(){return curso;}
}
