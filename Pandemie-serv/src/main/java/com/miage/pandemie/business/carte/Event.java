package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.Enum.EEvent;

/**
 *
 * @author alex
 */
public class Event extends Joueur{
    public Event(EEvent event) {
        super("/Event",event.name());
    } 
}
