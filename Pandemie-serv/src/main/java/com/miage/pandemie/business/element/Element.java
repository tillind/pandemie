package com.miage.pandemie.business.element;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public abstract class Element implements Serializable {

    public Element(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
    
   
}
