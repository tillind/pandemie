package com.miage.pandemie.business.jeu;

import com.miage.pandemie.controller.IndexController;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Server {
    
    private String ip;
    private int port;
    private final String name="game" ;
    
    private Registry rmiRegistry;
    
    public Server(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    
    public void startServer(IndexController ctrl) throws RemoteException{
        if(LocateRegistry.getRegistry()==null){
            rmiRegistry = LocateRegistry.createRegistry(port);
        }else{
            rmiRegistry = LocateRegistry.getRegistry();
        }
        
        ServeurJeuImpl sj = new ServeurJeuImpl();
        sj.setController(ctrl);
        try{
            System.setProperty("java.rmi.server.hostname", this.ip);
            Naming.bind(name, sj);
        }catch (AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stopServer()
    {
        System.out.println("Stopping server");
        try {
            Naming.unbind(name);
            UnicastRemoteObject.unexportObject(this.rmiRegistry, true);
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
