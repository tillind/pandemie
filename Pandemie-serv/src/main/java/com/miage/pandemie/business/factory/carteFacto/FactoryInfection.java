package com.miage.pandemie.business.factory.carteFacto;

import com.miage.pandemie.business.Enum.ECouleur;
import com.miage.pandemie.business.Enum.EVilleBleu;
import com.miage.pandemie.business.Enum.EVilleJaune;
import com.miage.pandemie.business.Enum.EVilleNoir;
import com.miage.pandemie.business.Enum.EVilleRouge;
import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Infection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class FactoryInfection implements IFactoryCarte {

    @Override
    public List<Carte> newGame() {
        List<Carte> lesCartes = new ArrayList<>();
        for (ECouleur couleur : ECouleur.values()) {
            switch(couleur){
                case Rouge:
                        for (EVilleRouge ville : EVilleRouge.values()) {
                            lesCartes.add(new Infection("/Ville", couleur, ville));
                        }; break;
                case Bleu:
                        for (EVilleBleu ville : EVilleBleu.values()) {
                            lesCartes.add(new Infection("/Ville", couleur, ville));
                        };break;
                case Noir:  
                        for (EVilleNoir ville : EVilleNoir.values()) {
                            lesCartes.add(new Infection("/Ville", couleur, ville));
                        };break;
                case Jaune:
                         for (EVilleJaune ville : EVilleJaune.values()) {
                            lesCartes.add(new Infection("/Ville", couleur, ville));
                        };break;
                default:throw new UnsupportedOperationException("Color not supported yet.");
            }
        } 
        return lesCartes;
    }

    @Override
    public List<Carte> savedGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
