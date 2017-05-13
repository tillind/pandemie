/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.pandemie.business.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author alex
 */
public class ClientChatImpl extends UnicastRemoteObject implements ClientChat{

    public ClientChatImpl()throws RemoteException{
    }

    @Override
    public void Message(String s, String User) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RemoveUser(String s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AddUser(String s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
