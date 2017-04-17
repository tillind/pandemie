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

    public TauxInfection(int positionPiste, int valeur, String name) {
        super(valeur, name);
        this.positionPiste = positionPiste;
    }
      private int positionPiste;
}
