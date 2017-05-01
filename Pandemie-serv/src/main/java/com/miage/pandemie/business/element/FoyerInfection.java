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
public class FoyerInfection extends MarqueurInfection {
    
    public FoyerInfection (int valeur)
    {
        super(valeur,"Marqueur Foyer d'infection");
    }
    
    public void augmenterInfection(){
        
        this.setValeur(this.getValeur()+1);
    }
}
