package com.salesianas.manageralumnos.controller;
import com.salesianas.manageralumnos.HelloApplication;
import com.salesianas.manageralumnos.model.Alumno;
import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class ListadoController {
 @FXML private TableView<Alumno> tabla;
 @FXML private TableColumn<Alumno,Number> colId;
 @FXML private TableColumn<Alumno,String> colNombre,colApellidos,colCorreo,colCurso;
 @FXML private Button btnEditar,btnEliminar;
 @FXML public void initialize(){
  colId.setCellValueFactory(d->d.getValue().idProperty());
  colNombre.setCellValueFactory(d->d.getValue().nombreProperty());
  colApellidos.setCellValueFactory(d->d.getValue().apellidosProperty());
  colCorreo.setCellValueFactory(d->d.getValue().correoProperty());
  colCurso.setCellValueFactory(d->d.getValue().cursoProperty());
  tabla.setItems(AlumnosRepository.todos());
  btnEditar.disableProperty().bind(tabla.getSelectionModel().selectedItemProperty().isNull());
  btnEliminar.disableProperty().bind(tabla.getSelectionModel().selectedItemProperty().isNull());
  tabla.setPlaceholder(new Label("No hay alumnos registrados"));
 }
 @FXML private void nuevo(){abrirFormulario(null);}
 @FXML private void editar(){Alumno a=tabla.getSelectionModel().getSelectedItem();if(a!=null)abrirFormulario(a);}
 @FXML private void eliminar(){
  Alumno a=tabla.getSelectionModel().getSelectedItem(); if(a==null)return;
  Alert confirm=new Alert(Alert.AlertType.CONFIRMATION,"¿Eliminar a "+a.getNombre()+" "+a.getApellidos()+"?",ButtonType.YES,ButtonType.NO);
  confirm.setHeaderText("Confirmar eliminación");
  confirm.showAndWait().filter(b->b==ButtonType.YES).ifPresent(b->AlumnosRepository.eliminar(a));
 }
 private void abrirFormulario(Alumno alumno){
  try {
   FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("formulario-view.fxml"));
   Parent root=loader.load();
   FormularioController controller=loader.getController();
   controller.setAlumno(alumno);
   Stage dialog=new Stage();dialog.setTitle(alumno==null?"Nuevo alumno":"Editar alumno");
   dialog.initOwner(tabla.getScene().getWindow());dialog.initModality(Modality.WINDOW_MODAL);
   dialog.setScene(new Scene(root));dialog.setResizable(false);dialog.showAndWait();
   tabla.refresh();
  } catch(Exception e){new Alert(Alert.AlertType.ERROR,"No se pudo abrir el formulario: "+e.getMessage()).showAndWait();}
 }
}
