/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import java.util.Optional;

import com.example.Threads.Caller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Label labelConnection;
    @FXML
    private Button btnReconnect;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnInspect;
    @FXML
    private Button btnAddBalance;

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
    private CryptoList cryptolist;
    Caller caller;

    
    @FXML
    private void initialize() {
        //tabellaPersone.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)-> mostraDettagliPersona(newValue));
    }


    @FXML
    public void addBalance(){
        
    }

    void setMainModel() {
        TableviewCrypto.setItems(cryptolist.getCryptoList());
        columnName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        columnTotalValue.setCellValueFactory(cellData -> cellData.getValue().getAssetValue());
    }

    public void refreshTable(){
        TableviewCrypto.refresh();
    }

    public void connected(){
        btnBuy.setDisable(false);
        btnSell.setDisable(false);
        btnReconnect.setVisible(false);
        labelConnection.setVisible(false);
    }

    public void noConnection(){
        btnBuy.setDisable(true);
        btnSell.setDisable(true);
        btnReconnect.setVisible(true);
        labelConnection.setVisible(true);
    }

    @FXML
    private void reconnectAttempt(){
        this.cryptolist.getCall2Action().release();
    }

    public CryptoList getCryptolist() {
        return cryptolist;
    }

    public void setCryptolist(CryptoList cryptolist) {
        this.cryptolist = cryptolist;
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
