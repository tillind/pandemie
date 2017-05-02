package com.miage.pandemie.business.carte;

/**
 *
 * @author alex
 */
public class Reference extends Carte{
   
    public Reference() {
        super("/Reference","Rev_Recto_1");
    }  
    
   /* @Override
    public String linkImg(){
        return super.link.concat(super.getName()).concat(".jpg");
    } 
    */
    @Override
    public String linkImgVerso(){
        return ("/com/miage/pandemie/image/Reference/Rev_Verso_1").concat(".jpg");
    }
}
