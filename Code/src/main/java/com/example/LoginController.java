package com.example;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Boolean controllo;
    

    public FileCrypt getFilecrypt() {
      return filecrypt;
    }

    public void setApp(App app) {
      this.app = app;
    }

    @FXML
    private void initialize(){
        this.testFile();
       

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

      this.n=true;

      btnPrev.setVisible(true);
      btnEnter.setVisible(true);
      passwdFile.setVisible(true);
      passwdFileConfirm.setVisible(true);
      textName.setVisible(true);


      btnLoginFile1.setVisible(false);
      btnNewLogin.setVisible(false);
      
      }

    public void testFile(){
        try {
          File myObj = new File("filename.ncrypt");
          if (myObj.createNewFile()) {
            System.out.println("\nFile created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }}

  @FXML
  public void loginFromFile(){

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

    if(passwdFile.getText().equals("")){
      labelProblem3.setVisible(true);
      labelProblem1.setVisible(false);
      labelProblem2.setVisible(false);
      return;
    }

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
      app.menu_crypto();
    }
    else{
      //entro con file gia creato
      this.controllo = false;
      this.filecrypt = new FileCrypt(passwdFile.getText(), choiceFile.getValue().toString());
      try {
        if (filecrypt.getChiave().equals(passwdFile.getText())) app.menu_crypto();
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
        Optional<ButtonType> res = alert.showAndWait();
  }


  public boolean isN() {
    return n;
  }

  public FileCrypt getFile() {
    return this.filecrypt;
  }

  

}

