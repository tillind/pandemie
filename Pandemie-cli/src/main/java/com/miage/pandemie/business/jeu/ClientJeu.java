<<<<<<< HEAD:Pandemie-cli/src/main/java/com/miage/pandemie/business/jeu/ClientJeu.java
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
public interface ClientJeu extends Remote{
    void AffichageMain(String s) throws RemoteException;
}
=======
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
>>>>>>> remi:Pandemie-serv/src/main/java/com/miage/pandemie/business/jeu/ClientDistant.java
