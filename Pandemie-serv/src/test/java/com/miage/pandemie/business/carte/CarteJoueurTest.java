package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.EEvent;
import com.miage.pandemie.business.enumparam.EVille;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alex
 */

public class CarteJoueurTest {
    
    private Joueur joueurEpi;
    private Joueur joueurEvent;
    private Joueur joueurLoc;
    private String concat ="";
    private  String path = "/com/miage/pandemie/image/Joueur";
    
    
    public CarteJoueurTest(){
        joueurEpi = new Epidemie();
        joueurEvent = new Event(EEvent.Event_Nuit);
        joueurLoc = new Localisation("/Ville", ECouleur.Rouge, EVille.Bangkok);
        
    }

    @Test
    public void testVersoCarteEpidemie(){
        concat = path.concat("/Verso.jpg");
        assertEquals(joueurEpi.linkImgVerso(),concat);
    }

    @Test
    public void testRectoCarteEpidemie(){
        concat = path.concat("/Epidemie/Epidemie_1.jpg");
        assertEquals(joueurEpi.linkImg(),concat);
    }
    
    @Test
    public void testVersoCarteEvent(){
        concat = path.concat("/Verso.jpg");
        assertEquals(joueurEvent.linkImgVerso(),concat);
    }

   @Test
    public void testRectoCarteEvent(){
        concat = path.concat("/Event/Event_Nuit.jpg");
        assertEquals(joueurEvent.linkImg(),concat);
    }
    
    @Test
    public void testVersoCarteLoc(){
        concat = path.concat("/Verso.jpg");
        assertEquals(joueurLoc.linkImgVerso(),concat);
    }

   @Test
    public void testRectoCarteLoc(){
        concat = path.concat("/Localisation/Ville/Rouge/Bangkok.jpg");
        assertEquals(joueurLoc.linkImg(),concat);
    }
}
