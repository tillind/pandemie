/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.TauxInfection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class FactoryTauxInfection implements IFactoryElement{
    
    @Override
     public List<Element> newGame(){
              List<Element> LeTauxInfection = new ArrayList<>();
              LeTauxInfection.add(new TauxInfection(1,2));
              return LeTauxInfection;
     }
    
     @Override
     public List<Element> saveGame(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
}
