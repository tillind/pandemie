package com.miage.pandemie.business.element;


/**
 *
 * @author alex
 */
public class Station extends Element{

    public Station(Ville position) {
        super("Station");
        this.position = position;
    }

    private Ville position;
    
     public Ville getPosition() {
        return position;
    }

    public void setPosition(Ville position) {
        this.position = position;
    }
    
    
}
