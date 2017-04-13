package com.miage.pandemie.business.element;

import com.miage.pandemie.business.Enum.ECouleurPion;
import com.miage.pandemie.business.Enum.EVille;

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
