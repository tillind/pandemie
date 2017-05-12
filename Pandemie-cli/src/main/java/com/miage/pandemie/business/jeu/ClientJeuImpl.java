/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.controller.BoardController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Remi
 */
public class ClientJeuImpl extends UnicastRemoteObject implements ClientJeu {

    private static final long serialVersionUID = 1L;

    transient BoardController ctrl;

    
    public ClientJeuImpl(Object ctr)throws RemoteException{
        ctrl = (BoardController) ctr;
    }
    @Override
    public void canLaunchGame() throws RemoteException {
        this.ctrl.displayStartGame();
    }

    @Override
    public void addCarte(String link) throws RemoteException {
        this.ctrl.addCarteMain(link);
    }
}
