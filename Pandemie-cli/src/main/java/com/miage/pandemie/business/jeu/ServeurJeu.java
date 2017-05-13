package com.miage.pandemie.business.jeu;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author Remi
 */
public interface ServeurJeu extends Remote {
    void Connect(ClientJeu s,String User) throws RemoteException;
    void Desconnect(ClientJeu s,String User) throws RemoteException;
    void launchGame(String usr) throws RemoteException;
    public void conduire(String usr,String dest)throws RemoteException;
    public void volDirect(String usr,String loc) throws RemoteException;
    public void volNavette(String usr, String villeDepart, String villeArrive)throws RemoteException;
    public void volCharter (String usr,String loc,String ville) throws RemoteException;
    public void finDetour(String usr) throws RemoteException;
    public void competenceRepartiteur(String usr) throws RemoteException;
    public void construireStationRecherche(String usr, String loc, String ville) throws RemoteException;
    public void construireStationRechercheExpert(String usr, String ville) throws RemoteException;
    public void decouvrirRemede(String usr, List<String> lesLoc, String couleur) throws RemoteException;
    public void retirerCubeMaladie(String usr, String couleur) throws RemoteException;
    public void donnerCarte(String usr, String receiver, String carte) throws RemoteException;
    /**auto***/
    public void defausseCarte(String usr, String carte ) throws RemoteException;
    public void jouerCarteEvent(String usr, String carte) throws RemoteException;
}
