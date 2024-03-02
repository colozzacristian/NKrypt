/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MainUiController {
 
//importo i vari tasti ecc dal file mainUI.fxml
    @FXML private CheckBox checkEnable;
    @FXML private CheckBox checkAdd;

    @FXML private TextField text1;
    @FXML private TextField text2;
    
    @FXML
    private void initialize(){
       //check1.setVisible(false);
    }
}