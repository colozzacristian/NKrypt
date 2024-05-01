package com.example;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {

  private boolean n;
  private App app;


    @FXML ChoiceBox choiceFile;


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

      App change = new App();

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
    btnDelete.setVisible(true);
    btnPrev.setVisible(true);
    btnEnter.setVisible(true);
    passwdFile.setVisible(true);


    btnLoginFile1.setVisible(false);
    btnNewLogin.setVisible(false);


  }




  @FXML
  public void loginFile() {
    if (choiceFile.getValue() == null) {
      //chiamare alert
    }
    else {
      this.filecrypt = new FileCrypt(passwdFile.getText(), (String)choiceFile.getValue());
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

    btnLoginFile1.setVisible(true);
    btnNewLogin.setVisible(true);


  }

  @FXML
  public void enter(){

    if(passwdFile.getText().equals("")){
      labelProblem3.setVisible(true);
      labelProblem1.setVisible(false);
      labelProblem2.setVisible(false);
      return;
    }

    if(this.n){

    File folder = new File("./");
    File[] listOfFiles = folder.listFiles();

    for (File file : listOfFiles) {
    if (file.getName().contains(".ncrypt") && file.getName().contains(textName.getText())) {
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
    app.menu_crypto();

    }else{
      app.menu_crypto();
    //passare a mainUI.fxml
    }

  }


}

