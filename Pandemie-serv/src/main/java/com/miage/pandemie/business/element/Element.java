package com.miage.pandemie.business.element;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public abstract class Element {

    public Element(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
    
    @Override
    public String toString(){
        return " Name [";
    }
}
