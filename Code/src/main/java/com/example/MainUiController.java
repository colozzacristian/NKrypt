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
import javafx.scene.image.ImageView;
import javafx.scene.*;



public class MainUiController {
 

     @FXML
    private Label labelMoney;
    @FXML
    private Label labelEUR;
    @FXML
    private Label labelCrypto;
    @FXML
    private Label labelAction;
    @FXML
    private Label labelStirato;
    @FXML
    private Label labelConnection;
    @FXML
    private Button btnReconnect;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnTransact;
    @FXML
    private Button btnMaxCoin;
    @FXML
    private Button btnMaxEur;

    @FXML
    private Button btnInspect;
    @FXML
    private Button btnAddBalance;
    @FXML
    private ImageView btnBack;

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

    @FXML
    private TextField txtEuros;
    @FXML
    private TextField txtCoins;
    //__
    private CryptoList cryptolist;
    private Crypto selected;

    private boolean isConnected=true;

    private int transactionType=0;

    
    @FXML
    private void initialize() {
        TableviewCrypto.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)-> updateSelected(newValue));
        txtCoins.setVisible(false);
        txtEuros.setVisible(false);
        btnBack.setVisible(false);
        btnTransact.setVisible(false);
        btnMaxCoin.setVisible(false);
        btnMaxEur.setVisible(false);
        labelAction.setVisible(false);
        labelStirato.setVisible(false);
        labelConnection.setVisible(false);
        btnReconnect.setVisible(false);
        labelEUR.setVisible(false);
        labelCrypto.setVisible(false);
        btnBuy.setDisable(true);
        btnSell.setDisable(true);
    }


    @FXML
    public void goBalance(){
        txtCoins.setVisible(false);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        btnMaxCoin.setVisible(false);
        btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(false);
        TableviewCrypto.setVisible(false);
        labelAction.setText("Add money ot balance");
        transactionType=1;
        
    }

    @FXML
    public void goBuy(){
        txtCoins.setVisible(true);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        btnMaxCoin.setVisible(true);
        btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(true);
        TableviewCrypto.setVisible(false);
        //mettere quale è il nome della crypto
        labelAction.setText("Buy crypto");
        transactionType=2;
        
    }
    @FXML
    public void goSell(){
        txtCoins.setVisible(true);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        btnMaxCoin.setVisible(true);
        btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(true);
        TableviewCrypto.setVisible(false);
        //mettere quale è il nome della crypto
        labelAction.setText("Sell crypto");
        transactionType=3;
        
    }
    @FXML
    public void goBack(){
        txtCoins.setVisible(false);
        txtEuros.setVisible(false);
        btnBack.setVisible(false);
        btnTransact.setVisible(false);
        btnMaxCoin.setVisible(false);
        btnMaxEur.setVisible(false);
        labelAction.setVisible(false);
        labelStirato.setVisible(false);
        labelEUR.setVisible(false);
        labelCrypto.setVisible(false);
        TableviewCrypto.setVisible(true);
        btnTransact.setDisable(false);
        transactionType=0;
    }
    @FXML
    public void maxEur(){
        
    }
    @FXML
    public void maxCoins(){
        
    }
    @FXML
    public void execTransaction(){
        
    }


    private void updateSelected(Crypto newValue){
        System.out.println("selected: "+newValue.toString());
        if(isConnected){
            btnBuy.setDisable(false);
            btnSell.setDisable(false);
        }
        selected = newValue;
    }

    void setMainModel() {
        TableviewCrypto.setItems(cryptolist.getCryptoList());
        columnName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        columnTotalValue.setCellValueFactory(cellData -> cellData.getValue().getAssetValue());
        cryptolist.getCryptoList().get(0).setQuantity(0.02);
    }

    public void refreshTable(){
        TableviewCrypto.refresh();
    }

    public void connected(){
        isConnected=true;
        if(selected!=null){
            btnBuy.setDisable(false);
            btnSell.setDisable(false);
        }
        btnReconnect.setVisible(false);
        labelConnection.setVisible(false);
    }

    public void noConnection(){
        isConnected=false;

        btnBuy.setDisable(true);
        btnSell.setDisable(true);

        if(transactionType!=1 && transactionType!=0){
            btnTransact.setDisable(true);
        }
        
        btnReconnect.setVisible(true);
        labelConnection.setVisible(true);
    }

    @FXML
    private void reconnectAttempt(){
        System.out.println("User forced reconnection");
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
    }*/
    
}
