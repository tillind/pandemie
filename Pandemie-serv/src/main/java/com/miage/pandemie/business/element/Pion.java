package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.ECouleurPion;
import com.miage.pandemie.business.enumparam.EVille;

/**
 *
 * @author alex
 */
public class Pion extends Element{
    private ECouleurPion couleur;
    private EVille position;

    public Pion(ECouleurPion couleur, EVille position) {
        super("Pion de couleur"+couleur.name());
        this.couleur = couleur;
        this.position = position;
    }
    
    
    
    
}
