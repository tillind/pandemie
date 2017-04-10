package com.miage.pandemie.business.carte;

/**
 *
 * @author alex
 */
public abstract class Carte {
    protected String link = "/com/miage/pandemie/image"; 
    private String name;
    
    public Carte(String path,String name) {
       this.link += path;
       this.name=name;
    }
    public String linkImg(){
        return link.concat(getName()).concat(".jpg");
    }
    public String linkImgVerso(){
        return link.concat("/Verso").concat(".jpg");
    }
     /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
