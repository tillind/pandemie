/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.element.CubeMaladie;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.enumparam.ECouleur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class FactoryCubeMaladie implements IFactoryElement{

    @Override
    public List<Element> newGame() {
        
        List<Element> ListeCube = new ArrayList<>();
        for(ECouleur couleur : ECouleur.values())
        {
            for (int cpt = 0; cpt < 24 ; cpt ++)
            {
                ListeCube.add(new CubeMaladie(couleur));
            }
        }
        return ListeCube;
    }

    @Override
    public List<Element> saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
