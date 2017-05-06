/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.element.Element;
import com.miage.pandemie.business.element.Ville;
import com.miage.pandemie.business.enumparam.EVilleBleu;
import com.miage.pandemie.business.enumparam.EVilleJaune;
import com.miage.pandemie.business.enumparam.EVilleNoir;
import com.miage.pandemie.business.enumparam.EVilleRouge;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Remi
 */
public class FactoryVille implements IFactoryElement {

    @Override
    public List<Element> newGame() {
    ArrayList<Element> villes = new ArrayList<>();
        //Création des villes bleues
        Ville saintpetersbourg = new Ville("Saint_Petersburg", "bleu");
        Ville milan = new Ville("Milan", "bleu");
        Ville essen = new Ville("Essen", "bleu");
        Ville paris = new Ville("Paris", "bleu");
        Ville londres = new Ville("Londres", "bleu");
        Ville madrid = new Ville("Madrid", "bleu");
        Ville newyork = new Ville("New York", "bleu");
        Ville washington = new Ville("Washington", "bleu");
        Ville toronto = new Ville("Toronto", "bleu");
        Ville chicago = new Ville("Chicago", "bleu");
        Ville atlanta = new Ville("Atlanta", "bleu");
        Ville sanfrancisco = new Ville("San_Francisco", "bleu");

        //Création des villes noires
        Ville algers = new Ville("Algers", "noir");
        Ville istanbul = new Ville("Istanbul", "noir");
        Ville caire = new Ville("Le_Caire", "noir");
        Ville riyadh = new Ville("Riyadh", "noir");
        Ville baghdad = new Ville("Baghdad", "noir");
        Ville karachi = new Ville("Karachi", "noir");
        Ville moscou = new Ville("Moscou", "noir");
        Ville tehran = new Ville("Tehran", "noir");
        Ville delhi = new Ville("Delhi", "noir");
        Ville kolkata = new Ville("Kolkata", "noir");
        Ville mumbai = new Ville("Mumbai", "noir");
        Ville chennai = new Ville("Chennai", "noir");

        //Création des villes rouges
        Ville beijing = new Ville("Beijing", "rouge");
        Ville seoul = new Ville("Seoul", "rouge");
        Ville tokyo = new Ville("Tokyo", "rouge");
        Ville shanghai = new Ville("Shanghai", "rouge");
        Ville osaka = new Ville("Osaka", "rouge");
        Ville taipei = new Ville("Taipei", "rouge");
        Ville hongkong = new Ville("Hong_Kong", "rouge");
        Ville manila = new Ville("Manila", "rouge");
        Ville bangkok = new Ville("Bangkok", "rouge");
        Ville jakarta = new Ville("Jakarta", "rouge");
        Ville hochiminh = new Ville("Ho_Chi_Minh_City", "rouge");
        Ville sydney = new Ville("Sydney", "rouge");

        //Création des villes jaunes
        Ville losangeles = new Ville("Los_Angeles", "jaune");
        Ville mexico = new Ville("Mexico_City", "jaune");
        Ville miami = new Ville("Miami", "jaune");
        Ville bogota = new Ville("Bogota", "jaune");
        Ville lima = new Ville("Lima", "jaune");
        Ville santiago = new Ville("Santiago", "jaune");
        Ville buenosaires = new Ville("Buenos_Aires", "jaune");
        Ville saopaulo = new Ville("Sao_Paulo", "jaune");
        Ville lagos = new Ville("Lagos", "jaune");
        Ville khartoum = new Ville("Khartoum", "jaune");
        Ville kinshasa = new Ville("Kinshasa", "jaune");
        Ville johannesburg = new Ville("Johannsburg", "jaune");

        //Création des liens de voisinages
        saintpetersbourg.ajouterVoisinage(moscou);
        saintpetersbourg.ajouterVoisinage(istanbul);
        saintpetersbourg.ajouterVoisinage(essen);
        villes.add(saintpetersbourg);
        
        essen.ajouterVoisinage(milan);
        essen.ajouterVoisinage(paris);
        essen.ajouterVoisinage(londres);
        villes.add(essen);

        milan.ajouterVoisinage(istanbul);
        milan.ajouterVoisinage(paris);
        villes.add(milan);

        paris.ajouterVoisinage(londres);
        paris.ajouterVoisinage(madrid);
        paris.ajouterVoisinage(algers);
        villes.add(paris);

        londres.ajouterVoisinage(newyork);
        londres.ajouterVoisinage(madrid);
        villes.add(londres);

        madrid.ajouterVoisinage(algers);
        madrid.ajouterVoisinage(newyork);
        madrid.ajouterVoisinage(saopaulo);
        villes.add(madrid);

        newyork.ajouterVoisinage(toronto);
        newyork.ajouterVoisinage(washington);
        villes.add(newyork);

        toronto.ajouterVoisinage(washington);
        toronto.ajouterVoisinage(chicago);
        villes.add(toronto);

        washington.ajouterVoisinage(atlanta);
        washington.ajouterVoisinage(miami);
        villes.add(washington);

        atlanta.ajouterVoisinage(miami);
        atlanta.ajouterVoisinage(chicago);
        villes.add(atlanta);

        chicago.ajouterVoisinage(mexico);
        chicago.ajouterVoisinage(sanfrancisco);
        chicago.ajouterVoisinage(losangeles);
        villes.add(chicago);

        sanfrancisco.ajouterVoisinage(losangeles);
        sanfrancisco.ajouterVoisinage(tokyo);
        sanfrancisco.ajouterVoisinage(manila);
        villes.add(sanfrancisco);

        losangeles.ajouterVoisinage(sydney);
        losangeles.ajouterVoisinage(mexico);
        villes.add(losangeles);

        mexico.ajouterVoisinage(miami);
        mexico.ajouterVoisinage(bogota);
        mexico.ajouterVoisinage(lima);
        villes.add(mexico);

        miami.ajouterVoisinage(bogota);
        villes.add(miami);

        bogota.ajouterVoisinage(lima);
        bogota.ajouterVoisinage(saopaulo);
        bogota.ajouterVoisinage(buenosaires);
        villes.add(bogota);

        lima.ajouterVoisinage(santiago);
        villes.add(lima);

        buenosaires.ajouterVoisinage(saopaulo);
        villes.add(buenosaires);

        saopaulo.ajouterVoisinage(lagos);
        villes.add(saopaulo);

        lagos.ajouterVoisinage(kinshasa);
        lagos.ajouterVoisinage(khartoum);
        villes.add(lagos);

        kinshasa.ajouterVoisinage(khartoum);
        kinshasa.ajouterVoisinage(johannesburg);
        villes.add(kinshasa);

        johannesburg.ajouterVoisinage(khartoum);
        villes.add(johannesburg);

        khartoum.ajouterVoisinage(caire);
        villes.add(khartoum);

        caire.ajouterVoisinage(algers);
        caire.ajouterVoisinage(riyadh);
        caire.ajouterVoisinage(baghdad);
        caire.ajouterVoisinage(istanbul);
        villes.add(caire);

        algers.ajouterVoisinage(istanbul);
        villes.add(algers);

        istanbul.ajouterVoisinage(moscou);
        istanbul.ajouterVoisinage(baghdad);
        villes.add(istanbul);

        
        baghdad.ajouterVoisinage(riyadh);
        baghdad.ajouterVoisinage(karachi);
        baghdad.ajouterVoisinage(tehran);
        villes.add(baghdad);

        riyadh.ajouterVoisinage(karachi);
        villes.add(riyadh);

        moscou.ajouterVoisinage(tehran);
        villes.add(moscou);

        tehran.ajouterVoisinage(delhi);
        villes.add(tehran);

        karachi.ajouterVoisinage(mumbai);
        karachi.ajouterVoisinage(delhi);
        villes.add(karachi);

        delhi.ajouterVoisinage(mumbai);
        delhi.ajouterVoisinage(kolkata);
        delhi.ajouterVoisinage(chennai);
        villes.add(delhi);

        mumbai.ajouterVoisinage(chennai);
        villes.add(mumbai);

        chennai.ajouterVoisinage(kolkata);
        chennai.ajouterVoisinage(bangkok);
        chennai.ajouterVoisinage(jakarta);
        villes.add(chennai);

        kolkata.ajouterVoisinage(hongkong);
        kolkata.ajouterVoisinage(bangkok);
        villes.add(kolkata);

        bangkok.ajouterVoisinage(jakarta);
        bangkok.ajouterVoisinage(hochiminh);
        bangkok.ajouterVoisinage(hongkong);
        villes.add(bangkok);

        jakarta.ajouterVoisinage(hochiminh);
        jakarta.ajouterVoisinage(sydney);
        villes.add(jakarta);

        sydney.ajouterVoisinage(manila);
        villes.add(sydney);

        manila.ajouterVoisinage(hochiminh);
        manila.ajouterVoisinage(hongkong);
        manila.ajouterVoisinage(taipei);
        villes.add(manila);

        taipei.ajouterVoisinage(hongkong);
        taipei.ajouterVoisinage(osaka);
        taipei.ajouterVoisinage(shanghai);
        villes.add(taipei);

        hongkong.ajouterVoisinage(shanghai);
        villes.add(hongkong);
        

        shanghai.ajouterVoisinage(beijing);
        shanghai.ajouterVoisinage(seoul);
        shanghai.ajouterVoisinage(tokyo);
        villes.add(shanghai);

        beijing.ajouterVoisinage(seoul);
        villes.add(beijing);

        seoul.ajouterVoisinage(tokyo);
        villes.add(seoul);

        tokyo.ajouterVoisinage(osaka);
        villes.add(tokyo);
        
        villes.add(santiago);
        villes.add(hochiminh);
        villes.add(osaka);
        
        return villes;
    }

    @Override
    public List<Element> saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
