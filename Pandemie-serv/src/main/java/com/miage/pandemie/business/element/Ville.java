
package com.miage.pandemie.business.element;

import com.miage.pandemie.business.enumparam.ECouleur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Julien on 25/04/2017.
 */
public class Ville extends Element{

    private String couleur;
    private HashSet<Ville> villesVoisines;
    private HashMap<ECouleur,List<CubeMaladie>> Infection;




    public Ville(String nom,String couleur) {
        super(nom);
        this.couleur = couleur;
        this.villesVoisines = new HashSet();
        this.Infection = new HashMap<>();
        
        for(ECouleur EnumCouleur : ECouleur.values()){
            Infection.put(EnumCouleur,new ArrayList<>());
        }

    }

    public HashMap<ECouleur, List<CubeMaladie>> getInfection() {
        return Infection;
    }

    public List<Ville> getVillesVoisines() {
        List<Ville> villeVoisine = new ArrayList<>(); 
        Iterator it = villesVoisines.iterator();
        while(it.hasNext())
            villeVoisine.add((Ville)it.next());
        return villeVoisine;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + this.getName() + '\'' +
                '}';
    }

    /**
     *
     * @param nomVille
     * @return la ville correpsondante au nom donnée si elle est dans les voisins.
     */
    public Ville getVille(String nomVille){

        Ville result = null;

        for(Ville ville : villesVoisines) {
            if(ville.getName() == nomVille) {
                result = ville;
            }
        }

        if(result == null) {
            System.out.println("Erreur, pas de ville voisine intitulée "+nomVille+" à "+getName()+".");
            return null;
        } else {
            return result;
        }
    }

    /**
     * Ajoute un lien de voisinage entre deux villes automatiquement
     * @param ville
     */
    public void ajouterVoisinage(Ville ville) {
        villesVoisines.add(ville);
        ville.getVillesVoisines().add(this);
    }

    public ArrayList<Ville> getAllVoisins() {
        ArrayList<Ville> lesVoisines = new ArrayList<>();
        for (Ville ville : villesVoisines) {
            lesVoisines.add(ville);
        }
        return lesVoisines;
    }
    
    public void ajouterInfection(CubeMaladie cube)
    {
        this.Infection.get(cube.couleur).add(cube);
        
    }
    
    
    
}
