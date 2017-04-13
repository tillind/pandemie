package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.element.Element;
import java.util.List;

/**
 *
 * @author alex
 */
public interface IFactoryElement {
    public List<Element> newGame();
    public List<Element> saveGame();
    
}
