/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeCarte;
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
    

    
    Jeu jeu = new Jeu(initJoueur());
    
    
    public  ArrayList<String> initJoueur(){
        ArrayList<String> joueurs = new ArrayList<>();
            joueurs.add("User1");
            joueurs.add("User2");
            joueurs.add("User3");
        return joueurs;    
    }
    
    public  TestJeu() throws Exception{

        jeu.InitialiseNouvellePartie();

    }
    
    @Test
    public void TestInfection(){
        
        Ville villeTest = new Ville("TestVille","rouge");
        jeu.infecterVille(villeTest, ECouleur.Bleu);
        assertEquals(villeTest.getInfection().get(ECouleur.Bleu).size(),1);
        
    }
    
    @Test
    public void TestInfectionPropagation(){
        
        Ville villeTest2 = new Ville("TestVille2","rouge");
        Ville villeTest3 = new Ville("TestVille3","rouge");
        
        villeTest2.ajouterVoisinage(villeTest2);
        
        for (int i = 0; i<4 ; i++)
        {
            jeu.infecterVille(villeTest2, ECouleur.Bleu);
        }

        assertEquals(1,villeTest3.getInfection().get(ECouleur.Bleu).size());

    }
    
    
}
