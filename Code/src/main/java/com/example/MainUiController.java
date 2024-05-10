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
    private Button btnMaxEur;
    @FXML
    private Button btnMaxCoin;

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

    private boolean isConnected=false;

    private int transactionType=0;

    
    @FXML
    private void initialize() {
        TableviewCrypto.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)-> updateSelected(newValue));
        txtCoins.setVisible(false);
        txtEuros.setVisible(false);
        btnBack.setVisible(false);
        btnTransact.setVisible(false);
        btnMaxEur.setVisible(false);
        labelAction.setVisible(false);
        labelConnection.setVisible(false);
        btnReconnect.setVisible(false);
        labelEUR.setVisible(false);
        labelCrypto.setVisible(false);
        btnBuy.setDisable(true);
        btnSell.setDisable(true);
        btnMaxCoin.setVisible(false);
    }


    @FXML
    public void goBalance(){
        txtCoins.setVisible(false);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        //btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(false);
        TableviewCrypto.setVisible(false);
        labelAction.setText("Add money ot balance");
        transactionType=1;
        txtCoins.setText("");
        txtEuros.setText("");
        
    }

    @FXML
    public void goBuy(){
        btnMaxCoin.setVisible(false);
        txtCoins.setVisible(true);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(true);
        TableviewCrypto.setVisible(false);
        //mettere quale è il nome della crypto
        labelAction.setText("Buying "+selected.getName());
        transactionType=2;
        txtCoins.setText("");
        txtEuros.setText("");
        
    }
    @FXML
    public void goSell(){
        txtCoins.setVisible(true);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        btnMaxCoin.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(true);
        TableviewCrypto.setVisible(false);
        btnMaxEur.setVisible(false);
        //mettere quale è il nome della crypto
        labelAction.setText("Selling "+selected.getName());
        transactionType=3;
        txtCoins.setText("");
        txtEuros.setText("");
        
    }
    @FXML
    public void goBack(){
        txtCoins.setVisible(false);
        txtEuros.setVisible(false);
        btnBack.setVisible(false);
        btnTransact.setVisible(false);
        btnMaxEur.setVisible(false);
        btnMaxCoin.setVisible(false);
        labelAction.setVisible(false);
        labelEUR.setVisible(false);
        labelCrypto.setVisible(false);
        TableviewCrypto.setVisible(true);
        btnTransact.setDisable(false);
        transactionType=0;
        txtCoins.setText("");
        txtEuros.setText("");
    }
    @FXML
    public void maxEur(){
        txtEuros.setText(String.valueOf(cryptolist.getBalance()));
        txtCoins.setText(
                String.valueOf(
                    Double.parseDouble(txtEuros.getText())/selected.getPrice()
                    )
            );
        
    }

    @FXML
    public void maxCoin(){
        txtCoins.setText(String.valueOf(selected.getQuantity()));
        txtEuros.setText(
                String.valueOf(
                    selected.getQuantity()*selected.getPrice()
                    )
            );
        
    }

    @FXML
    private void syncToCoins(){
        String aux = StringParserCC.toNum(txtEuros.getText());
        if(aux==null || aux==""){
            System.out.println("no string when syncing to Coins");
            return;
        }
        
        
        if(transactionType!=1){
            if(Double.parseDouble(aux) > cryptolist.getBalance()){
                aux = String.valueOf(cryptolist.getBalance());
            }
            txtEuros.setText(aux);
            txtCoins.setText(
                String.valueOf(
                    Double.parseDouble(aux)/selected.getPrice()
                    )
            );
        }
    }

    @FXML
    private void syncToEur(){
        if(txtCoins.getText()==null || txtCoins.getText()==""){
            System.out.println("no string when syncinc to Eur");
            return;
        }
        txtCoins.setText(StringParserCC.toNum(txtCoins.getText()));
        if(transactionType!=1){
            txtEuros.setText(
                String.valueOf(
                    Double.parseDouble(txtCoins.getText())*selected.getPrice()
                    )
            );
        }
    }

    @FXML
    public void execTransaction(){
        String eur=txtEuros.getText();
        String crypt=txtCoins.getText();
        switch (transactionType) {
            case 1:
                System.out.println("adding balance");
                cryptolist.setBalance(cryptolist.getBalance()+Double.parseDouble(txtEuros.getText()));
                
                
                break;
            case 2:
                System.out.println("Buying");
                syncToCoins();
                cryptolist.getCall2Action().release();
                selected.setQuantity(selected.getQuantity()+(Double.parseDouble(txtEuros.getText()) /selected.getPrice()));
                System.out.println("new owned value: "+ selected.getQuantity());
                cryptolist.setBalance(cryptolist.getBalance()-Double.parseDouble(txtEuros.getText()));                
                break;
            case 3:
                System.out.println("Selling");
                syncToEur();
                cryptolist.getCall2Action().release();
                
                selected.setQuantity(selected.getQuantity()-Double.parseDouble(txtCoins.getText()));
                System.out.println("new owned value: "+ selected.getQuantity());
                cryptolist.setBalance(cryptolist.getBalance()+Double.parseDouble(txtCoins.getText())*selected.getPrice());             
                break;
        
            default:
                break;
        }
        labelMoney.setText("€"+String.valueOf(cryptolist.getBalance()
                    ));
                    System.out.println("new balance: "+cryptolist.getBalance());
        goBack();
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
