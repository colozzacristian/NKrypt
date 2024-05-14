/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import java.math.BigDecimal;

import com.example.Crypto.Crypto;
import com.example.Crypto.CryptoList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;



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
        //goes to the add balance menu
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
        //goes to the buy menu
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
        //goes to the sell menu
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
        //goes back to the tableview
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
        //set the value of the euros to the total owned value
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
        //set the value of the coins to the total owned value
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
        //syncs the value expressed in euros to the equal value in cryptos

        txtEuros.setText(StringParserCC.toNum(txtEuros.getText()));
        if(txtEuros.getText()==null || txtEuros.getText()==""){
            return;
        }
        
        
        if(transactionType!=1){
            if(Double.parseDouble(txtEuros.getText()) > cryptolist.getBalance() && transactionType==2){
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
        //syncs the value expressed in cryptos to the equal value in euros
        txtCoins.setText(StringParserCC.toNum(txtCoins.getText()));
        if(txtCoins.getText()==null || txtCoins.getText()==""){
            return;
        }
        if(transactionType!=1){
            if(Double.parseDouble(txtEuros.getText()) > selected.getQuantity() && transactionType==3){
                txtCoins.setText(String.valueOf(selected.getQuantity()));
            }
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
        //another control just to be sure
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
    private void selectedEUR(){
        System.out.println("selected eur box");
    }

    @FXML
    private void selectedCOINS(){
        System.out.println("selected coins box");
    }

    @FXML
    public void execTransaction(){
        /*
         * manages the various transactions, the switch is used to differenciate between them.
         * it also makes more controls over the user input, and re updates the prices.
         */
        sync();
        switch (transactionType) {
            case 1:
            if (txtEuros.getText()=="") {
                System.out.println("No string");
                return;
            }
                System.out.println("adding balance");
                cryptolist.setBalance(cryptolist.getBalance()+Double.parseDouble(txtEuros.getText()));
                
                
                break;
            case 2:
                if (txtEuros.getText()=="") {
                    System.out.println("No string");
                    return;
                }
                System.out.println("Buying");
                syncToCoins();
                cryptolist.getCall2Action().release();
                selected.setQuantity(selected.getQuantity()+(Double.parseDouble(txtEuros.getText()) /selected.getPrice()));
                System.out.println("new owned value: "+ selected.getQuantity());
                cryptolist.setBalance(cryptolist.getBalance()-Double.parseDouble(txtEuros.getText()));                
                break;
            case 3:
                if (txtCoins.getText()=="") {
                    System.out.println("No string");
                    return;
                }
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
        /*
         * manages the selection of the cryptos from the table
         */
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
        labelMoney.setText("€"+cryptolist.getBalance());
        
    }

    public void refreshTable(){
        //refreshes the tableview
        TableviewCrypto.refresh();
    }

    public void connected(){
         /*
         * reenables the functions disabled in "noConnection" when the app notices that we are online
         */
        isConnected=true;
        if(selected!=null){
            btnBuy.setDisable(false);
            btnSell.setDisable(false);
        }
        btnReconnect.setVisible(false);
        labelConnection.setVisible(false);
    }

    public void noConnection(){
        /*
         * disables some function when tha app notices that there is no connection
         */
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

    @SuppressWarnings("exports")
    public CryptoList getCryptolist() {
        return cryptolist;
    }
    @SuppressWarnings("exports")
    public void setCryptolist(CryptoList cryptolist) {
        this.cryptolist = cryptolist;
    }

    
}
