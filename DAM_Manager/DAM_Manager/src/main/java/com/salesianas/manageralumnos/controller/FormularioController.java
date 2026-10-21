package com.salesianas.manageralumnos.controller;
import com.salesianas.manageralumnos.model.Alumno;
import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormularioController {
 @FXML private TextField txtNombre,txtApellidos,txtCorreo;
 @FXML private ComboBox<String> cmbCurso;
 @FXML private Button btnGuardar;
 @FXML private Label lblTitulo;
 private Alumno alumnoEditado;
 @FXML public void initialize(){
  cmbCurso.getItems().addAll("1.º DAM","2.º DAM");
  btnGuardar.disableProperty().bind(txtNombre.textProperty().isEmpty()
    .or(txtApellidos.textProperty().isEmpty())
    .or(txtCorreo.textProperty().isEmpty())
    .or(cmbCurso.valueProperty().isNull()));
 }
 public void setAlumno(Alumno alumno){
  alumnoEditado=alumno;
  if(alumno!=null){lblTitulo.setText("Modificar alumno");txtNombre.setText(alumno.getNombre());txtApellidos.setText(alumno.getApellidos());txtCorreo.setText(alumno.getCorreo());cmbCurso.setValue(alumno.getCurso());}
 }
 @FXML private void guardar(){
  String nombre=txtNombre.getText().trim(),apellidos=txtApellidos.getText().trim(),correo=txtCorreo.getText().trim();
  if(nombre.isEmpty()||apellidos.isEmpty()||correo.isEmpty()||!correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
   new Alert(Alert.AlertType.WARNING,"Revisa los campos. El correo debe tener un formato válido.").showAndWait();return;
  }
  if(alumnoEditado==null)AlumnosRepository.agregar(nombre,apellidos,correo,cmbCurso.getValue());
  else {alumnoEditado.setNombre(nombre);alumnoEditado.setApellidos(apellidos);alumnoEditado.setCorreo(correo);alumnoEditado.setCurso(cmbCurso.getValue());}
  cerrar();
 }
 @FXML private void cancelar(){cerrar();}
 private void cerrar(){((Stage)txtNombre.getScene().getWindow()).close();}
}
