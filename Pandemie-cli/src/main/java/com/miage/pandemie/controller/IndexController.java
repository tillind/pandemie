/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.controller;

import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.launch.Launch;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class IndexController implements Initializable {
    
    private Launch myApps;

    @FXML
    private Label label;
    @FXML
    private Button fullScreenBtn;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button optionsBtn;
    @FXML 
    private Button quitBtn;
    
    private boolean fullscreen = false;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void fullScreenHandle(ActionEvent event) {
       Stage stage = (Stage) fullScreenBtn.getScene().getWindow();
       stage.setFullScreen(true);
       if (!fullscreen){
           stage.setFullScreen(true);
           System.out.println("mode plein écran");
           this.fullScreenBtn.setText("Quitter le mode plein écran");
           this.fullscreen = true;
       }else{
           stage.setFullScreen(false);
           System.out.println("mode window");
           this.fullScreenBtn.setText("Mode plein écran");
           this.fullscreen = false;
       }
    }
    
    @FXML
    private void startGameHandle(ActionEvent event){
        this.myApps.switchScene(EMenu.NEW_GAME);
    }
    @FXML
    private void optionsGameHandle(ActionEvent event){
        this.myApps.switchScene(EMenu.OPTIONS);
    }
    
    public void setLogic(Application apps){
        this.myApps = (Launch) apps;
    }
    
    @FXML
    public void handleClickQuit(ActionEvent event){
          Platform.exit();
    }
    
}
