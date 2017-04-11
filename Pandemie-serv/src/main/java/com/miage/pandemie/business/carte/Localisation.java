
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.Enum.ECouleur;
import com.miage.pandemie.business.Enum.EVille;
import com.miage.pandemie.business.Enum.EVilleBleu;
import com.miage.pandemie.business.Enum.EVilleJaune;
import com.miage.pandemie.business.Enum.EVilleNoir;
import com.miage.pandemie.business.Enum.EVilleRouge;

/**
 *
 * @author alex
 */
public class Localisation extends Joueur{
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
    
}
