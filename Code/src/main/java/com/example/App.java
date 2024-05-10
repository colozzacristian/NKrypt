
package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.NoSuchPaddingException;

import com.example.Threads.Caller;
import com.example.Threads.CallerCaller;

import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.scene.*;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Stage primaryStage;
    private MainUiController mainController;
    private LoginController loginController;
    private FileCrypt fileCrypt;
    private Caller caller = new Caller(null);
    private CallerCaller cc= new CallerCaller(null);
    private CryptoList cl;
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NKrypt");
        inizializza();
    }

    private void inizializza() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Login.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            

            loginController=loader.getController();
            loginController.setApp(this);
            //mainController.setMainApp(this);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            //L'operatore :: si può utilizzare per fare chiamate di metodi di oggetti (si utilizza 
            primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void closeWindowEvent(WindowEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Chiusura applicazione");
        alert.setContentText("Sei sicuro di voler chiudere il programma?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();

        if(res.isPresent()) {
            if(res.get().equals(ButtonType.CANCEL)) {
                System.out.println("Cambiato idea");
                event.consume(); //ANNULLA la chiusura
            }
            else {
                if (loginController.getFile()==null) {
                    System.out.println("Non è stata effettutata ancora la login");
                    //event.consume();
                }
                else{
                    try {
                        this.fileCrypt = loginController.getFile();
                        //fileCrypt.encryption(); //chiamata della criptazione del file
                        fileCrypt.update(mainController.getCryptolist());
                        System.out.println("pippo");
                        caller.interrupt();
                        cc.interrupt();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
    }

    public void menu_crypto(CryptoList cryptoList) throws ClassNotFoundException {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/com/example/mainUI.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            


            //mainController.setMainApp(this);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            

            mainController=loader.getController();
            this.fileCrypt = loginController.getFile();



            if(loginController.isN()){
                //login new file
                cl=new CryptoList();
            }else{
                //login with file
                cl=cryptoList;
            }


            caller.start();
            caller.setMain(mainController);
            caller.setCryptoList(cl);
            cc.start();
            cc.setCryptoList(cl);
            
            mainController.setCryptolist(cl);
            mainController.setMainModel();
            primaryStage.show();
        
            //L'operatore :: si può utilizzare per fare chiamate di metodi di oggetti (si utilizza 
            //primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
