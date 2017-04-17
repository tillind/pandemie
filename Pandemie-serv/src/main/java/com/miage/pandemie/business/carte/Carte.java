package com.miage.pandemie.business.carte;

import java.util.Objects;

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
        return link.concat("/".concat(getName())).concat(".jpg");
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
    
    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false; 
        Carte tmp = (Carte) obj;
        if(tmp.getName()==null)
            return false;
        return name.equals(tmp.getName());
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(name);
    }
}
