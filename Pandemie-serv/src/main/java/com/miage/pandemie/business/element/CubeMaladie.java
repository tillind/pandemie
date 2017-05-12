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
public class CubeMaladie extends Element {
    
    ECouleur couleur; 
    public CubeMaladie( ECouleur c) {
        super("Cube maladie "+c.name());
        this.couleur = c;
    }

    public ECouleur getCouleur() {
        return couleur;
    }
    
    @Override
    public String toString(){
        return " CubeMaladie [ name : "+this.getName()+" , couleur : "+this.couleur+" ]";
    }
    
}
