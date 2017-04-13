package com.miage.pandemie.business.element;

import com.miage.pandemie.business.Enum.EVille;

/**
 *
 * @author alex
 */
public class Station extends Element{

    public Station(EVille position, String name) {
        super(name);
        this.position = position;
    }
    private EVille position;
    
    
    
}
