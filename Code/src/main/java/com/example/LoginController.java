package com.example;

import java.io.File;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


import com.example.Crypto.CryptoList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {

  private boolean n;
  private App app;


    @FXML ChoiceBox<String> choiceFile;


    @FXML Button btnNewLogin;
    @FXML ImageView btnDelete;
    @FXML Button btnLoginFile1;
    @FXML ImageView btnPrev;
    @FXML ImageView btnEnter;
    @FXML PasswordField passwdFile;
    @FXML PasswordField passwdFileConfirm;
    @FXML TextField textName;
    @FXML Label labelProblem1;
    @FXML Label labelProblem2;
    @FXML Label labelProblem3;



    private FileCrypt filecrypt;


    CryptoList cl;
    

    public FileCrypt getFilecrypt() {
      return filecrypt;
    }

    public void setApp(App app) {
      this.app = app;
    }

    @FXML
    private void initialize(){

        choiceFile.setVisible(false);
        btnDelete.setVisible(false);
        btnPrev.setVisible(false);
        btnEnter.setVisible(false);
        passwdFile.setVisible(false);
        passwdFileConfirm.setVisible(false);
        textName.setVisible(false);
        labelProblem1.setVisible(false);
        labelProblem2.setVisible(false);
        labelProblem3.setVisible(false);




    }

    public void newLogin() {
      //goes to the new login menu

      this.n=true;

      btnPrev.setVisible(true);
      btnEnter.setVisible(true);
      passwdFile.setVisible(true);
      passwdFileConfirm.setVisible(true);
      textName.setVisible(true);


      btnLoginFile1.setVisible(false);
      btnNewLogin.setVisible(false);
      
      }

  @FXML
  public void loginFromFile(){
      //goes to the login from file menu


    int i=0;

    File folder = new File("./");
    File[] listOfFiles = folder.listFiles();

    this.n=false;
   
    while(i<choiceFile.getItems().size()){
      choiceFile.getItems().remove(i);
      System.out.println("removed object");
    }

    

    for (File file : listOfFiles) {
      if (file.getName().contains(".ncrypt")) {
        choiceFile.getItems().add(file.getName());
      }
    }

    choiceFile.setVisible(true);
    //btnDelete.setVisible(true);
    btnPrev.setVisible(true);
    btnEnter.setVisible(true);
    passwdFile.setVisible(true);


    btnLoginFile1.setVisible(false);
    btnNewLogin.setVisible(false);


  }




  @FXML
  public void loginFile() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    if (choiceFile.getValue() == null) {
      //chiamare alert
    }
    else {
      this.filecrypt = new FileCrypt(passwdFile.getText(), (String)choiceFile.getAccessibleText());
    }
  }

  @FXML
  public void toMainMenu(){
      //goes to the choice of the login type

    choiceFile.setVisible(false);
    btnDelete.setVisible(false);
    btnPrev.setVisible(false);
    btnEnter.setVisible(false);
    passwdFile.setVisible(false);
    passwdFileConfirm.setVisible(false);
    textName.setVisible(false);
    labelProblem1.setVisible(false);
    labelProblem2.setVisible(false);
    labelProblem3.setVisible(false);
    passwdFile.setText("");
    passwdFileConfirm.setText("");
    textName.setText("");

    btnLoginFile1.setVisible(true);
    btnNewLogin.setVisible(true);


  }

  @FXML
  public void enter() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ClassNotFoundException{
      //manages the login etither thru a new one or an old one

    if(passwdFile.getText().equals("")){
      labelProblem3.setVisible(true);
      labelProblem1.setVisible(false);
      labelProblem2.setVisible(false);
      return;
    }
    
    CryptoList cl = new CryptoList(true);

    if(this.n) {

      File folder = new File("./");
      File[] listOfFiles = folder.listFiles();

      for (File file : listOfFiles) {
        if (file.getName().contains(".ncrypt") && file.getName().equals(textName.getText()+".ncrypt")) {
          labelProblem1.setVisible(true);
          labelProblem2.setVisible(false);
          labelProblem3.setVisible(false);
          return;
        }
      }
      if(! passwdFile.getText().equals(passwdFileConfirm.getText())){
        labelProblem2.setVisible(true);
        labelProblem1.setVisible(false);
        labelProblem3.setVisible(false);
        return;
      }
      //file nuovo
      this.filecrypt = new FileCrypt(passwdFile.getText(), textName.getText()+".ncrypt");
      app.menu_crypto(cl);
    }
    else{
      if(choiceFile.getValue()==null){
        labelProblem3.setVisible(false);
        labelProblem1.setVisible(true);
        labelProblem2.setVisible(false);
        System.out.println("no file selected");
        return;
      }
  
      //entro con file gia creato
      this.filecrypt = new FileCrypt(passwdFile.getText(), choiceFile.getValue().toString());
      try {
        if (this.filecrypt.readData(cl)) app.menu_crypto(cl);
        else wrong_passwd();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public void wrong_passwd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setContentText("Password errata");
        alert.showAndWait();
  }


  public boolean isN() {
    return n;
  }

  public FileCrypt getFile() {
    return this.filecrypt;
  }

  

}

