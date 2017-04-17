/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.carte;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Tag;

/**
 *
 * @author alex
 */
public class CarteReferenceTest {
    
    private Reference maCarte;
    private final String path = "/com/miage/pandemie/image/Reference";
    public CarteReferenceTest() {
        maCarte = new Reference();
    }

    @Tag("Test lien verso de la carte") 
    @Test
    public void testLinkVerso() {
        String tmp = path.concat("/Rev_Verso_1.jpg");
        assertTrue(maCarte.linkImgVerso().equals(tmp));
     
    }
    
    @Tag("Test lien recto de la carte") 
    @Test
    public void testLinkRecto() {
        String tmp = path.concat("/Rev_Recto_1.jpg");
        assertTrue(maCarte.linkImg().equals(tmp));
    }
}
