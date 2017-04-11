package com.miage.pandemie.business.factory.carteFacto;

import com.miage.pandemie.business.Enum.EDifficulter;
import com.miage.pandemie.business.carte.Carte;
import java.util.List;

/**
 *
 * @author alex
 */
public interface IFactoryCarte {
    public List<Carte> newGame();
    public List<Carte> savedGame();
}
