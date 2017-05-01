package com.miage.pandemie.business.jeu;


import com.miage.pandemie.controller.IndexController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.miage.pandemie.business.chat.ClientChat;

/**
 *
 * @author Remi
 */
public class ServeurJeuImpl extends UnicastRemoteObject implements ServeurJeu{

    private static final long serialVersionUID = 1L;
    
    public List<ClientJeu> lesClients;

    private Jeu leJeu;
    
    /**
     * @return the ctrl
     */
    public IndexController getController() {
        return ctrl;
    }

    /**
     * @param ctrl the ctrl to set
     */
    public void setController(IndexController ctrl) {
        this.ctrl = ctrl;
    }
    

    private IndexController ctrl;

    public ServeurJeuImpl()throws RemoteException{
        lesClients= new ArrayList<ClientJeu>();
        leJeu = Jeu.getInstance();
    }
    
    @Override
    public void Connect(ClientJeu s, String User) throws RemoteException {
        try {
            this.leJeu.addJoueur(User);
            this.lesClients.add(s);
            ctrl.addElementToGame("[ "+User+" ] :  est connectée");
        } catch (Exception ex) {
            Logger.getLogger(ServeurJeuImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Desconnect(ClientJeu s, String User) throws RemoteException {
        try {
            this.leJeu.removeJoueur(User);
            this.lesClients.remove(s);
              ctrl.addElementToGame("[ "+User+" ] :  est déconnectée");
        } catch (Exception ex) {
            Logger.getLogger(ServeurJeuImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      
       
      
}
