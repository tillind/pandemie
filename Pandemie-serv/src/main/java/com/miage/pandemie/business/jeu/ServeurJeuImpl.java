/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.chat.ClientDistant;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.TauxInfection;
import com.miage.pandemie.business.enumparam.ETypeCarte;
import com.miage.pandemie.business.enumparam.ETypeElement;
import com.miage.pandemie.business.facade.FacadeCarte;
import com.miage.pandemie.business.facade.FacadeElement;
import com.miage.pandemie.controller.IndexController;
import com.miage.pandemie.business.carte.Infection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Remi
 */
public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu{
    



    public ServeurJeuImpl()throws RemoteException{
        
    }
    
    @Override
    public void Connect(ClientDistant s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Desconnect(ClientDistant s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      
       
      
}