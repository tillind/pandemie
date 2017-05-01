package com.miage.pandemie.business.factory.carteFacto;

import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.EDifficulter;
import com.miage.pandemie.business.enumparam.EEvent;
import com.miage.pandemie.business.enumparam.EVilleBleu;
import com.miage.pandemie.business.enumparam.EVilleJaune;
import com.miage.pandemie.business.enumparam.EVilleNoir;
import com.miage.pandemie.business.enumparam.EVilleRouge;
import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Epidemie;
import com.miage.pandemie.business.carte.Event;
import com.miage.pandemie.business.carte.Infection;
import com.miage.pandemie.business.carte.Joueur;
import com.miage.pandemie.business.carte.Localisation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class FactoryJoueur implements IFactoryCarte {

    @Override
    public List<Carte> newGame() {
       List<Carte> lesCartes = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lesCartes.add(new Epidemie());
        }
        
        for(EEvent event: EEvent.values() ){
            lesCartes.add(new Event(event));
        }
        
        for (ECouleur couleur : ECouleur.values()) {
          switch(couleur){
              case Rouge:
                      for (EVilleRouge ville : EVilleRouge.values()) {
                          lesCartes.add(new Localisation("/Ville", couleur, ville));
                      } break;
              case Bleu:
                      for (EVilleBleu ville : EVilleBleu.values()) {
                          lesCartes.add(new Localisation("/Ville", couleur, ville));
                      }break;
              case Noir:  
                      for (EVilleNoir ville : EVilleNoir.values()) {
                          lesCartes.add(new Localisation("/Ville", couleur, ville));
                      }break;
              case Jaune:
                       for (EVilleJaune ville : EVilleJaune.values()) {
                          lesCartes.add(new Localisation("/Ville", couleur, ville));
                      }break;
              default:throw new UnsupportedOperationException("Color not supported yet.");
          }
        } 
        return lesCartes;

    }

    @Override
    public List<Carte> savedGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
