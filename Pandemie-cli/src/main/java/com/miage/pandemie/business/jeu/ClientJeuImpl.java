/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package com.miage.pandemie.business.jeu;



import com.miage.pandemie.controller.BoardController;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;



/**

 *

 * @author Remi

 */

public class ClientJeuImpl extends UnicastRemoteObject implements ClientJeu {



    private static final long serialVersionUID = 1L;



    transient BoardController ctrl;



    public ClientJeuImpl(Object ctr) throws RemoteException {

        ctrl = (BoardController) ctr;

    }



    @Override

    public void canLaunchGame() throws RemoteException {

        Platform.runLater(()-> this.ctrl.displayStartGame());

    }



    @Override

    public void addCarte(String link) throws RemoteException {

        Platform.runLater(()->this.ctrl.addCarteMain(link));

    }



    @Override

    public void setTauxInfection(int valeur, int position) throws RemoteException {

        Platform.runLater(()->this.ctrl.setTauxInfection(valeur, position));

    }



    @Override

    public void setFoyerInfection(int valeur) throws RemoteException {

        Platform.runLater(()->this.ctrl.setFoyerInfection(valeur));

    }



    @Override

    public void setPion(String user, String position) throws RemoteException {

       Platform.runLater(()-> this.ctrl.setPion(user, position));

    }







    @Override

    public void removeCarte(String link) throws RemoteException {

        Platform.runLater(()->this.ctrl.removeCarteMain(link));

    }



    @Override

    public void addDefausseJoueur(String link) throws RemoteException {

        Platform.runLater(()->this.ctrl.addCarteDefausseJoueur(link));

    }



    @Override

    public void addDefausseInfection(String link) throws RemoteException {

        Platform.runLater(()->this.ctrl.addCarteDefausseInfection(link));

    }



    @Override

    public void addRole(String nomRole) throws RemoteException {

        Platform.runLater(()->this.ctrl.addRole(nomRole));

    }







    @Override

    public void decouvrirRemede(String couleur) throws RemoteException {

        Platform.runLater(()->this.ctrl.decouvrirRemede(couleur));

    }



    @Override

    public void defaite() throws RemoteException {

        Platform.runLater(()->this.ctrl.defaite());

    }



    @Override

    public void victoire() throws RemoteException {

       Platform.runLater(()-> this.ctrl.victoire());

    }



    @Override

    public void addMaladieEradique(String couleur) throws RemoteException {

        Platform.runLater(()->this.ctrl.addMaladieEradique(couleur));

    }



    @Override

    public void addPseudo(String pseudo) throws RemoteException {

       Platform.runLater(()->this.ctrl.addJoueur(pseudo));

    }



    @Override

    public void afficherInfoVille(boolean aStation, int nbCubeJaune, int nbCubeRouge, int nbCubeBleu, int nbCubeNoir) throws RemoteException {

        Platform.runLater(()->this.ctrl.afficherInfoVille(aStation,nbCubeJaune,nbCubeRouge,nbCubeBleu,nbCubeNoir));

    }

}

