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
public class MarqueurRemede extends Element{
    private boolean decouvert;
    private ECouleur couleur; 

    public MarqueurRemede(boolean decouvert,ECouleur couleur) {
        super("Marqueur remède"+couleur.name());
        this.decouvert = decouvert;
    }
    
    @Override
    public String toString(){
        return this.getName()+" [ devouvert:"+this.decouvert+",couleur:"+this.couleur.name()+"]" ;
    }
}
