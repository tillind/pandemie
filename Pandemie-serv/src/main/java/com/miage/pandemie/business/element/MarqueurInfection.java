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
public abstract class MarqueurInfection extends Element {
    private int valeur;

    public MarqueurInfection(int valeur, String name) {
        super(name);
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
    

}
