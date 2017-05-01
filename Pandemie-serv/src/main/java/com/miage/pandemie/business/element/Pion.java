package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.ECouleurPion;
import com.miage.pandemie.business.enumparam.EVille;

/**
 *
 * @author alex
 */
public class Pion extends Element {

    private String couleur;
    private Ville position;

    public Pion(ECouleurPion couleur) {
        super("Pion de couleur" + couleur.name());
        this.couleur = couleur.name();
    }

    public String getCouleur() {
        return couleur;
    }

    public Ville getPosition() {
        return position;
    }

    public void setVille(Ville v){
        this.position = v;
    }
    
}
