/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.pandemie.business.chat;

/**
 *
 * @author bach
 */
import com.miage.pandemie.controller.IndexController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bach
 */
public class ServeurChatImpl extends UnicastRemoteObject implements ServeurChat{
    ArrayList<ClientDistant> Clients = new ArrayList<>();
    ArrayList<String> Users=new ArrayList<>();
    
    transient IndexController program;
    
    public ServeurChatImpl()throws RemoteException{
    }
    
    @Override
    public void Connect(ClientDistant s,String User)throws RemoteException{
        notifyClt(User);
        Clients.add(s);
        Users.add(User);
        this.program.addElementToChat(User+" est connecté");
        Notify(s);
    }

    public void setController(IndexController control){
        this.program = control;
    }
    //On doit passer tous les clients quand un nouvau client ce connect
    public void Notify(ClientDistant s){
        Users.forEach((Usr) -> {
            try {
                s.AddUser(Usr);
            } catch (RemoteException ex) {
                Logger.getLogger(ServeurChatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //Quand un client ce Deconnect
    @Override
    public void Desconnect(ClientDistant s,String User)throws RemoteException{
        Clients.remove(s);
        Users.remove(User);
        this.program.addElementToChat(User+" est déconnecté");
        System.out.println(User);
        notifyRoom(User);
    }

    //Invocke par les clients pour des nouveaux messages
    @Override
    public void Getmessage(String s,String User)throws RemoteException{
        this.program.addElementToChat("["+User+"] : "+s);
        notifyClient(s,User);
    }

    
    //Informer les clients quand un nouveau message arrive
    void notifyClient(String s,String Usr) throws RemoteException{
        Clients.forEach((x) -> {
            try {
                x.Message(s,Usr);
            } catch (RemoteException e) {
                Logger.getLogger(ServeurChatImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    //Supprimer le client qui s'est Deconnecter
    void notifyRoom(String Usr){
        Clients.forEach((x) -> {
            try {
                x.RemoveUser(Usr);
            } catch (RemoteException ex) {
                Logger.getLogger(ServeurChatImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //Ajouter le client connecter dans les clients deja connecter
    void notifyClt(String Usr) throws RemoteException{
        for(ClientDistant clt:Clients){
            clt.AddUser(Usr);
        }
    }
    
    @Override
    public boolean equals(Object obj){      
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false; 
        ServeurChatImpl tmp = (ServeurChatImpl) obj;
        
        return this == tmp;            
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.Clients);
        hash = 43 * hash + Objects.hashCode(this.Users);
        hash = 43 * hash + Objects.hashCode(this.program);
        return hash;
    }

}   
