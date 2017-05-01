<<<<<<< HEAD:Pandemie-serv/src/main/java/com/miage/pandemie/business/jeu/ClientJeuImpl.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Remi
 */
public class ClientJeuImpl extends UnicastRemoteObject implements ClientJeu {

    
    public ClientJeuImpl()throws RemoteException{
    }

    
    @Override
    public void AffichageMain(String s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Remi
 */
public class ClientDistantImpl extends UnicastRemoteObject implements ClientDistant {

    
    public ClientDistantImpl()throws RemoteException{
    }

    
    @Override
    public void AffichageMain(String s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
>>>>>>> remi:Pandemie-serv/src/main/java/com/miage/pandemie/business/jeu/ClientDistantImpl.java
