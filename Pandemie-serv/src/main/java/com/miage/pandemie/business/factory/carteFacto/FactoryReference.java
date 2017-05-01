package com.miage.pandemie.business.factory.carteFacto;

import com.miage.pandemie.business.enumparam.EDifficulter;
import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class FactoryReference implements IFactoryCarte{

    @Override
    public List<Carte> newGame() {
        List<Carte> lesCartes = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            lesCartes.add(new Reference());
        }
        return lesCartes;
    }

    @Override
    public List<Carte> savedGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
