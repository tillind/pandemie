package com.miage.pandemie.business.factory.carteFacto;

import com.miage.pandemie.business.enumparam.EDifficulter;
import com.miage.pandemie.business.enumparam.ERole;
import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class FactoryRole implements IFactoryCarte{

    @Override
    public List<Carte> newGame() {
        List<Carte> lesRoles = new ArrayList<>();
        
        for (ERole value : ERole.values()) {
            lesRoles.add(new Role(value));
        }
        
        return lesRoles;
    }

    @Override
    public List<Carte> savedGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
    
}
