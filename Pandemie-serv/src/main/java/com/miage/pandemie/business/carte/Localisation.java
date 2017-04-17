
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.EVille;
import com.miage.pandemie.business.enumparam.EVilleBleu;
import com.miage.pandemie.business.enumparam.EVilleJaune;
import com.miage.pandemie.business.enumparam.EVilleNoir;
import com.miage.pandemie.business.enumparam.EVilleRouge;
import java.util.Objects;

/**
 *
 * @author alex
 */
public class Localisation extends Joueur{

    /**
     * @return the couleur
     */
    public ECouleur getCouleur() {
        return couleur;
    }
    private ECouleur couleur;
    public Localisation(String path,ECouleur couleur,EVille ville) {
        super("/Localisation"+path+"/"+couleur.name(),ville.name());
        this.couleur = couleur;
    }
    
    public Localisation(String path,ECouleur couleur,EVilleBleu ville) {
        super("/Localisation"+path+"/"+couleur.name(),ville.name());
        this.couleur = couleur;
    }
    public Localisation(String path,ECouleur couleur,EVilleJaune ville) {
        super("/Localisation"+path+"/"+couleur.name(),ville.name());
        this.couleur = couleur;
    }
    public Localisation(String path,ECouleur couleur,EVilleRouge ville) {
        super("/Localisation"+path+"/"+couleur.name(),ville.name());
        this.couleur = couleur;
    }
    public Localisation(String path,ECouleur couleur,EVilleNoir ville) {
        super("/Localisation"+path+"/"+couleur.name(),ville.name());
        this.couleur = couleur;
    }
    
   @Override
    public boolean equals(Object obj){
        
        if(super.equals(obj)){
            Infection tmp =(Infection) obj;
            if(this.getCouleur().equals(tmp.getCouleur())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.getCouleur());
        return hash;
    }
    
}
