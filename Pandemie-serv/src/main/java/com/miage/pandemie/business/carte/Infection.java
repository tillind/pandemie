
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.Enum.ECouleur;
import com.miage.pandemie.business.Enum.EVille;

/**
 *
 * @author alex
 */
public class Infection extends Carte{
    private ECouleur couleur;
    public Infection(String path,ECouleur couleur,EVille ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
}
