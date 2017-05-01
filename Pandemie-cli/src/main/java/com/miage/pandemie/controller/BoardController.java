package com.miage.pandemie.controller;

import com.miage.pandemie.business.chat.ClientDistantImpl;
import com.miage.pandemie.business.chat.ServeurChat;
import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.business.param.ParamCli;
import com.miage.pandemie.launch.Launch;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author alex
 */
public class BoardController implements Initializable {

    private Launch myApps;  
    private ClientDistantImpl cdi;
    private ServeurChat cd;
    private ParamCli param;
    
    
    @FXML
    private Button quitBtn;
    @FXML
    private ListView chatView;
    @FXML
    private TextField chatField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        param = JsonParam.getParamJson().getParameters();
        
        try {
            cd =(ServeurChat) Naming.lookup("chat");
                       
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Attention erreur de conexion");
             alert.setHeaderText("Nous n'avons pas pu trouvé le serveur, vérifier vos options de connexion.");
             alert.showAndWait();
             this.myApps.switchScene(EMenu.MAIN_MENU);
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        try {
            cdi = new ClientDistantImpl(this);
            cd.Connect(cdi, param.getName());
        } catch (RemoteException ex) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Attention erreur de conexion");
             alert.setHeaderText("Erreur de connexion inconnue. Veuillez ressayer.");
             alert.showAndWait();
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    private void quitHandle(ActionEvent event){    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter le jeux");
        alert.setHeaderText("Attention, vous allez quitter la partie en cours");
        alert.setContentText("Que voulez vous faire ?");
        ButtonType quitToMenu = new ButtonType("Retourner au menu");
        ButtonType quitToDesktop = new ButtonType("Quitter le programme");
        ButtonType cancel = new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(quitToMenu, quitToDesktop , cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == quitToMenu){
            this.myApps.switchScene(EMenu.MAIN_MENU);
        } else if (result.get() == quitToDesktop) {
            Platform.exit();
        }else{
            
        }
    }
    @FXML
    private void sendMessageHandle(){
        try {
            cd.Getmessage(this.chatField.getText(),this.param.getName());
            this.chatField.clear();
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void addMessageChat(String message){
        this.chatView.getItems().add(message);
    }
    
    public void setLogic(Application apps){
        this.myApps = (Launch) apps;
    }
    
    public void stopControl(){
        try {
            cd.Desconnect(cdi, this.param.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
