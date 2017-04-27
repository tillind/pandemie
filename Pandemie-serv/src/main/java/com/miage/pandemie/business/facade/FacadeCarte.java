
package com.miage.pandemie.business.facade;

import com.miage.pandemie.business.enumparam.ETypeCarte;
import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.factory.carteFacto.FactoryInfection;
import com.miage.pandemie.business.factory.carteFacto.FactoryJoueur;
import com.miage.pandemie.business.factory.carteFacto.FactoryReference;
import com.miage.pandemie.business.factory.carteFacto.FactoryRole;
import com.miage.pandemie.business.factory.carteFacto.IFactoryCarte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alex
 */
public class FacadeCarte {
    private HashMap<ETypeCarte,List<Carte>> lesCartes;
    
    public FacadeCarte(){
        
        this.lesCartes = new HashMap<>();
    }
    
    public void newGame(){
        IFactoryCarte fabricInfection = new FactoryInfection();
        IFactoryCarte fabricJoueur = new FactoryJoueur();
        IFactoryCarte fabricReference = new FactoryReference();
        IFactoryCarte fabricRole = new FactoryRole();
        
        lesCartes.put(ETypeCarte.Role, fabricRole.newGame());
        lesCartes.put(ETypeCarte.Infection, fabricInfection.newGame());
        lesCartes.put(ETypeCarte.Joueur, fabricJoueur.newGame());
        lesCartes.put(ETypeCarte.Reference, fabricReference.newGame());   
    }
    
    
    public HashMap<ETypeCarte,List<Carte>> getLesCartes(){
        return this.lesCartes;
    }
    
}
