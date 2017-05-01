/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.chat;

import com.miage.pandemie.controller.IndexController;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
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
public class ServerChat {
    
    private String ip ;
    private int port ;
    private final String name ="chat";
    private Registry rmiRegistry;
    
    
    public ServerChat(String ip, int port){
        this.ip = ip;
        this.port= port;
    }
    
    public void startServer(IndexController ctrl) throws RemoteException{ 
        rmiRegistry = LocateRegistry.createRegistry(port);
        ServeurChatImpl sc = new ServeurChatImpl();
        sc.setController(ctrl);
        try {
            System.setProperty("java.rmi.server.hostname",this.ip);
            Naming.bind(name, sc);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

import com.miage.pandemie.controller.IndexController;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
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
public class ServerChat {
    
    private String ip ;
    private int port ;
    private final String name ="chat";
    private Registry rmiRegistry;
    
    
    public ServerChat(String ip, int port){
        this.ip = ip;
        this.port= port;
    }
    
    public void startServer(IndexController ctrl) throws RemoteException{ 
        rmiRegistry = LocateRegistry.createRegistry(port);
        ServeurChatImpl sc = new ServeurChatImpl();
        sc.setController(ctrl);
        try {
            System.setProperty("java.rmi.server.hostname",this.ip);
            Naming.bind(name, sc);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.chat;

import com.miage.pandemie.controller.IndexController;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
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
public class ServerChat {
    
    private String ip ;
    private int port ;
    private final String name ="chat";
    private Registry rmiRegistry;
    
    
    public ServerChat(String ip, int port){
        this.ip = ip;
        this.port= port;
    }
    
    public void startServer(IndexController ctrl) throws RemoteException{ 
        //if(LocateRegistry.getRegistry()==null){
            rmiRegistry = LocateRegistry.createRegistry(port);
        //}else{
          //  rmiRegistry = LocateRegistry.getRegistry();
        //}
        
        ServeurChatImpl sc = new ServeurChatImpl();
        sc.setController(ctrl);
        try {
            System.setProperty("java.rmi.server.hostname",this.ip);
            Naming.bind(name, sc);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}