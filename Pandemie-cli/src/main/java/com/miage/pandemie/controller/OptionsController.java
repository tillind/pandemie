/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.controller;

import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.business.param.ParamCli;
import com.miage.pandemie.launch.Launch;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class OptionsController implements Initializable {
    private Launch myApps;
    
    @FXML
    private Button exitToMenu;
    @FXML
    private Button saveToMenu;
    @FXML
    private TextField nameText;
    @FXML
    private TextField ipText;
    @FXML
    private TextField portText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JsonParam param = JsonParam.getParamJson();
        ParamCli parameters = param.getParameters();
        nameText.setText(parameters.getName());
        this.ipText.setText(parameters.getIp());
        this.portText.setText(parameters.getPortChat());
        
        
    }    

    public void setLogic(Application apps){
        this.myApps = (Launch) apps;
    }
    
    
    @FXML
    private void saveToMenuHandle(ActionEvent actionEvent){
        JsonParam.writeParam(new ParamCli(ipText.getText(), portText.getText(),nameText.getText()));
        this.myApps.switchScene(EMenu.MAIN_MENU);
    }
    
    @FXML
    private void exitToMenuHandle(ActionEvent actionEvent){
        this.myApps.switchScene(EMenu.MAIN_MENU);
    }
}
