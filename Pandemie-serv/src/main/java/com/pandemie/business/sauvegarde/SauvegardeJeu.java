/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandemie.business.sauvegarde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.pandemie.business.jeu.Jeu;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class SauvegardeJeu extends Thread {
    private final Boolean gameStart=true;
    private final ObjectMapper mapper = new ObjectMapper();
    
    private Jeu leJeu ;
    
    public SauvegardeJeu(){
        this.leJeu = Jeu.getInstance();
    }
    
    
    public void writeSaveJson(String file){
        try {
            mapper.writeValue(new File(file+".json"), leJeu);
        } catch (IOException ex) {
            Logger.getLogger(SauvegardeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void writeSaveJson(){
        
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int seconde = c.get(Calendar.SECOND);
        
        int result = year*10000 + month*100 + day*1 ;
        int tmp =  hour*10000  + minute * 100 + seconde *1;

        this.writeSaveJson(String.valueOf(result)+" "+String.valueOf(tmp));
    }
    public void getSaveJson(){
        try {
            leJeu = mapper.readValue(new File("save.json"), Jeu.class);
        } catch (IOException ex) {
            Logger.getLogger(SauvegardeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
     public void getSaveJson(File file) throws IOException{       
        leJeu = mapper.readValue(file, Jeu.class);
    }
    @Override
    public void run(){
        try {
            while(gameStart){
                this.writeSaveJson();
                System.out.println("Sauvegarde du jeu");
                Thread.sleep(50000);
            }
        } catch (InterruptedException ex) {
                Logger.getLogger(SauvegardeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
