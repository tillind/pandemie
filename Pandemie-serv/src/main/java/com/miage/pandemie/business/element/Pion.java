package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.ECouleurPion;

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
        position = null;
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
    
    @Override
    public String toString(){
        if(position == null){
            return " Pion [ name : "+this.getName()+",  couleur : "+this.couleur+", position :  ]";
        }else{
            return " Pion [ name : "+this.getName()+",  couleur : "+this.couleur+", position : "+position.toString()+" ]";
        }
    }
    
}
