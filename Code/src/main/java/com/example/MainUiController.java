/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.*;

/**
 *
 * @author giuseppe.depietro
 */
public class MainUiController {
 

   /*  @FXML private CheckBox checkPulsante;
    @FXML private Button btnDettagli;
    @FXML private TextField txtCognome;
    @FXML private TextField txtNome;
    @FXML private TextField txtTelefono;

    @FXML private TableView<Persona> tabellaPersone;

    @FXML private TableColumn<Persona, String> colonnaCognome;
    @FXML private TableColumn<Persona, String> colonnaNome;
    @FXML private TableColumn<Persona, String> colonnaTelefono;
    */

    
    @FXML
    private void initialize(){
        /*colonnaCognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeProperty());
        colonnaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        colonnaTelefono.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());
        tabellaPersone.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)-> mostraDettagliPersona(newValue));
    */
    }

    void setMainModel() {
                //tabellaPersone.setItems(gestione.getElencoPersone());
    }
    
    /*  private void mostraDettagliPersona(Persona p) {
        if (p!=null){
            txtCognome.setText(p.getCognome());
            txtNome.setText(p.getNome());
            txtTelefono.setText(p.getTelefono());
           
            
        }
        
        //tabellaPersone.getSelectionModel().clearSelection();
    }
     
     @FXML
     private void metodoCheNonFaNulla(){
         int indiceSelezionato=tabellaPersone.getSelectionModel().getSelectedIndex();
         if(indiceSelezionato!=-1){
            Persona p=tabellaPersone.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(p.getCognome());
            alert.showAndWait();
            
         }
         
         
     }
     
     @FXML
     private void rimuoviPersona(){
         int indiceSelezionato=0;
         //eliminare un oggetto dalla lista
         gestione.getElencoPersone().remove(indiceSelezionato);
         //aggiungere un oggetto alla lista
         gestione.getElencoPersone().add(new Persona("","",""));
     }
     @FXML
     private void mostraNascondiPulsante(){
         if(checkPulsante.isSelected()){
             btnDettagli.setVisible(false);
         }else{
             btnDettagli.setVisible(true);
         }
         
     }*/
    
}
