/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.chat.ClientDistant;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.TauxInfection;
import com.miage.pandemie.business.enumparam.ETypeCarte;
import com.miage.pandemie.business.enumparam.ETypeElement;
import com.miage.pandemie.business.facade.FacadeCarte;
import com.miage.pandemie.business.facade.FacadeElement;
import com.miage.pandemie.controller.IndexController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Remi
 */
public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu{
    
    ArrayList<ClientDistant> Clients = new ArrayList<>();
    ArrayList<String> Users=new ArrayList<>();
    FacadeElement FacadeDesElements = new FacadeElement();
    FacadeCarte FacadeDesCartes = new FacadeCarte();
    HashMap<ETypeElement,List<Element>> lesElements;
    HashMap<ETypeCarte,List<Carte>> LesCartes;
    HashMap<ETypeCarte,List<Carte>> LesDefausses;
    HashMap<List<String>,List<Carte>> LesMains;
    HashMap<String,Carte> LesRoles;
    TauxInfection tauxInfection;
    ArrayList<Carte> TirageInfection= new ArrayList<>();;


    public ServeurJeuImpl(){
        
    }
    
    @Override
    public void Connect(ClientDistant s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Desconnect(ClientDistant s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finDePartie(ClientDistant s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finDeTour(ClientDistant s, String User) throws RemoteException {
        
        //Tour de l'infecteur
        
        //L'infecteur tire un nombre de cartes Infection égal au Taux actuel d'Infection 
       TirageInfection.clear(); 
       for(int i = 0; i<this.tauxInfection.getValeur(); i++)
       {
           int indexAleatoire = (int)Math.random()*LesCartes.get(ETypeCarte.Infection).size()-1;
           TirageInfection.add(LesCartes.get(ETypeCarte.Infection).get(indexAleatoire));
           LesCartes.get(ETypeCarte.Infection).remove(indexAleatoire);
       }
       
       //L'infecteur joue ensuite chacune de ses cartes 
       for(Carte carte: TirageInfection){
           
           //Besoin de la classe ville
       }
       
    }

    @Override
    public void InitialiseNouvellePartie(ClientDistant s, String User) throws RemoteException {
        //On crée les pioches de cartes et les différents élements de jeu
        FacadeDesElements.newGame();
        FacadeDesCartes.newGame();
        this.lesElements = FacadeDesElements.getLesElements();
        this.LesCartes = FacadeDesCartes.getLesCartes();
        
        //On distribue les cartes Joueurs aux joueurs
        for(String user:Users)
        {
            ArrayList<Carte> Main = new ArrayList<>();
            for(int i = 0; i<6-Users.size();i++)
            {
                int joueurAleatoire = (int)Math.random()*LesCartes.get(ETypeCarte.Joueur).size()-1;
                Main.add(LesCartes.get(ETypeCarte.Joueur).get(joueurAleatoire));
                LesCartes.get(ETypeCarte.Joueur).remove(joueurAleatoire);
            }   
           
            LesMains.put(Users, Main);
        }

        
        //On créer les défausses de cartes
        LesDefausses.put(ETypeCarte.Joueur,new ArrayList<Carte>());
        LesDefausses.put(ETypeCarte.Infection,new ArrayList<Carte>());
        
        //On distribue les roles aux joueurs
        for(String user: Users)
        {
           int indexAleatoire = (int)Math.random()*LesCartes.get(ETypeCarte.Role).size()-1;
           LesRoles.put(user,LesCartes.get(ETypeCarte.Role).get(indexAleatoire));
           LesCartes.get(ETypeCarte.Role).remove(indexAleatoire);
            
        }
        
        
        
    }
    
    
    

    
}
