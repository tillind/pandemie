/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.EVille;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

/**
 *
 * @author alex
 */
public class CarteInfectionTest {
    private Infection maCarteBleu;
    private Infection maCarteNoir;
    private Infection maCarteRouge;
    private Infection maCarteJaune;
    

    private final String path = "/com/miage/pandemie/image/Infection";
    
    public CarteInfectionTest() {
       maCarteBleu = new Infection("/Ville",ECouleur.Bleu, EVille.Atlanta);
       maCarteNoir = new Infection("/Ville",ECouleur.Noir, EVille.Alger);
       maCarteRouge = new Infection("/Ville",ECouleur.Rouge, EVille.Bangkok);
       maCarteJaune = new Infection("/Ville",ECouleur.Jaune, EVille.Bogota);
    }
   
    @Tag("Test lien verso de la carte") 
    @Test
    public void testLinkVerso() {
        String tmp = path.concat("/Verso.jpg");
        assertTrue(maCarteBleu.linkImgVerso().equals(tmp));
        assertTrue(maCarteNoir.linkImgVerso().equals(tmp));
        assertTrue(maCarteRouge.linkImgVerso().equals(tmp));
        assertTrue(maCarteJaune.linkImgVerso().equals(tmp));
    }
    
    @Tag("Test lien carte rouge") 
    @Test
    public void testLinkRectoRed() {
        String tmp = path.concat("/Ville/"+ECouleur.Rouge.name()).concat("/"+EVille.Bangkok.name()+".jpg");
        assertTrue(maCarteRouge.linkImg().equals(tmp));
    }
    @Tag("Test lien carte bleu") 
    @Test
    public void testLinkRectoBlue() {
        String tmp = path.concat("/Ville/"+ECouleur.Bleu.name()).concat("/"+EVille.Atlanta.name()+".jpg");
        assertTrue(maCarteBleu.linkImg().equals(tmp));
    }
    @Tag("Test lien carte jaune") 
    @Test
    public void testLinkRectoYellow() {
        String tmp = path.concat("/Ville/"+ECouleur.Jaune.name()).concat("/"+EVille.Bogota.name()+".jpg");
        assertTrue(maCarteJaune.linkImg().equals(tmp));
    }
    
    @Tag("Test lien carte Noir") 
    @Test
    public void testLinkRectoBlack() {
        String tmp = path.concat("/Ville/"+ECouleur.Noir.name()).concat("/"+EVille.Alger.name()+".jpg");
        assertTrue(maCarteNoir.linkImg().equals(tmp));
    }
}
