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
import com.miage.pandemie.business.element.Pion;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeCarte;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author Remi
 *
 */
public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu {

    private static final long serialVersionUID = 1L;
    public HashMap<String, ClientJeu> lesClients;
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

    public ServeurJeuImpl() throws RemoteException {
        lesClients = new HashMap<>();
        leJeu = Jeu.getInstance();
    }

    @Override
    public void Connect(ClientJeu s, String User) throws RemoteException {
        try {
            this.leJeu.addJoueur(User);
            this.lesClients.put(User, s);
            ctrl.addElementToGame("[ " + User + " ] :  est connectée");
            if (lesClients.size() > 1) {

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

            ctrl.addElementToGame("[ " + User + " ] :  est déconnectée");

        } catch (Exception ex) {

            Logger.getLogger(ServeurJeuImpl.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void conduire(String user, String dest) throws RemoteException {
        leJeu.conduire(user, leJeu.getVille(dest));
        Pion pion = leJeu.getLesPions().get(user);
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            ClientJeu value = entry.getValue();
            value.setPion(user, pion.getPosition().getName());
        }
    }

    @Override
    public void volDirect(String user, String loc) throws RemoteException {
        leJeu.volDirect(user, leJeu.getCarteLoc(loc));
        Pion pion = leJeu.getLesPions().get(user);
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            String key = entry.getKey();
            ClientJeu value = entry.getValue();
            value.setPion(user, pion.getPosition().getName());
            if (key.equals(user)) {
                value.removeCarte(loc);
            }
            value.addDefausseJoueur(loc);
        }
    }

    @Override
    public void volNavette(String usr, String villeDepart, String villeArrive) throws RemoteException {
        leJeu.volNavette(usr, leJeu.getVille(villeDepart), leJeu.getVille(villeArrive));
        Pion pion = leJeu.getLesPions().get(usr);
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            ClientJeu value = entry.getValue();
            value.setPion(usr, pion.getPosition().getName());
        }

    }

    @Override
    public void volCharter(String usr, String loc, String ville) throws RemoteException {
        leJeu.volCharter(usr, leJeu.getCarteLoc(loc), leJeu.getVille(ville));
        Pion pion = leJeu.getLesPions().get(usr);
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            String key = entry.getKey();
            ClientJeu value = entry.getValue();
            value.setPion(usr, pion.getPosition().getName());
            if (key.equals(usr)) {
                value.removeCarte(loc);
            }
            value.addDefausseJoueur(loc);
        }
    }

    @Override
    public void finDetour(String usr) throws RemoteException {
        leJeu.finDeTour(usr);
        HashMap<String, List<Carte>> tmp = leJeu.getLesMains();
        List<Ville> villesModifiees = leJeu.getVillesModifiees();
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            String key = entry.getKey();
            ClientJeu value = entry.getValue();
            for (Map.Entry<String, List<Carte>> entry1 : tmp.entrySet()) {
                String key1 = entry1.getKey();
                List<Carte> value1 = entry1.getValue();
                if (key1.equals(key)) {
                    for (Carte carte : value1) {
                        value.addCarte(carte.linkImg());
                    }
                }
            }

            value.setTauxInfection(leJeu.getTauxInfection().getValeur(), leJeu.getTauxInfection().getValeur());
            value.setFoyerInfection(leJeu.getFoyerInfection().getValeur());
            value.addDefausseInfection(leJeu.getLesDefausses().get(ETypeCarte.Infection).get(leJeu.getLesDefausses().get(ETypeCarte.Infection).size() - 1).linkImg());
            
            if( leJeu.getDefaite()){
                value.defaite();
            }
            
        }

        leJeu.clearVilleModifiees();
        

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
    public void construireStationRechercheExpert(String usr, String ville) throws RemoteException {
        leJeu.construireStation(usr, null, leJeu.getVille(ville));
    }

    @Override
    public void decouvrirRemede(String usr, List<String> lesLoc, String couleur) throws RemoteException {
        ArrayList<Localisation> localisations = new ArrayList<>();
        for (String loc : lesLoc) {
            localisations.add(leJeu.getCarteLoc(loc));
        }
        leJeu.decouvrirRemede(usr, localisations, ECouleur.valueOf(couleur));
        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            ClientJeu value = entry.getValue();
            value.decouvrirRemede(couleur);
            
            if(leJeu.getMaladiesEradiquees().contains(ECouleur.valueOf(couleur))){
                value.addMaladieEradique(couleur);
            }
            
            if( leJeu.getVictoire()){
                value.victoire();
            }
            
            
        }
        

    }

    @Override
    public void retirerCubeMaladie(String usr, String couleur) throws RemoteException {
        leJeu.traiterMaladie(usr, ECouleur.valueOf(couleur));
    }

    @Override
    public void donnerCarte(String usr, String receiver, String carte) throws RemoteException {
        leJeu.partageDeConnaissance(usr, receiver, leJeu.getCarteJoueur(carte));
        lesClients.get(usr).removeCarte(carte);
        lesClients.get(receiver).addCarte(carte);
        
    }

    @Override
    public void defausseCarte(String usr, String carte) throws RemoteException {
        leJeu.defausserCarte(usr, leJeu.getCarteJoueur(carte));
        lesClients.get(usr).removeCarte(carte);
    }

    @Override
    public void jouerCarteEvent(String usr, String carte) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void launchGame(String usr) throws RemoteException {
        leJeu.InitialiseNouvellePartie();
        HashMap<String, List<Carte>> tmp = leJeu.getLesMains();
        HashMap<String, Pion> tmpPions = leJeu.getLesPions();

        for (Map.Entry<String, ClientJeu> entry : lesClients.entrySet()) {
            String key = entry.getKey();
            ClientJeu value = entry.getValue();
            
            ArrayList<String> lesJoueurs = leJeu.getUsers();
            for (String leJoueur : lesJoueurs) {
                if(!leJoueur.equals(key)){
                   value.addPseudo(leJoueur);
                }
            }
            
            for (Map.Entry<String, List<Carte>> entry1 : tmp.entrySet()) {
                String key1 = entry1.getKey();
                List<Carte> value1 = entry1.getValue();
                if (key1.equals(key)) {
                    for (Carte carte : value1) {
                        value.addCarte(carte.linkImg());
                    }
                   value.addRole(leJeu.getLesRoles().get(key).getName());
                }
            }

          for (Map.Entry<String, Pion> entry2 : tmpPions.entrySet()) {
                Pion pion = entry2.getValue();
                value.setPion(pion.getCouleur(), pion.getPosition().getName());
            }


            value.setTauxInfection(leJeu.getTauxInfection().getValeur(), leJeu.getTauxInfection().getValeur());
            value.setFoyerInfection(leJeu.getFoyerInfection().getValeur());
            value.addDefausseInfection(leJeu.getLesDefausses().get(ETypeCarte.Infection).get(leJeu.getLesDefausses().get(ETypeCarte.Infection).size() - 1).linkImg());
        }

        leJeu.clearVilleModifiees();
    }

    @Override
    public void getInfoVille(String usr,String Ville) throws RemoteException {
       Ville tmpVille = leJeu.getVille(Ville);
       int nbCubeRouge = tmpVille.getInfection().get(ECouleur.Rouge).size();
       int nbCubeJaune = tmpVille.getInfection().get(ECouleur.Jaune).size();
       int nbCubeBleu = tmpVille.getInfection().get(ECouleur.Bleu).size();
       int nbCubeNoir = tmpVille.getInfection().get(ECouleur.Noir).size();
       boolean aStation = leJeu.aUneStation(tmpVille);
       lesClients.get(usr).afficherInfoVille(aStation, nbCubeJaune,nbCubeRouge,nbCubeBleu,nbCubeNoir);       
    }

}
