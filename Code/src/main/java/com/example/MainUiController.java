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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.*;



public class MainUiController {
 

     @FXML
    private Label labelMoney;
    @FXML
    private Button btnCoinAdd;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnInspect;

    @FXML
    private TableView<Crypto> TableviewCrypto;
    //__
    @FXML
    private TableColumn<Crypto, String> columnName;
    @FXML
    private TableColumn<Crypto, String> columnPrice;
    @FXML
    private TableColumn<Crypto, String> columnQuantity;
    @FXML
    private TableColumn<Crypto, String> columnTotalValue;
    //__



    
    @FXML
    private void initialize(){
        Crypto Bitcoin = new Crypto("btc", 62000, 0.00001);
        Crypto Etherium = new Crypto("eth", 1000, 0.002);
        columnName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        columnTotalValue.setCellValueFactory(cellData -> cellData.getValue().getAssetValue());
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
