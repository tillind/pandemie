
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
public class Infection extends Carte{
    private ECouleur couleur;
    public Infection(String path,ECouleur couleur,EVille ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
    public Infection(String path,ECouleur couleur,EVilleBleu ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
    public Infection(String path,ECouleur couleur,EVilleRouge ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
    public Infection(String path,ECouleur couleur,EVilleJaune ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
    public Infection(String path,ECouleur couleur,EVilleNoir ville) {
        super("/Infection"+path+"/"+couleur.name(),ville.name());  
        this.couleur = couleur;
    }
    
    @Override
    public String linkImgVerso(){
        return ("/com/miage/pandemie/image/Infection/Verso").concat(".jpg");
    }
}
