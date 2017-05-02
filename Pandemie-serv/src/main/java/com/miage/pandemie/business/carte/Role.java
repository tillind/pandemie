package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.enumparam.ERole;

/**
 *
 * @author alex
 */
public class Role extends Carte{   
    public Role(ERole role) {
        super("/Roles",role.name());
    } 
}
