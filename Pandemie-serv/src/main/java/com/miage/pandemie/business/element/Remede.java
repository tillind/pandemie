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
    private boolean decouvert;
    private String couleur; 

    public Remede(boolean decouvert,ECouleur couleur) {
        super("Marqueur remède"+couleur.name());
        this.decouvert = decouvert;
        this.couleur = couleur.name();
    }
    
    
}
