/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Infection;
import com.miage.pandemie.business.carte.Joueur;
import com.miage.pandemie.business.carte.Role;
import com.miage.pandemie.business.chat.ClientDistant;
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
import java.util.Random;

/**
 *
 * @author Remi
 */
public class Jeu {

    private ArrayList<com.miage.pandemie.business.chat.ClientDistant> Clients;
    private ArrayList<String> Users;
    private FacadeElement FacadeDesElements;
    private FacadeCarte FacadeDesCartes;
    private HashMap<ETypeElement, List<Element>> LesElements;
    private HashMap<ETypeCarte, List<Carte>> LesCartes;
    private HashMap<ETypeCarte, List<Carte>> LesDefausses;
    private HashMap<String, List<Carte>> LesMains;
    private HashMap<String, Role> LesRoles;
    private HashMap<String, Pion> LesPions;
    private ArrayList<Infection> TirageInfection;
    private TauxInfection tauxInfection;
    private FoyerInfection foyerInfection;

    public Jeu(ArrayList<String> joueurs) {
        this.FacadeDesCartes = new FacadeCarte();
        this.FacadeDesElements = new FacadeElement();
        this.LesElements = new HashMap<>();
        this.LesCartes = new HashMap<>();
        this.LesDefausses = new HashMap<>();
        this.LesMains = new HashMap<>();
        this.LesRoles = new HashMap<>();
        this.LesPions = new HashMap<>();
        this.Users = joueurs;
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
                if (ville.getName().equals(carte.getVille())) {
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
        int indexAleatoire;
        Random randomGenerator = new Random();
        for (int cptUser = 0; cptUser < Users.size(); cptUser++) {
            indexAleatoire = randomGenerator.nextInt(LesCartes.get(ETypeCarte.Role).size());
            LesRoles.put(Users.get(cptUser), (Role) LesCartes.get(ETypeCarte.Role).get(indexAleatoire));

            switch (LesCartes.get(ETypeCarte.Role).get(indexAleatoire).getName()) {
                case "Chercheuse":
                    LesPions.put(Users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(2));
                    break;
                case "Expert":
                    LesPions.put(Users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(4));
                    ;
                    break;
                case "Medecin":
                    LesPions.put(Users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(0));
                    ;
                    break;
                case "Repartiteur":
                    LesPions.put(Users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(3));
                    ;
                    break;
                case "Scientifique":
                    LesPions.put(Users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(1));
                    ;
                    break;
                default:  ;

            }

            LesCartes.get(ETypeCarte.Role).remove(indexAleatoire);
            
            //On creer la main du joueur
            LesMains.put(Users.get(cptUser),new ArrayList<>());

           

            //On fait ensuite piocher au joueur 6 carte 
            
             for(int cptPioche = 0; cptPioche <6; cptPioche++) {
              piocherCarte(Users.get(cptUser)); 
             }
             
             
        }
    }

    public void infecterVille(Ville v, ECouleur couleur) {

        if (v.getInfection().get(couleur).size() < 3) {
            boolean cubeEnPlace = false;
            int index = 0;
            //On cherche un cube maladie de la bonne couleur
            while (!cubeEnPlace && index < LesElements.get(ETypeElement.CubeMaladie).size()) {
                
                CubeMaladie tmpCube = (CubeMaladie) LesElements.get(ETypeElement.CubeMaladie).get(index);
                
                //Si on le trouve on le place
                if (tmpCube.getCouleur() == couleur) {
                    cubeEnPlace = true;
                    v.ajouterInfection(tmpCube);
                }
                //Sinon on incremente l index
                else{
                    index++;
                }
            }
            //Si il n'y en a plus c'est la defaite
            if (!cubeEnPlace) {
                defaite();
            }
            
            //Si le cube est place on le suprimme du stock
            else {
              
                LesElements.get(ETypeElement.CubeMaladie).remove(index);

            }
            
            //Si il y a désormais 3 cube, on incrément le foyer d'infection
            if (v.getInfection().get(couleur).size() == 3) {
               // foyerInfection.augmenterInfection();
                if (foyerInfection.getValeur() == 8) {
                    defaite();
                }
            }
            
        }
       
        /**
        else {
           
            //On exclu se foyer de la chainne de propagation
            VillesDejaPropagees.add(v);
            //for (Ville villeVoisine : v.getVillesVoisines()) 
            for (int cptVilleVoisine = 0; cptVilleVoisine < v.getVillesVoisines().size(); cptVilleVoisine++)
            {
                if(!VillesDejaPropagees.contains(v.getVillesVoisines().get(cptVilleVoisine))){
                    
                    infecterVille(v.getVillesVoisines().get(cptVilleVoisine), couleur, VillesDejaPropagees );
                }
            }
        }
        * */
        

    }
        

    public void piocherCarte(String user) {
        //Si il n'y a plus de carte à piocher la partie est perdue
        if (LesCartes.get(ETypeCarte.Joueur).size() < 1) {
            defaite();
        } 
        
        else {

            //On prend une carte au hasard dans la pioche
            int indexAleatoire;
            Random randomGenerator = new Random();
            indexAleatoire = randomGenerator.nextInt(LesCartes.get(ETypeCarte.Joueur).size());
            Joueur tmpCarte = (Joueur)LesCartes.get(ETypeCarte.Joueur).get(indexAleatoire);
            
            //On la retire de la pioche
            LesCartes.get(ETypeCarte.Joueur).remove(indexAleatoire);

            //On regarde si la carte  est une épidémie
            if (tmpCarte.getName().equals("Epidemie_1")) {
                
                
                //On déplace le taux d'infection
                tauxInfection.augementerPositionPiste();

                //On tire une carte infection ( si il en reste )
                if (LesCartes.get(ETypeCarte.Infection).size() > 0) {
                    
                    indexAleatoire = randomGenerator.nextInt(LesCartes.get(ETypeCarte.Infection).size());
                    Infection carteInfection = (Infection) LesCartes.get(ETypeCarte.Infection).get(indexAleatoire);
                    
                   //On met la carte dans la défausse
                    LesDefausses.get(ETypeCarte.Infection).add(carteInfection);
                    LesCartes.get(ETypeCarte.Infection).remove(indexAleatoire);

                    
                    //On trouve la ville a infecter parmis la liste
                    for (int villeCpt = 0; villeCpt < LesElements.get(ETypeElement.Ville).size(); villeCpt++) {
                        if (LesElements.get(ETypeElement.Ville).get(villeCpt).getName().equals(carteInfection.getVille())) {
                            Ville tmpVille = (Ville) LesElements.get(ETypeElement.Ville).get(villeCpt);

                            //On déclenche l'infection 3 fois
                            for (int i = 0; i < 3; i++) {
                                infecterVille(tmpVille, carteInfection.getCouleur());
                            }
                        }
                    }



                    //On melange la défausse Infection avec la pioche
                    for (Carte carteDeLaDefausse : LesDefausses.get(ETypeCarte.Infection)) {
                        LesCartes.get(ETypeCarte.Infection).add(carteDeLaDefausse);
                    }

                   LesDefausses.get(ETypeCarte.Infection).clear();
                    
                }
                //On defausse la carte Epidemie apres sa resolution
                LesDefausses.get(ETypeCarte.Joueur).add(tmpCarte);
               
            } 
            else {
                LesMains.get(user).add(tmpCarte);
            }

        }

    }

    public void conduire(String user, Ville destination) {

        //On vérifie si la destination est une ville adjacente à la courante
        ArrayList<Ville> tmpVillesVoisines = LesPions.get(user).getPosition().getAllVoisins();
        if (tmpVillesVoisines.contains(destination)) {
            LesPions.get(user).setVille(destination);
        }
    }

}
