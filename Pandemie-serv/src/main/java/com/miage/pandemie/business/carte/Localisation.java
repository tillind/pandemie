
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.Enum.ECouleur;
import com.miage.pandemie.business.Enum.EVille;

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
    
}
