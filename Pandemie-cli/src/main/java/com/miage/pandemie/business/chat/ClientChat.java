package com.miage.pandemie.business.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author bach
 */
public interface ClientChat extends Remote{
    void Message(String s,String User) throws RemoteException;
    void RemoveUser(String s) throws RemoteException;
    void AddUser(String s) throws RemoteException;
}
