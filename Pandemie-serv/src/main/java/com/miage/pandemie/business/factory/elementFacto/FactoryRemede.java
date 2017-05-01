/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.EVille;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.Remede;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class FactoryRemede implements IFactoryElement {
    
    @Override
     public List<Element> newGame(){
        List<Element> lesRemedes = new ArrayList<>();
        for (ECouleur value : ECouleur.values()){
            lesRemedes.add(new Remede(false,value));
        }
        
        return lesRemedes;
     }
     
    @Override
    public List<Element> saveGame(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
