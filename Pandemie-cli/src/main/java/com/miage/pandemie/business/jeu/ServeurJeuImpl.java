/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu{

    private static final long serialVersionUID = 1L;
    
    public List<ClientJeu> lesClients;



    public ServeurJeuImpl()throws RemoteException{
        lesClients= new ArrayList<ClientJeu>();
    }
    


    @Override
    public void Connect(ClientJeu s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Desconnect(ClientJeu s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      
       
      
}
