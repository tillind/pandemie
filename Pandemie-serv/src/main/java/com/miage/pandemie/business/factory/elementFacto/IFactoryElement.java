package com.miage.pandemie.business.factory.elementFacto;

import com.miage.pandemie.business.element.Element;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author alex
 */
public interface IFactoryElement  {
     List<Element> newGame();
     List<Element> saveGame();
    
}
