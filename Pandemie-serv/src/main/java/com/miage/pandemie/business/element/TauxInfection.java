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
    
      private int positionPiste;
}
