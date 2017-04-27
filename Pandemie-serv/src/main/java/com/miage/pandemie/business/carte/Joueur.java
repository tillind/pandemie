package com.miage.pandemie.business.carte;

/**
 *
 * @author alex
 */
public abstract class Joueur extends Carte{
    public Joueur(String path,String name) {
        super("/Joueur"+path,name);
    }
    
      @Override
    public String linkImgVerso(){
        return ("/com/miage/pandemie/image/Joueur/Verso").concat(".jpg");
    }
}
