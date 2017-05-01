/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.element;


import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeElement;
import com.miage.pandemie.business.facade.FacadeElement;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;


import static org.junit.Assert.*;
/**
 *
 * @author Remi
 */
public class TestFacadeElement {

    FacadeElement FacadeDesElements = new FacadeElement();
    HashMap<ETypeElement,List<Element>> LesElements;

     
    public TestFacadeElement() {
     FacadeDesElements.newGame();
     this.LesElements = FacadeDesElements.getLesElements();

    }
    
    @Test
    public void TestNbPions()
    {
        int TmpNb = (this.LesElements.get(ETypeElement.Pion)).size();
        assertEquals(5,TmpNb);
    }
    

    
    @Test
    public void TestNbRemede()
    {        
        int TmpNbRemede =(this.LesElements.get(ETypeElement.Remede)).size();
        assertEquals(4,TmpNbRemede);
        
    }
    
    @Test
    public void TestNbCubeMaladie()
    {        
        int TmpNbCube =(this.LesElements.get(ETypeElement.CubeMaladie)).size();
        assertEquals(96,TmpNbCube);
        
    }
    
    @Test
    public void TestNbStation()
    {        
        int TmpNbStation =(this.LesElements.get(ETypeElement.Station)).size();
        assertEquals(6,TmpNbStation);
        
    }
    
    @Test
    public void TestNbVille()
    {        
        int TmpNbVille=(this.LesElements.get(ETypeElement.Ville)).size();
        assertEquals(48,TmpNbVille);
        
    }
    
  
}
