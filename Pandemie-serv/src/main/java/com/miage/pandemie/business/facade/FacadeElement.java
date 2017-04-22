package com.miage.pandemie.business.facade;

import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.enumparam.ETypeElement;
import com.miage.pandemie.business.factory.carteFacto.FactoryInfection;
import com.miage.pandemie.business.factory.elementFacto.FactoryFoyerInfection;
import com.miage.pandemie.business.factory.elementFacto.FactoryPion;
import com.miage.pandemie.business.factory.elementFacto.FactoryRemede;
import com.miage.pandemie.business.factory.elementFacto.FactoryStation;
import com.miage.pandemie.business.factory.elementFacto.FactoryTauxInfection;
import com.miage.pandemie.business.factory.elementFacto.IFactoryElement;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rémi
 */
public class FacadeElement {
      private HashMap<ETypeElement,List<Element>> lesElements;
    
    public FacadeElement(){
       this.newGame();
    }
    
    public void newGame() {
        IFactoryElement fabricFoyerInfection = new FactoryFoyerInfection();
        IFactoryElement fabricTauxInfection= new FactoryTauxInfection();
        IFactoryElement fabricPion = new FactoryPion();
        IFactoryElement fabricRemede = new FactoryRemede();
        IFactoryElement fabricStation = new FactoryStation();
        
        lesElements.put(ETypeElement.FoyerInfection, fabricFoyerInfection.newGame());
        lesElements.put(ETypeElement.TauxInfection, fabricTauxInfection.newGame());
        lesElements.put(ETypeElement.Pion, fabricPion.newGame());
        lesElements.put(ETypeElement.Remede, fabricRemede.newGame()); 
        lesElements.put(ETypeElement.Station, fabricStation.newGame());   

    }
    
    public HashMap<ETypeElement,List<Element>> getLesElements(){
        return this.lesElements;
    }
    
}
