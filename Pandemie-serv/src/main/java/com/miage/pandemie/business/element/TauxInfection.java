/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.element;

/**
 *
 * @author Remi
 */
public class TauxInfection extends MarqueurInfection{

    public TauxInfection(int positionPiste, int valeur) {
        super(valeur, "Marqueur de taux d'infection");
        this.positionPiste = positionPiste;
    }

    public int getPositionPiste() {
        return positionPiste;
    }

    public void setPositionPiste(int positionPiste) {
        this.positionPiste = positionPiste;
    }
    
    public void augementerPositionPiste() {
        this.positionPiste ++;
        if(positionPiste == 4){
            this.setValeur(3);
        }
        
        else if(positionPiste == 6){
         this.setValeur(4);
        }
    }
    
      private int positionPiste;
}
