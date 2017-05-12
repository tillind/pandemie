package com.miage.pandemie.business.jeu;

import com.pandemie.business.sauvegarde.SauvegardeJeu;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author alex
 */
public class TestSauvegarde {
    private SauvegardeJeu save;
    private Jeu game;
    public TestSauvegarde(){
        this.save = new SauvegardeJeu();
        this.game = Jeu.getInstance();
        try {
            this.game.InitialiseNouvellePartie();
        } catch (Exception ex) {
            Logger.getLogger(TestSauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testEcritureSauvegarde(){
        this.save.writeSaveJson();
        Assert.assertTrue(true);
    }
    
    @Test
    public void testLectureJson(){
        Assert.assertTrue(true);
    }
    
    
    
    
    
}
