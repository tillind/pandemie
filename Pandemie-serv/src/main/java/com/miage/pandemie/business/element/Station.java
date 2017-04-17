package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.EVille;

/**
 *
 * @author alex
 */
public class Station extends Element{

    public Station(EVille position) {
        super("Station");
        this.position = position;
    }
    private EVille position;
    
    
    
}
