/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Joueur;
import com.miage.pandemie.business.carte.Localisation;
import com.miage.pandemie.business.element.Remede;
import com.miage.pandemie.business.element.Station;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeElement;
import java.lang.reflect.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;
/**
 *
 * @author Remi
 */
public class TestJeu {

    private Jeu jeu;
    private Class cls;
    
    
   @Before
   public  void InitPartie() throws Exception{
    

    jeu = Jeu.getInstance();
    cls = jeu.getClass();
    Field instance = cls.getDeclaredField("inst");
    instance.setAccessible(true);
    instance.set(jeu, null);
    jeu = Jeu.getInstance();
    jeu.addJoueur("Joueur1"); 
    jeu.addJoueur("Joueur2"); 
    jeu.addJoueur("Joueur3"); 
    jeu.addJoueur("Joueur4");       
    jeu.InitialiseNouvellePartie();
   }  
    

    
    @Test
    public void TestInitPartie()throws Exception{
        
        assertEquals(6,jeu.getLesMains().get("Joueur4").size());

    }
    
   @Test
    public void TestNbAction(){
        assertEquals(4,jeu.getLeNombreAction().get("Joueur1").intValue());
    }
    
    
    @Test
    public void TestInfectionPropagation(){
        
        Ville villeTest2 = new Ville("TestVille2","bleu");
        Ville villeTest3 = new Ville("TestVille3","bleu");
        
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
public void TestTmp(){
    assertTrue(jeu.getVille("Atlanta").getAllVoisins().contains(jeu.getVille("Washington")));
}
    @Test
    public void TestConduire(){
        jeu.conduire("Joueur1",jeu.getVille("Washington"));
        assertEquals("Washington",jeu.getLesPions().get("Joueur1").getPosition().getName());
        
    }
    
    @Test
    public void TestVolDirect(){
        Localisation tmpLoc = jeu.getCarteLoc("Sydney");
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        jeu.volDirect("Joueur1", tmpLoc);
        assertEquals("Sydney",jeu.getLesPions().get("Joueur1").getPosition().getName());
    }
    
    @Test
    public void TestVolCharter(){
        Localisation tmpLoc = jeu.getCarteLoc("Atlanta");
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        Ville dest = jeu.getVille("Londres");
        jeu.volCharter("Joueur1", tmpLoc, dest);
        assertEquals("Londres",jeu.getLesPions().get("Joueur1").getPosition().getName());
    }
    
    @Test
    public void TestVolNavette(){
        Station  stationDepart  = (Station)jeu.getLesElements().get(ETypeElement.Station).get(0);
        Station  stationArrivee  = (Station)jeu.getLesElements().get(ETypeElement.Station).get(1);
        stationDepart.setPosition(jeu.getVille("Atlanta"));
        stationArrivee.setPosition(jeu.getVille("Essen"));
        jeu.volNavette("Joueur1", jeu.getVille("Atlanta"), jeu.getVille("Essen"));
        assertEquals("Essen",jeu.getLesPions().get("Joueur1").getPosition().getName());        
    }
    
    @Test
    public void TestPartageConnaissancet(){
        Carte cartePartage = jeu.getLesMains().get("Joueur1").get(0);
        jeu.partageDeConnaissance("Joueur1", "Joueur2", cartePartage);
        assertTrue(jeu.getLesMains().get("Joueur2").contains(cartePartage));
        
    }
    
    @Test
    public void TestConstruireUneStation(){
        Localisation tmpLoc = jeu.getCarteLoc("Atlanta");
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        jeu.construireStation("Joueur1", tmpLoc, null);
        Station stationConstruite = (Station)jeu.getLesElements().get(ETypeElement.Station).get(0);
        assertEquals("Atlanta",stationConstruite.getPosition().getName());

    }
    
    @Test
    public void TestDecouvrirRemede(){
        ArrayList<Localisation> locList = new ArrayList<>();
        Localisation tmpLoc = jeu.getCarteLoc("Atlanta");
        locList.add(tmpLoc);
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        tmpLoc = jeu.getCarteLoc("Paris");
        locList.add(tmpLoc);        
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        tmpLoc = jeu.getCarteLoc("Londres");
        locList.add(tmpLoc);
        jeu.getLesMains().get("Joueur1").add(tmpLoc);        
        tmpLoc = jeu.getCarteLoc("Madrid");
        locList.add(tmpLoc);
        jeu.getLesMains().get("Joueur1").add(tmpLoc);
        tmpLoc = jeu.getCarteLoc("Essen");
        locList.add(tmpLoc);
        jeu.getLesMains().get("Joueur1").add(tmpLoc);     
        Station local = (Station)jeu.getLesElements().get(ETypeElement.Station).get(0);
        local.setPosition(jeu.getVille("Atlanta"));
        jeu.decouvrirRemede("Joueur1", locList);
        Remede  remede = (Remede)jeu.getLesElements().get(ETypeElement.Remede).get(0);
        assertTrue(remede.isDecouvert());
    }
    
    @Test
    public void TestTraiterMaladie(){
        jeu.infecterVille(jeu.getVille("Atlanta"), ECouleur.Bleu);
        int nbCubeMaladie = jeu.getVille("Atlanta").getInfection().get(ECouleur.Bleu).size();
        jeu.traiterMaladie("Joueur1", ECouleur.Bleu);
        assertEquals(nbCubeMaladie-1,jeu.getVille("Atlanta").getInfection().get(ECouleur.Bleu).size());
        
    }
    


    
    @Test
    public void TestGetCarteJoueur(){
       assertEquals("Moscou", jeu.getCarteJoueur("Moscou").getName()); 
    }
    
    @Test
    public void TestGetVille(){
        assertEquals("Atlanta", jeu.getVille("Atlanta").getName());
    }

    
    @Test
    public void TestFinTour(){
        jeu.finDeTour("Joueur1");
        assertEquals(8,jeu.getLesMains().get("Joueur1").size());
    }
    
   
}
