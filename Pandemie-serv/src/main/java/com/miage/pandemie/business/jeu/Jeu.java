package com.miage.pandemie.business.jeu;

import com.miage.pandemie.business.carte.Carte;
import com.miage.pandemie.business.carte.Infection;
import com.miage.pandemie.business.carte.Joueur;
import com.miage.pandemie.business.carte.Localisation;
import com.miage.pandemie.business.carte.Role;
import com.miage.pandemie.business.element.CubeMaladie;
import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.FoyerInfection;
import com.miage.pandemie.business.element.Pion;
import com.miage.pandemie.business.element.Station;
import com.miage.pandemie.business.element.TauxInfection;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.element.Remede;
import com.miage.pandemie.business.enumparam.ECouleur;
import com.miage.pandemie.business.enumparam.ERole;
import com.miage.pandemie.business.enumparam.ETypeCarte;
import com.miage.pandemie.business.enumparam.ETypeElement;
import com.miage.pandemie.business.facade.FacadeCarte;
import com.miage.pandemie.business.facade.FacadeElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Jeu {
    
    private static Jeu inst = null;

    private ArrayList<ClientJeu> Clients;
    private ArrayList<String> users;
    private FacadeElement FacadeDesElements;
    private FacadeCarte FacadeDesCartes;
    private HashMap<ETypeElement, List<Element>> LesElements;
    private HashMap<ETypeCarte, List<Carte>> LesCartes;
    private HashMap<ETypeCarte, List<Carte>> LesDefausses;
    private HashMap<String, List<Carte>> LesMains;
    private HashMap<String, Role> LesRoles;
    private HashMap<String, Pion> LesPions;
    private HashMap<String, Integer> LeNombreAction;
    private ArrayList<Infection> TirageInfection;
    private TauxInfection tauxInfection;
    private FoyerInfection foyerInfection;
    private List<ECouleur> maladiesEradiquees;
   

  
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
    
     /**
     *
     * @param user
     * @return la position du joueur suivant
     */
    public int getNextPlayer(String user){
        int position = users.indexOf(user);
        if (position == users.size() - 1){
            return 0;
        } else {
            return position + 1;
        }
    }
    
    public void finDeTour(String user) {

        //A la fin de son tour le joueur pioche 2 cartes
        piocherCarte(user);
        piocherCarte(user);
        
       //On met le nombre d'actions de joueur à 0
        LeNombreAction.put(user, 0);

        //On met le nombre d'actions du joueur suivant à 4
        int posNextUser = getNextPlayer(user);
        String nextUser = users.get(posNextUser);
        LeNombreAction.put(nextUser,4);

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
                    //On marque la ville comme infectee
                    tmpVille.setPropagation(true);
                    //On declenche l infection
                    infecterVille(tmpVille, carte.getCouleur());
                    //On enleve ensuite les marqueurs de parcours du graphe
                    enleverMarqueurPropagation(tmpVille);
                    //On remplace la ville par sa version modifie
                    LesElements.get(ETypeElement.Ville).remove(ville);
                    LesElements.get(ETypeElement.Ville).add(tmpVille);
                    
                }
            }
        }

    }

    public void InitialiseNouvellePartie() throws Exception {
        //On initialise le nombre d'actions des joueurs à 0, sauf le premier à 4
        for(String user : users) {
            LeNombreAction.put(user, 0);
        }
        String firstUser = users.get(0);
        LeNombreAction.put(firstUser,4);
        
        
        //On initialise une nouvelle partie
        this.FacadeDesCartes.newGame();
        this.FacadeDesElements.newGame();

        //On cree les pioches de cartes et les differents elements de jeu
        this.LesElements = FacadeDesElements.getLesElements();
        this.LesCartes = FacadeDesCartes.getLesCartes();
        
        //On melange la pioche infection
        Collections.shuffle(FacadeDesCartes.getLesCartes().get(ETypeCarte.Infection));
        
        //On sépare les cartes epidemies des cartes joueurs 
        ArrayList<Carte> Epidemie = new ArrayList<>(FacadeDesCartes.getLesCartes().get(ETypeCarte.Joueur).subList(0, 3));
        for (int cptEpidemie = 0; cptEpidemie <4; cptEpidemie++){
            FacadeDesCartes.getLesCartes().get(ETypeCarte.Joueur).remove(cptEpidemie);
        }
        //On melange la pioche joueur
        Collections.shuffle(FacadeDesCartes.getLesCartes().get(ETypeCarte.Joueur));
        
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
        for (int cptUser = 0; cptUser < users.size(); cptUser++) {
            indexAleatoire = randomGenerator.nextInt(LesCartes.get(ETypeCarte.Role).size());
            LesRoles.put(users.get(cptUser), (Role) LesCartes.get(ETypeCarte.Role).get(indexAleatoire));

            switch (LesCartes.get(ETypeCarte.Role).get(indexAleatoire).getName()) {
                case "Chercheuse":
                    LesPions.put(users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(2));
                    break;
                case "Expert":
                    LesPions.put(users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(4));
                    break;
                case "Medecin":
                    LesPions.put(users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(0));
                    break;
                case "Repartiteur":
                    LesPions.put(users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(3));
                    break;
                case "Scientifique":
                    LesPions.put(users.get(cptUser), (Pion) LesElements.get(ETypeElement.Pion).get(1));
                    break;
                default:  ;

            }

            LesCartes.get(ETypeCarte.Role).remove(indexAleatoire);
            
            //On creer la main du joueur
            LesMains.put(users.get(cptUser),new ArrayList<>());

           

            //On fait ensuite piocher au joueur 6 cartes 
            
             for(int cptPioche = 0; cptPioche <6; cptPioche++) {
              piocherCarte(users.get(cptUser)); 
             }
             
             //On incoropore ensuite les cartes epidemies
             melangerEpidemie(Epidemie);
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
       
        
        else {
            
           
            for (int cptVilleVoisine = 0; cptVilleVoisine < v.getVillesVoisines().size(); cptVilleVoisine++)
            {
                //Si la ville voisine n a pas ete propagee
                if(!v.getVillesVoisines().get(cptVilleVoisine).getPropagation()){
                    
                    
                    //On la marque comme propagee
                    v.getVillesVoisines().get(cptVilleVoisine).setPropagation(true);
                    //On infecte une ville voisine 
                    infecterVille(v.getVillesVoisines().get(cptVilleVoisine), couleur );
                                           
                }
            }
        }
        
        

    }
        
    public void piocherCarte(String user) {
        //Si il n'y a plus de carte à piocher la partie est perdue
        if (LesCartes.get(ETypeCarte.Joueur).size() < 1) {
            defaite();
        } 
        
        else {

            //On prend la prochaine carte dans la pioche
   
            Joueur tmpCarte = (Joueur)LesCartes.get(ETypeCarte.Joueur).get(0);
            
            //On la retire de la pioche
            LesCartes.get(ETypeCarte.Joueur).remove(0);

            //On regarde si la carte  est une épidémie
            if (tmpCarte.getName().equals("Epidemie_1")) {
                
                
                //On déplace le taux d'infection
                tauxInfection.augementerPositionPiste();

                //On tire la derniere carte infection ( si il en reste )
                if (LesCartes.get(ETypeCarte.Infection).size() > 0) {
                    
                    Infection carteInfection = (Infection) LesCartes.get(ETypeCarte.Infection).get(LesCartes.get(ETypeCarte.Infection).size()-1);
                    
                   //On met la carte dans la défausse
                    LesDefausses.get(ETypeCarte.Infection).add(carteInfection);
                    LesCartes.get(ETypeCarte.Infection).remove(0);

                    
                    //On trouve la ville a infecter parmis la liste
                    for (int villeCpt = 0; villeCpt < LesElements.get(ETypeElement.Ville).size(); villeCpt++) {
                        if (LesElements.get(ETypeElement.Ville).get(villeCpt).getName().equals(carteInfection.getVille())) {
                            Ville tmpVille = (Ville) LesElements.get(ETypeElement.Ville).get(villeCpt);
                            if(!maladiesEradiquees.contains(carteInfection.getCouleur()))
                            {
                                //On déclenche l'infection 3 fois
                                for (int i = 0; i < 3; i++) {
                                    //On marque la ville comme infectee
                                    tmpVille.setPropagation(true);
                                    //On declenche l infection
                                    infecterVille(tmpVille, carteInfection.getCouleur());
                                    //On enleve ensuite les marqueurs de parcours du graphe
                                    enleverMarqueurPropagation(tmpVille);
                                }
                                

                            }
                             LesElements.get(ETypeElement.Ville).remove(villeCpt);
                             LesElements.get(ETypeElement.Ville).add(tmpVille);
                        }
                    }



                    //On met la défausse Infection sur la pioche
                    for (Carte carteDeLaDefausse : LesDefausses.get(ETypeCarte.Infection)) {
                        LesCartes.get(ETypeCarte.Infection).add(0,carteDeLaDefausse);
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
    
    public void enleverMarqueurPropagation(Ville v){
    // A la fin d une propagation on remet les villes en etat non propagees
    v.setPropagation(false);
    for (int cptVilleVoisine = 0; cptVilleVoisine < v.getVillesVoisines().size(); cptVilleVoisine++)
    {
         if(v.getVillesVoisines().get(cptVilleVoisine).getPropagation()){
             enleverMarqueurPropagation(v.getVillesVoisines().get(cptVilleVoisine));
         }
    }
    
    }
    
    public void traiterMaladie(String user, ECouleur couleur){
           if(LeNombreAction.get(user)>0){
            //On recupere la valeur decouvert du remede de la bonne couleur
            boolean estDecouvert = false;
            for (Element remede : LesElements.get(ETypeElement.Remede))
            {
                Remede tmpRemede = (Remede)remede;
                if(tmpRemede.getCouleur().equals(couleur)){
                    estDecouvert = tmpRemede.getDecouvert();
                }
                
            }

            //On récupère la ville correspondant à la position du joueur 
            for (Element ville : LesElements.get(ETypeElement.Ville)) {
                if (ville.equals(LesPions.get(user).getPosition()))
                {   
                       
                    Ville tmpVille = (Ville)ville;  
                    //si le joueur est un medecin on retire tout les cubes
                    if(LesRoles.get(user).equals(ERole.Medecin) || estDecouvert  ) {
                    for(CubeMaladie cube :tmpVille.getInfection().get(couleur))
                        {
                          //On remet les cubes dans les stocks
                          LesElements.get(ETypeElement.CubeMaladie).add(cube);
                          
                          tmpVille.enleverInfection(couleur);

   
                        }
                        depenserAction(user);
                    }
                    
                    else{
                        //On creer un cube maladie qui s ajoute au stock
                        CubeMaladie cube = new CubeMaladie(couleur);                        
                        LesElements.get(ETypeElement.CubeMaladie).add(cube);
                        // On retire un cube Maladie
                        tmpVille.enleverInfection(couleur);
                        depenserAction(user);

                    }
                    
                    //On remplace la ville modifie dans la liste
                    LesElements.get(ETypeElement.Ville).remove(ville);
                    LesElements.get(ETypeElement.Ville).add(tmpVille);

                    
                }
            }
            
            }
    }
    
    public void melangerEpidemie(ArrayList<Carte> carteEpidemie){
        Random r = new Random();
        int indexRandom;
        for (int index=0; index <carteEpidemie.size(); index++){
            indexRandom = (index*LesCartes.get(ETypeCarte.Joueur).size()/carteEpidemie.size() ) + r.nextInt(LesCartes.get(ETypeCarte.Joueur).size()/carteEpidemie.size());
            LesCartes.get(ETypeCarte.Joueur).add(indexRandom,carteEpidemie.get(index));                       
        }
    }
    
    /**
     *
     * @param user
     * @param localisation
     */
    public void volDirect(String user, Localisation localisation) {

        if(LeNombreAction.get(user)>0){ 
            
        //On retire la carte de la main du joueur et on la met dans la défausse
        LesMains.get(user).remove(localisation);
        LesDefausses.get(ETypeCarte.Joueur).add(localisation);

        //On déplace le joueur sur la nouvelle ville
        Ville ville = getVille(localisation.getName());
        LesPions.get(user).setVille(ville);
        
        depenserAction(user);
        }
    }

    /**
     *
     * @param user
     * @param localisation
     * @param ville
     */
    public void volCharter(String user, Localisation localisation, Ville ville) {
        //On récupère la ville du user et la ville correspondante à la carte localisation
        Ville userVille = LesPions.get(user).getPosition();
        Ville locaVille = getVille(localisation.getName());

        //Si les villes correspondent, on peut faire un vol charter vers la ville sélectionnée
        if(userVille.equals(locaVille)) {

            //On retire la carte de la main du joueur et on la met dans la défausse
            LesMains.get(user).remove(localisation);
            LesDefausses.get(ETypeCarte.Joueur).add(localisation);

            //On déplace le joueur
            LesPions.get(user).setVille(ville);
        }
    }

    /**
     *
     * @param user
     * @param depart
     * @param arrivee
     */
    public void volNavette(String user, Ville depart, Ville arrivee) {
         if(LeNombreAction.get(user)>0){
        
        //Si les deux villes possèdent une station de recherche, le déplacement est possible
        if(depart.isHaveStation() && arrivee.isHaveStation()) {
            LesPions.get(user).setVille(arrivee);
            depenserAction(user);
        }
       }
    }

    /**
     *
     * @return les stations non affectées
     */
    public ArrayList<Station> getAvailableStation() {
        ArrayList<Station> result = new ArrayList<>();
        for(Element element : LesElements.get(ETypeElement.Station)) {
            Station station = (Station)(element);
            if(station.getPosition() == null){
                result.add(station);
            }
        }

        return result;
    }

    /**
     *
     * @param user
     * @param localisation
     * @param villeDeRepli
     */
    public void construireStation(String user, Localisation localisation, Ville villeDeRepli) {

        if(LeNombreAction.get(user)>0)
        {
            
        //On récupère la liste des stations non utilisées
        ArrayList<Station> availableStation = getAvailableStation();
        Ville userVille = LesPions.get(user).getPosition();

        if(LesRoles.get(user).equals(ERole.Expert)) {
            //Si il y a des stations de recherche
            if(availableStation.size()>0) {
                //On prend la première et on l'ajoute
                availableStation.get(0).setPosition(userVille);
            } else {
                villeDeRepli.setHaveStation(false);
            }
            userVille.setHaveStation(true);
        } else {
            Ville locaVille = getVille(localisation.getName());

            if(userVille.equals(locaVille)) {
                LesMains.get(user).remove(localisation);
                LesDefausses.get(ETypeCarte.Joueur).add(localisation);

                //Si il y a des stations de recherche
                if(availableStation.size()>0) {
                    //On prend la première et on l'ajoute
                    availableStation.get(0).setPosition(userVille);
                } else {
                    villeDeRepli.setHaveStation(false);
                }
                userVille.setHaveStation(true);
       
            }
         }
        }
    }

    /**
     *
     * @param nomVille
     * @return l'objet Ville correspondant au nom de la ville
     */
    public Ville getVille(String nomVille) {
       Ville result = null;
        for(Element element : LesElements.get(ETypeElement.Ville)){
            Ville ville = (Ville)(element);
            if(ville.getName() == nomVille){
                result = ville;
            }
        }
        return result;
    }

    /**
     *
     * @param userDonneur
     * @param userReceveur
     * @param carte
     */
    public void partageDeConnaissance(String userDonneur, String userReceveur, Carte carte){
        if(LesPions.get(userDonneur).getPosition().equals(LesPions.get(userReceveur).getPosition())){

            //Si le donneur est "Chercheuse"
            if(LesRoles.get(userDonneur).equals(ERole.Chercheuse)){
                LesMains.get(userDonneur).remove(carte);
                LesMains.get(userReceveur).add(carte);
            } else {
                //Il faut que la carte soit de type ville.
                if(carte instanceof Localisation){
                    LesMains.get(userDonneur).remove(carte);
                    LesMains.get(userReceveur).add(carte);
                }
            }

        }
    }

    public void decouvrirRemede(String user, ArrayList<Localisation> localisations) {
        if(LeNombreAction.get(user) > 0){
        //On vérifie que la ville actuelle du joueur possède une station de recherche
        if(LesPions.get(user).getPosition().isHaveStation()) {
            //On regarde si le joueur est du rôle "Scientifique"

                //On vérifie que les cartes sont de la même couleur
                ECouleur coulCarte = localisations.get(0).getCouleur();
                boolean memeCouleur = true;

                for (Localisation localisation : localisations) {
                    if(localisation.getCouleur() != coulCarte){
                        memeCouleur = false;
                    }
                }
                //Si les cartes sont de la même couleur, on peut donc développer le remède et les défausser
                if(memeCouleur && localisations.size() == 4 && LesRoles.get(user).equals(ERole.Scientifique)) {

                   List<Element> remedes = LesElements.get(ETypeElement.Remede);
                   for(Element element : remedes) {
                       Remede remede = (Remede)(element);

                       //Découverte du remède
                       if(remede.getCouleur().equals(coulCarte)){
                           remede.setDecouvert(true);
                           depenserAction(user);
                       }
                   }

                   //Suppression des cartes
                    for(Localisation localisation : localisations) {
                       LesMains.get(user).remove(localisation);
                    }
                } else if(memeCouleur && localisations.size() == 5) {
                    List<Element> remedes = LesElements.get(ETypeElement.Remede);
                    for(Element element : remedes) {
                        Remede remede = (Remede)(element);

                        //Découverte du remède
                        if(remede.getCouleur().equals(coulCarte)){
                            remede.setDecouvert(true);
                            depenserAction(user);

                        }
                    }

                    //Suppression des cartes
                    for(Localisation localisation : localisations) {
                        LesMains.get(user).remove(localisation);
                    }
                }

        }
        }

    }
    
    /**
     *
     * @param user
     */
    private void depenserAction(String user){
       LeNombreAction.put(user,LeNombreAction.get(user)-1);
    }
    
}
