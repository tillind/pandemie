package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.Enum.ERole;

/**
 *
 * @author alex
 */
public class Role extends Carte{   
    public Role(String path,ERole role) {
        super("/Roles"+path,role.name());
    } 
}
