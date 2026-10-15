package com.salesianas.manageralumnos.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo de datos Alumno.
 * Utiliza propiedades de JavaFX (IntegerProperty, StringProperty) para permitir
 * el enlace de datos (Binding) con los componentes de la interfaz como TableView.
 */
public class Alumno {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty curso = new SimpleStringProperty();

    public Alumno(int id, String nombre, String apellidos, String email, String curso) {
        setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setEmail(email);
        setCurso(curso);
    }

    // --- ID ---
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    // --- NOMBRE ---
    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public StringProperty nombreProperty() { return nombre; }

    // --- APELLIDOS ---
    public String getApellidos() { return apellidos.get(); }
    public void setApellidos(String apellidos) { this.apellidos.set(apellidos); }
    public StringProperty apellidosProperty() { return apellidos; }

    // --- EMAIL ---
    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    // --- CURSO ---
    public String getCurso() { return curso.get(); }
    public void setCurso(String curso) { this.curso.set(curso); }
    public StringProperty cursoProperty() { return curso; }
}