/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.controller;

import com.miage.pandemie.business.chat.ServerChat;
import com.miage.pandemie.business.chat.ServeurChatImpl;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class IndexController implements Initializable {
    private String IP_BASE;
    private final static String PORT_BASE="1099";
    
    @FXML
    private Label statusGameLbl;
    @FXML
    private Label statusChatLbl;
    @FXML
    private TextArea logServerArea;

    @FXML
    private Button startBtn;
    @FXML
    private Button restartBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private TextField ipServerField;
    @FXML
    private TextField portField;
    @FXML
    private CheckBox typeChk;
    @FXML
    private ProgressIndicator launchServerProgess;
    @FXML
    public  ListView chatList;
    
    
    private ServerChat monServeur;
    
    
    @FXML
    private void handleCheckAction(ActionEvent event) {
        if(this.typeChk.isSelected()){
            this.ipServerField.setText(IP_BASE);
            this.ipServerField.setEditable(false);
            this.portField.setText(PORT_BASE);
            this.portField.setEditable(false);
        }else{
            this.ipServerField.setText(IP_BASE);
            this.ipServerField.setEditable(true);
            this.portField.setText(PORT_BASE);
            this.portField.setEditable(true);
        }    
    }
    
    @FXML
    private void handleButtonStartAction(ActionEvent event) {
        boolean chatUp=true;
        this.launchServerProgess.setVisible(true);
        this.launchServerProgess.setVisible(false);
        monServeur = new ServerChat(ipServerField.getText(),Integer.parseInt(portField.getText()));
        
        try {
            monServeur.startServer(this);
            System.out.println("chat up");
        } catch (RemoteException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            statusChatLbl.setText("error");
            statusChatLbl.setTextFill(Color.RED);
            chatList.getItems().add(ex.getMessage());
            chatUp=false;
        }
        
        if(chatUp){
            statusChatLbl.setText("up");
            statusChatLbl.setTextFill(Color.GREEN);
        }
        
        startBtn.setDisable(true);
        restartBtn.setDisable(false);
        stopBtn.setDisable(false);
        
    }
    
    @FXML
    private void handleButtonStopAction(ActionEvent event) {
        this.monServeur.stopServer();
        statusChatLbl.setText("down");
        statusChatLbl.setTextFill(Color.BROWN);
        startBtn.setDisable(false);
        restartBtn.setDisable(true);
        stopBtn.setDisable(true);
    
    }
    @FXML
    private void handleButtonRestartAction(ActionEvent event) {
        
       
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.IP_BASE = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("com.miage.pandemie.controller.IndexController.initialize()");
        this.ipServerField.setText(IP_BASE);
        this.ipServerField.setEditable(false);
        this.portField.setText(PORT_BASE);
        this.portField.setEditable(false);
        this.typeChk.setSelected(true);
        logServerArea.setEditable(false);
        restartBtn.setDisable(true);
        stopBtn.setDisable(true);
        this.launchServerProgess.setVisible(false);
        statusChatLbl.setText("down");
        statusChatLbl.setTextFill(Color.BROWN);
        statusGameLbl.setText("down");
        statusGameLbl.setTextFill(Color.BROWN);
        
    }    
    
    
    private void printAlert(Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Careful with the next step!");
        alert.showAndWait();
    }
    
    public void addElementToChat(String str){
        this.chatList.getItems().add(str);
    }
    
}
