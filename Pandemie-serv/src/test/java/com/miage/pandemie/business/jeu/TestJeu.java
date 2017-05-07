/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Joueur;
import com.miage.pandemie.business.carte.Localisation;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeElement;
import java.rmi.RemoteException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
/**
 *
 * @author Remi
 */
public class TestJeu {
    

    
    Jeu jeu = Jeu.getInstance();    
    

    
    @Test
    public void TestInitPartie()throws Exception{
       jeu.addJoueur("Joueur1"); 
       jeu.addJoueur("Joueur2"); 
       jeu.addJoueur("Joueur3"); 
       jeu.addJoueur("Joueur4"); 
        
        jeu.InitialiseNouvellePartie();
        assertEquals(6,jeu.getLesMains().get("Joueur4").size());

    }
    
   @Test
    public void TestNbAction(){
        assertEquals(4,jeu.getLeNombreAction().get("Joueur1").intValue());
    }
    
    
    @Test
    public void TestInfectionPropagation(){
        
        Ville villeTest2 = new Ville("TestVille2","rouge");
        Ville villeTest3 = new Ville("TestVille3","rouge");
        
        villeTest2.ajouterVoisinage(villeTest3);
        
        for (int i = 0; i<8 ; i++)
        {
            villeTest2.setPropagation(true);
            jeu.infecterVille(villeTest2, ECouleur.Bleu);
            jeu.enleverMarqueurPropagation(villeTest2);
        }

        assertEquals(3,villeTest3.getInfection().get(ECouleur.Bleu).size());

    }
    
    
    @Test
    public void TestConduire(){
        jeu.conduire("Joueur1",jeu.getVille("Washington"));
        assertEquals("Washington",jeu.getLesPions().get("Joueur1").getPosition().getName());
        
    }


    
    @Test
    public void TestGetCarte(){
       assertEquals("Moscou", jeu.getCarteJoueur("Moscou").getName()); 
    }
    

    
    @Test
    public void TestFinTour(){
        jeu.finDeTour("Joueur1");
        assertEquals(8,jeu.getLesMains().get("joueur1").size());
    }
   
}
