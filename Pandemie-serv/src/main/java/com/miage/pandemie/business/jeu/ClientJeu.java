/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.element.Ville;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Remi
 */
public interface ClientJeu extends Remote {   
    public void changerTauInfection();
}
