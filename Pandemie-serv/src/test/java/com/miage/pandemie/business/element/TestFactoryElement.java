/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.element;


import com.miage.pandemie.business.factory.elementFacto.FactoryPion;
import com.miage.pandemie.business.factory.elementFacto.IFactoryElement;
import java.rmi.RemoteException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Remi
 */
public class TestFactoryElement {
    private IFactoryElement LesPions;
    
    private List<Element> ListeDesPions;
    
    public TestFactoryElement() throws RemoteException{
        LesPions = new FactoryPion();
        
        ListeDesPions = LesPions.newGame();
        
    }
    
    @Test
    public void TestNbPions()
    {
        int TmpNb = ListeDesPions.size();
        assertEquals(5,TmpNb);
    }
}
