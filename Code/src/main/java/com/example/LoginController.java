package com.example;

import java.io.File;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurve;

public class LoginController {


    @FXML ChoiceBox choiceFile;


    @FXML Button btnNewLogin;
    @FXML ImageView btnDelete;
    @FXML Button btnLoginFile1;
    @FXML ImageView btnPrev;
    @FXML ImageView btnEnter;
    @FXML PasswordField passwdFile;







    @FXML
    private void initialize(){
        this.testFile();
       

        choiceFile.setVisible(false);
        btnDelete.setVisible(false);
        btnPrev.setVisible(false);
        btnEnter.setVisible(false);
        passwdFile.setVisible(false);

    }

    public void testFile() {
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
        }
      }

  @FXML
  public void loginFromFile(){

    int i=0;
   
    while(i<choiceFile.getItems().size()){
      choiceFile.getItems().remove(i);
      System.out.println("removed object");
    }

    File folder = new File("./");
    File[] listOfFiles = folder.listFiles();

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
  public void toMainMenu(){

    choiceFile.setVisible(false);
    btnDelete.setVisible(false);
    btnPrev.setVisible(false);
    btnEnter.setVisible(false);
    passwdFile.setVisible(false);


    btnLoginFile1.setVisible(true);
    btnNewLogin.setVisible(true);


  }


}

