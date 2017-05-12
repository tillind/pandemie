/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.ECouleur;

/**
 *
 * @author Remi
 */
public class Remede extends Element{

    /**
     * @return the decouvert
     */
    public boolean isDecouvert() {
        return decouvert;
    }

    /**
     * @param decouvert the decouvert to set
     */
    public void setDecouvert(boolean decouvert) {
        this.decouvert = decouvert;
    }
    private boolean decouvert;
    private ECouleur couleur; 

    public Remede(boolean decouvert,ECouleur couleur) {
        super("Marqueur remède "+couleur.name());
        this.decouvert = decouvert;
        this.couleur = couleur;
    }
    
    public boolean getDecouvert(){
        return this.isDecouvert();
    }
    
   public ECouleur getCouleur(){
        return this.couleur;
    }
   
   @Override
   public String toString(){
       return "Remede [ name : "+this.getName()+", decouvert :"+this.decouvert+",couleur:"+this.couleur+"]";
   }
    
}
