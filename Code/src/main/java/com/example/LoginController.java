package com.example;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class LoginController {

    @FXML ChoiceBox choiceFile;

    @FXML Button btnNewFile;

    @FXML
    private void initialize(){
        this.testFile();
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
        if (file.getName().contains(".ncrypt")) {
            choiceFile.getItems().add(file.getName());
            }
        }
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
}
