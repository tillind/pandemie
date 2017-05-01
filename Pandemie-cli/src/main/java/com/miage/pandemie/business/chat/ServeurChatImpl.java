package com.miage.pandemie.business.chat;

/**
 *
 * @author bach
 */
import com.miage.pandemie.controller.BoardController;
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
    ArrayList<ClientChat> Clients = new ArrayList<>();
    ArrayList<String> Users=new ArrayList<>();
    
    transient BoardController program;
    
    public ServeurChatImpl()throws RemoteException{
    }
    
    @Override
    public void Connect(ClientChat s,String User)throws RemoteException{
        notifyClt(User);
        Clients.add(s);
        Users.add(User);
        this.program.addMessageChat(User+" est connecté");
        Notify(s);
    }

    public void setController(BoardController control){
        this.program = control;
    }
    //On doit passer tous les clients quand un nouvau client ce connect
    public void Notify(ClientChat s){
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
    public void Desconnect(ClientChat s,String User)throws RemoteException{
        Clients.remove(s);
        Users.remove(User);
        this.program.addMessageChat(User+" est déconnecté");
        notifyRoom(User);
    }

    //Invocke par les clients pour des nouveaux messages
    @Override
    public void Getmessage(String s,String User)throws RemoteException{
        this.program.addMessageChat("["+User+"] : "+s);
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
        for(ClientChat clt:Clients){
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
