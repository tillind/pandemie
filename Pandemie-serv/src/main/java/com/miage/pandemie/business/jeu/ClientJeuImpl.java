/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Remi
 */
public class ClientJeuImpl extends UnicastRemoteObject implements ClientJeu {

    private Jeu leJeu;
    
    public ClientJeuImpl()throws RemoteException{
        this.leJeu = Jeu.getInstance();
    }

    @Override
    public void changerTauInfection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
}
