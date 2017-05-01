/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Infection;
import com.miage.pandemie.business.element.CubeMaladie;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.FoyerInfection;
import com.miage.pandemie.business.element.Pion;
import com.miage.pandemie.business.element.TauxInfection;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ETypeCarte;
import com.miage.pandemie.business.enumparam.ETypeElement;

import com.miage.pandemie.business.facade.FacadeCarte;
import com.miage.pandemie.business.facade.FacadeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.miage.pandemie.business.chat.ClientChat;

/**
 *
 * @author Remi
 */
public class Jeu {
    private static Jeu inst = null;
    private ArrayList<ClientChat> Clients;
    private ArrayList<String> users;
    private int nbJoueurs = 0;
    private FacadeElement FacadeDesElements;
    private FacadeCarte FacadeDesCartes;
    private HashMap<ETypeElement, List<Element>> LesElements;
    private HashMap<ETypeCarte, List<Carte>> LesCartes;
    private HashMap<ETypeCarte, List<Carte>> LesDefausses;
    private HashMap<List<String>, List<Carte>> LesMains;
    private HashMap<String, Carte> LesRoles;
    private HashMap<String, Pion> LesPions;
    private ArrayList<Infection> TirageInfection;
    private TauxInfection tauxInfection;
    private FoyerInfection foyerInfection;

    public static Jeu getInstance(){
        if(inst == null){
            inst = new Jeu();
        }
        return inst;
    }
    
    public void addJoueur(String usr) throws Exception{
        if(this.users.size()<=4){
            this.users.add(usr);
        }
        else{
            throw new Exception("Nomdre d'utilisateurs max atteint");
        }
    }
    
    public void removeJoueur(String usr) throws Exception{
        users.remove(usr);
    }
    
    private void init(){
        this.FacadeDesCartes = new FacadeCarte();
        this.FacadeDesElements = new FacadeElement();
        this.LesElements = new HashMap<>();
        this.LesCartes = new HashMap<>();
        this.LesDefausses = new HashMap<>();
        this.LesMains = new HashMap<>();
        this.LesRoles = new HashMap<>();
    }
    private Jeu(ArrayList<String> joueurs) {
        this.init();
        this.users = joueurs;
    }
     private Jeu() {
         this.init();
        this.users = new ArrayList<>();
    }

    public void defaite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void finDeTour(String user) {
        
        //A la fin de son tour le joueur pioche 2 cartes
        piocherCarte(user);
        piocherCarte(user);
        
        //Tour infecteur
        //L infecteur tire un nombre de cartes Infection egal au Taux actuel d Infection 
        TirageInfection.clear();

        for (int i = 0; i < tauxInfection.getValeur(); i++) {
            int indexAleatoire = (int) Math.random() * LesCartes.get(ETypeCarte.Infection).size() - 1;
            if (indexAleatoire >= 0) {
                TirageInfection.add((Infection) LesCartes.get(ETypeCarte.Infection).get(indexAleatoire));
                LesCartes.get(ETypeCarte.Infection).remove(indexAleatoire);
            }
        }

        //L infecteur joue ensuite chacune de ses cartes 
        for (Infection carte : TirageInfection) {

            for (Element ville : LesElements.get(ETypeElement.Ville)) {
                if (ville.getName() == carte.getVille()) {
                    Ville tmpVille = (Ville) ville;
                    infecterVille(tmpVille, carte.getCouleur());
                }
            }
        }

    }

    public void InitialiseNouvellePartie() throws Exception {
        //On initialise une nouvelle partie
        this.FacadeDesCartes.newGame();
        this.FacadeDesElements.newGame();

        //On cree les pioches de cartes et les differents elements de jeu
        this.LesElements = FacadeDesElements.getLesElements();
        this.LesCartes = FacadeDesCartes.getLesCartes();

        //On récupère le Taux d'infection 
        this.tauxInfection = (TauxInfection) LesElements.get(ETypeElement.TauxInfection).get(0);
        
        //On récupère le Foyer d'infection
        this.foyerInfection = (FoyerInfection) LesElements.get(ETypeElement.FoyerInfection).get(0);
       
        
       //On creer les defausses de cartes
        LesDefausses.put(ETypeCarte.Joueur, new ArrayList<Carte>());
        LesDefausses.put(ETypeCarte.Infection, new ArrayList<Carte>());
        
        //On distribue les roles aux joueurs et on leur donne le pion correspondant
                 /**

        for (String user : Users) {

            int indexAleatoire = (int)(Math.random()* (LesCartes.get(ETypeCarte.Role).size() - 1));
            LesRoles.put(user, LesCartes.get(ETypeCarte.Role).get(indexAleatoire));
            
            switch(LesCartes.get(ETypeCarte.Role).get(indexAleatoire).getName()){
                case "Chercheuse": LesPions.put(user, (Pion)LesElements.get(ETypeElement.Pion).get(2));break;
                case "Expert": LesPions.put(user, (Pion)LesElements.get(ETypeElement.Pion).get(4)); ;break;
                case "Medecin": LesPions.put(user, (Pion)LesElements.get(ETypeElement.Pion).get(0)); ;break;
                case "Repartiteur": LesPions.put(user, (Pion)LesElements.get(ETypeElement.Pion).get(3)); ;break;
                case "Scientifique": LesPions.put(user, (Pion)LesElements.get(ETypeElement.Pion).get(1)); ;break; 
                default : 
                    throw ( new Exception ("Erreur dans l'attribution des rôles")); 
                    

            }
            LesCartes.get(ETypeCarte.Role).remove(indexAleatoire);
            


        }
        
        //On distribue les cartes Joueurs aux joueurs 
        for (String user : Users) {
            ArrayList<Carte> Main = new ArrayList<>();
            for (int i = 0; i < 6 - Users.size(); i++) {
                int joueurAleatoire = (int) Math.random() * LesCartes.get(ETypeCarte.Joueur).size() - 1;
                Main.add(LesCartes.get(ETypeCarte.Joueur).get(joueurAleatoire));
                LesCartes.get(ETypeCarte.Joueur).remove(joueurAleatoire);
                
                
            }

            LesMains.put(Users, Main);
        }



        //On distribue les roles aux joueurs et on leur donne le pion correspondant
        for (String user : Users) {
            int indexAleatoire = (int) Math.random() * LesCartes.get(ETypeCarte.Role).size() - 1;
            LesRoles.put(user, LesCartes.get(ETypeCarte.Role).get(indexAleatoire));
            LesCartes.get(ETypeCarte.Role).remove(indexAleatoire);

        }
        * 
        * */

    }

