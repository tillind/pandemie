/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.element;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;

/**
 *
 * @author Remi
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestVille {
    Ville villeTest1 = new Ville("TestVille1","rouge");
    Ville villeTest2 = new Ville("TestVille2","rouge");
    Ville villeTest3 = new Ville("TestVille3","rouge");
    
    public TestVille(){
        villeTest1.ajouterVoisinage(villeTest2);
        villeTest1.ajouterVoisinage(villeTest3);
    }
    
    @Test
    public void TestGetVoisins()
    {
        assertEquals(villeTest1.getAllVoisins().size(),2);
    }
    
    @Test
    public void TestInfection()
    {
        CubeMaladie cubeTest = new CubeMaladie(ECouleur.Rouge);
        villeTest1.ajouterInfection(cubeTest);
        assertEquals(villeTest1.getInfection().get(cubeTest.couleur).size(),1);
    }
}
