package com.miage.pandemie.business.jeu;





import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Localisation;
import com.miage.pandemie.controller.IndexController;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;

import java.util.logging.Logger;

import com.miage.pandemie.business.chat.ClientChat;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import java.util.HashMap;
import java.util.Map;



/**

 *

 * @author Remi

 */

public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu{
    private static final long serialVersionUID = 1L;
    public HashMap<String,ClientJeu> lesClients;
    private Jeu leJeu;
    /**
     * @return the ctrl
     */
    public IndexController getController() {
        return ctrl;
    }

    /**
     * @param ctrl the ctrl to set
     */
    public void setController(IndexController ctrl) {
        this.ctrl = ctrl;
    }

    private IndexController ctrl;

    public ServeurJeuImpl()throws RemoteException{
        lesClients= new HashMap<>();
        leJeu = Jeu.getInstance();
    }

    @Override
    public void Connect(ClientJeu s, String User) throws RemoteException {
        try {
            this.leJeu.addJoueur(User);
            this.lesClients.put(User,s);
            ctrl.addElementToGame("[ "+User+" ] :  est connectée");
            if(lesClients.size()>1){
                
                for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
                    String key = entry.getKey();
                    ClientJeu value = entry.getValue();
                    value.canLaunchGame();
                    
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ServeurJeuImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override

    public void Desconnect(ClientJeu s, String User) throws RemoteException {

        try {

            this.leJeu.removeJoueur(User);

            this.lesClients.remove(s);

              ctrl.addElementToGame("[ "+User+" ] :  est déconnectée");

        } catch (Exception ex) {

            Logger.getLogger(ServeurJeuImpl.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    
    @Override
    public void conduire(String user, String dest) throws RemoteException {
        leJeu.conduire(user,leJeu.getVille(dest));
    }

    @Override
    public void volDirect(String usr, String loc) throws RemoteException {
        
        leJeu.volDirect(usr, leJeu.getCarteLoc(loc));
    }

    @Override
    public void volNavette(String usr, String villeDepart, String villeArrive) throws RemoteException {
        leJeu.volNavette(usr, leJeu.getVille(villeDepart), leJeu.getVille(villeArrive));
    }

    @Override
    public void volCharter(String usr, String loc, String ville) throws RemoteException {
        leJeu.volCharter(usr, leJeu.getCarteLoc(loc), leJeu.getVille(ville));
    }

    @Override
    public void finDetour(String usr) throws RemoteException {
        leJeu.finDeTour(usr);
    }

    @Override
    public void competenceRepartiteur(String usr) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construireStationRecherche(String usr, String loc, String ville) throws RemoteException {
        leJeu.construireStation(usr, leJeu.getCarteLoc(loc), leJeu.getVille(ville));
    }
    
    @Override
    public void construireStationRechercheExpert(String usr, String ville)throws RemoteException {
        leJeu.construireStation(usr, null, leJeu.getVille(ville));
    }
    

    @Override
    public void decouvrirRemede(String usr, List<String> lesLoc) throws RemoteException {
        ArrayList<Localisation> localisations = new ArrayList<>();
        for(String loc : lesLoc){
            localisations.add(leJeu.getCarteLoc(loc));
        }
        leJeu.decouvrirRemede(usr, localisations);
    }

    @Override
    public void retirerCubeMaladie(String usr, String couleur) throws RemoteException {
        leJeu.traiterMaladie(usr, ECouleur.valueOf(couleur));
    }

    @Override
    public void donnerCarte(String usr, String receiver, String carte) throws RemoteException {
        leJeu.partageDeConnaissance(usr, receiver, leJeu.getCarteJoueur(carte));
    }
    

    @Override
    public void defausseCarte(String usr, String carte) throws RemoteException {
       leJeu.defausserCarte(usr, leJeu.getCarteJoueur(carte));
    }

    @Override
    public void jouerCarteEvent(String usr, String carte) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void launchGame(String usr) throws RemoteException {
       leJeu.InitialiseNouvellePartie();
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            String key = entry.getKey();
            ClientJeu value = entry.getValue();
            HashMap<String,List<Carte>> tmp = leJeu.getLesMains();
            for (Map.Entry<String, List<Carte>> entry1 : tmp.entrySet()) {
                String key1 = entry1.getKey();
                List<Carte> value1 = entry1.getValue();
                if(key1.equals(key)){
                    for (Carte carte : value1) {
                        value.addCarte(carte.linkImg());
                    }
                }
            }
        }
    }



      

       

      

}

