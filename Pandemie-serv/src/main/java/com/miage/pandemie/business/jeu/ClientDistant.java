/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Remi
 */
public interface ClientDistant extends Remote{
    void AffichageMain(String s) throws RemoteException;
}
