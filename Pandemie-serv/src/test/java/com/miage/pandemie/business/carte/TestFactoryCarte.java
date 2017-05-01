/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.factory.carteFacto.FactoryInfection;
import com.miage.pandemie.business.factory.carteFacto.FactoryJoueur;
import com.miage.pandemie.business.factory.carteFacto.FactoryReference;
import com.miage.pandemie.business.factory.carteFacto.FactoryRole;
import com.miage.pandemie.business.factory.carteFacto.IFactoryCarte;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alex
 */

public class TestFactoryCarte {
    
    private IFactoryCarte lesCartesInfections;
    private IFactoryCarte lesCartesJoueur;
    private IFactoryCarte lesCartesReference;
    private IFactoryCarte lesCartesRole;
    
    private List<Carte> listeInfections;
    private List<Carte> listeReference;
    private List<Carte> listeRole;
    private List<Carte> listeJoueur;
    
    public TestFactoryCarte() {
        lesCartesInfections = new FactoryInfection();
        lesCartesJoueur = new FactoryJoueur();
        lesCartesReference=new FactoryReference();
        lesCartesRole=new FactoryRole();
        
        listeInfections =  lesCartesInfections.newGame();
        listeReference =  lesCartesReference.newGame();
        listeRole =  lesCartesRole.newGame();
        listeJoueur =  lesCartesJoueur.newGame();
    }
        
    @Test
    public void testNbCarteInfection() {
        int nbTmp =  listeInfections.size();
        assertEquals(nbTmp, 48);
    }
    
    @Test
    public void testNbCarteJoueur() {
        int nbTmp =  listeJoueur.size();
        assertEquals(nbTmp, 59);
    }
    
    @Test
    public void testNbCarteRole() {
        int nbTmp =  listeRole.size();
        assertEquals(nbTmp,5);
    }
    
    @Test
    public void testNbCarteRef() {
        int nbTmp =  listeReference.size();
        assertEquals(nbTmp,4);
    }
    
     @Test
    public void testDoublonCarteInfection() {
        for (int i = 0; i < listeInfections.size(); i++) {
            for (int j = i+1; j < listeInfections.size(); j++) {           
                assertNotEquals(listeInfections.get(i),listeInfections.get(j));
            }         
        }
    }
    
     @Test
    public void testDoublonCarteRole() {
        for (int i = 0; i < listeRole.size(); i++) {
            for (int j = i+1; j < listeRole.size(); j++) {           
                assertNotEquals(listeRole.get(i),listeRole.get(j));
            }         
        }
    }
    
    @Test
    public void testDoublonCarteJoueurLoc() {
        for (int i = 11; i < listeJoueur.size(); i++) {
            for (int j = i+1; j < listeJoueur.size(); j++) {           
                assertNotEquals(listeJoueur.get(i),listeJoueur.get(j));
            }         
        }
    }
    
    @Test
    public void testDoublonCarteJoueurEvent() {
        for (int i = 6; i < 11; i++) {     
            for (int j = i+1; j < 11; j++) {  
                assertNotEquals(listeJoueur.get(i),listeJoueur.get(j));
            }         
        }
    }
}
