/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.param;

import java.net.UnknownHostException;
import com.fasterxml.jackson.databind.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
/**
 *
 * @author alex
 */
public class JsonParam {
    private static JsonParam inst=null;
    private ObjectMapper mapper = new ObjectMapper();
    private ParamCli parameters;
    
    private JsonParam (){
        getParam();
    }
    

    
    private void getParam(){
        try {
            setParameters(mapper.readValue(new File("param.json"), ParamCli.class));
        } catch (IOException ex) {
            Logger.getLogger(JsonParam.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.newParam();
            } catch (UnknownHostException ex1) {
                Logger.getLogger(JsonParam.class.getName()).log(Level.SEVERE, null, ex1);
                Platform.exit();
            }
        }
    }
    
    private void newParam() throws UnknownHostException{
        this.setParameters(new ParamCli());
        try {
            mapper.writeValue(new File("param.json"), getParameters());
        } catch (IOException ex) {
            Logger.getLogger(JsonParam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void writeJson(){
        try {
            mapper.writeValue(new File("param.json"), getParameters());
        } catch (IOException ex) {
            Logger.getLogger(JsonParam.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public static JsonParam getParamJson(){
        if(inst==null)
        {
            inst = new JsonParam();
        }
        return inst;
    }

    public static void writeParam(ParamCli param){
       JsonParam tmp = getParamJson();
       tmp.setParameters(param);
       tmp.writeJson();
    }
    /**
     * @return the parameters
     */
    public ParamCli getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(ParamCli parameters) {
        this.parameters = parameters;
    }
}
