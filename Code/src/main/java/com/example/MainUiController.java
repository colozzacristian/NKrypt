/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import java.math.BigDecimal;
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
        System.out.println("MainUi initialited\n----------------------------------------------------------------------------\nnote that if your connection is slow you will encounter quite some problems\n----------------------------------------------------------------------------");
    }


    @FXML
    public void goBalance(){
        System.out.println("going to balance menu");
        txtCoins.setVisible(false);
        txtEuros.setVisible(true);
        btnBack.setVisible(true);
        btnTransact.setVisible(true);
        //btnMaxEur.setVisible(true);
        labelAction.setVisible(true);
        labelEUR.setVisible(true);
        labelCrypto.setVisible(false);
        TableviewCrypto.setVisible(false);
        btnMaxCoin.setVisible(false);
        btnMaxEur.setVisible(false);
        labelAction.setText("Add money ot balance");
        transactionType=1;
        System.out.println("tt: "+transactionType);
        txtCoins.setText("");
        txtEuros.setText("");
        
    }

    @FXML
    public void goBuy(){
        System.out.println("going to buy menu");
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
        System.out.println("tt: "+transactionType);
        txtCoins.setText("");
        txtEuros.setText("");
        
    }
    @FXML
    public void goSell(){
        System.out.println("going to sell menu");
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
        System.out.println("tt: "+transactionType);
        txtCoins.setText("");
        txtEuros.setText("");
        
    }
    @FXML
    public void goBack(){
        System.out.println("going back to table view");
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
        System.out.println("Setting txtEuros to use all the money in the balance");
        txtEuros.setText(new BigDecimal(cryptolist.getBalance()).toPlainString());
        txtCoins.setText(
            new BigDecimal(
                    Double.parseDouble(txtEuros.getText())/selected.getPrice()
                    ).toPlainString()
            );
        
    }

    @FXML
    public void maxCoin(){
        System.out.println("Setting txtCoins to use all the crypto of this type that the user owns");
        txtCoins.setText(new BigDecimal(selected.getQuantity()).toPlainString());
        txtEuros.setText(
            new BigDecimal(
                    selected.getQuantity()*selected.getPrice()
                    ).toPlainString()
            );
        
    }

    @FXML
    private void syncToCoins(){
        txtEuros.setText(StringParserCC.toNum(txtEuros.getText()));
        if(txtEuros.getText()==null || txtEuros.getText()==""){
            return;
        }
        
        
        if(transactionType!=1){
            if(Double.parseDouble(txtEuros.getText()) > cryptolist.getBalance()){
                txtEuros.setText(String.valueOf(cryptolist.getBalance()));
            }
            txtEuros.setText(txtEuros.getText());
            txtCoins.setText(
                new BigDecimal(
                    Double.parseDouble(txtEuros.getText())/selected.getPrice()
                    ).toPlainString()
                
            );
            if(transactionType==3){syncToEur();}
        }
    }

    @FXML
    private void syncToEur(){
        txtCoins.setText(StringParserCC.toNum(txtCoins.getText()));
        if(txtCoins.getText()==null || txtCoins.getText()==""){
            return;
        }
        if(transactionType!=1){
            txtEuros.setText(
                new BigDecimal(
                    Double.parseDouble(txtCoins.getText())*selected.getPrice()
                    ).toPlainString()
            );
            if(transactionType==2){
                syncToCoins();
            }
        }
    }

    @FXML
    private void sync(){
        switch (transactionType) {
            case 2:
                syncToCoins();
                break;
            case 3:
                syncToEur();
                break;
            default:
                break;
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
        this.refreshTable();
        labelMoney.setText("€"+String.valueOf(cryptolist.getBalance()
                    ));
                    System.out.println("new balance: "+cryptolist.getBalance());
        goBack();
    }


    private void updateSelected(Crypto newValue){
        System.out.println("selected: "+newValue.toString());
        //if the user is not connected we shall not allow nor buying nor selling
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
