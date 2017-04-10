package com.miage.pandemie.business.carte;

/**
 *
 * @author alex
 */
public class Reference extends Carte{
   
    public Reference(String path) {
        super("/reference"+path,"Rev_Recto_1");
    }  
    
    @Override
    public String linkImg(){
        return super.link.concat(super.getName()).concat(".jpg");
    } 
}