    public HashMap<ETypeCarte, List<Carte>> getLesCartes() {
        return LesCartes;
    }

    public void infecterVille(Ville v, ECouleur couleur) {

        if (v.getInfection().get(couleur).size() < 3) {

            boolean cubeEnPlace = false;
            int index = 0;
            while (!cubeEnPlace && index < LesElements.get(ETypeElement.CubeMaladie).size()) {
                CubeMaladie tmpCube = (CubeMaladie) LesElements.get(ETypeElement.CubeMaladie).get(index);
                if (tmpCube.getCouleur() == couleur) {
                    cubeEnPlace = true;
                    v.ajouterInfection(couleur, tmpCube);
                    LesElements.get(ETypeElement.CubeMaladie).remove(index);
                }

            }
            if (!cubeEnPlace) {
                defaite();
            }
            //Si il y a désormais 3 cube, on incrément le foyer d'infection
            if(v.getInfection().get(couleur).size() < 3){
                foyerInfection.augmenterInfection();
                if(foyerInfection.getValeur() == 8 ){
                    defaite();
                }
            }
        }
        else {
            for (Ville villeVoisine : v.getVillesVoisines()) {
                infecterVille(villeVoisine, couleur);
            }
        }

    }

    public void piocherCarte(String user) {
        //Si il n'y a plus de carte à piocher la partie est perdue
        if (LesCartes.get(ETypeCarte.Joueur).size() < 1) {
            defaite();
        } else {

            int indexAleatoire = (int) Math.random() * LesCartes.get(ETypeCarte.Joueur).size() - 1;
            Carte tmpCarte = LesCartes.get(ETypeCarte.Joueur).get(indexAleatoire);
            LesCartes.get(ETypeCarte.Joueur).remove(indexAleatoire);
            
            
            //On regarde si la carte piocher est une épidémie
            if (tmpCarte.getName() == "Epidemie_1") {
                //On déplace le taux d'infection
                tauxInfection.augementerPositionPiste();
                
                //On tire une carte infection ( si il en reste )
                indexAleatoire = (int) Math.random() * LesCartes.get(ETypeCarte.Infection).size() - 1;
                if (indexAleatoire >= 0) {
                    Infection carteInfection = (Infection) LesCartes.get(ETypeCarte.Infection).get(indexAleatoire);
                    
                    for (Element ville : LesElements.get(ETypeElement.Ville)) {
                        if (ville.getName() == carteInfection.getVille()) {
                            Ville tmpVille = (Ville) ville;
                            
                            //On déclenche l'infection 3 fois
                            for(int i=0;i<3;i++){
                                infecterVille(tmpVille, carteInfection.getCouleur());
                            }
                        }
                    }
                    
                    //On met la carte dans la défausse
                    LesDefausses.get(ETypeCarte.Infection).add(carteInfection);
                    LesCartes.get(ETypeCarte.Infection).remove(indexAleatoire);
                    
                    //On mélange la défausse avec la pioche
                    for (Carte carteDeLaDefausse: LesDefausses.get(ETypeCarte.Infection) ){
                        LesCartes.get(ETypeCarte.Infection).add(carteDeLaDefausse);
                        LesDefausses.get(ETypeCarte.Infection).remove(carteDeLaDefausse);
                    }

                }
             //On défausse la carte Epidemie apres sa resolution
             LesDefausses.get(ETypeCarte.Joueur).add(tmpCarte);
            }
            else {
                
                LesMains.get(user).add(tmpCarte);

            }
            
        }
    }
    
    public void conduire(String user, Ville destination){
        
        //On vérifie si la destination est une ville adjacente à la courante
        ArrayList<Ville> tmpVillesVoisines = LesPions.get(user).getPosition().getAllVoisins();
        if(tmpVillesVoisines.contains(destination)){
            LesPions.get(user).setVille(destination);
        }
    }

}
