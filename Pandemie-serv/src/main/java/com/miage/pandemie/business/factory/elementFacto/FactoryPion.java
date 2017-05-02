/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.enumparam.ECouleurPion;
import com.miage.pandemie.business.enumparam.EVille;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.Pion;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.EVilleBleu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class FactoryPion  implements IFactoryElement {
    
    @Override
    public List<Element> newGame(){
        List<Element> lesPions = new ArrayList<>();
        for (ECouleurPion value : ECouleurPion.values()){
            lesPions.add(new Pion(value));
        }
        return lesPions;
    }
    
    @Override
    public List<Element> saveGame(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
