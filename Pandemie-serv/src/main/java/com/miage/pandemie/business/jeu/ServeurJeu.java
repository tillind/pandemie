/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.chat.ClientDistant;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Remi
 */
public interface ServeurJeu extends Remote {

    void Connect(ClientDistant s,String User) throws RemoteException;
    void Desconnect(ClientDistant s,String User) throws RemoteException;

}
