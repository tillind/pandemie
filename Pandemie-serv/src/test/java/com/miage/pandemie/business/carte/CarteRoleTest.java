/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.carte;

import com.miage.pandemie.business.enumparam.ERole;
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
public class CarteRoleTest {
    
    private Role maCarte;
    private ERole role = ERole.Expert;
    private final String path = "/com/miage/pandemie/image/Roles";
    
    public CarteRoleTest() {
        maCarte = new Role(role);
    }  
    
    @Tag("Test lien verso de la carte") 
    @Test
    public void testLinkVerso() {
        String tmp = path.concat("/Verso.jpg");
        assertTrue(maCarte.linkImgVerso().equals(tmp));
     
    }
    
    @Tag("Test lien recto de la carte") 
    @Test
    public void testLinkRecto() {
        String tmp = path.concat("/"+role.name()+".jpg");
        assertTrue(maCarte.linkImg().equals(tmp));
     
    }
}
