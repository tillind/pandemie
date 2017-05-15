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
public interface ClientJeu extends Remote {   
    public void canLaunchGame() throws RemoteException;
    public void addCarte(String link)throws RemoteException;
    public void addPseudo(String pseudo)throws RemoteException;
    public void setTauxInfection(int valeur, int position) throws RemoteException;
    public void setFoyerInfection(int valeur) throws RemoteException;
    public void setPion(String usr, String position)throws RemoteException;
    public void removeCarte(String link)throws RemoteException;
    public void addDefausseJoueur(String link) throws RemoteException;
    public void addDefausseInfection(String linkImg)throws RemoteException;
    public void addRole(String nomRole)throws RemoteException;
    public void decouvrirRemede(String couleur) throws RemoteException;
    public void defaite()throws RemoteException;
    public void victoire()throws RemoteException;
    public void addMaladieEradique(String couleur)throws RemoteException;
    public void afficherInfoVille(boolean aStation, int nbCubeJaune, int nbCubeRouge, int nbCubeBleu, int nbCubeNoir)throws RemoteException;
    
}
