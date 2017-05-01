package com.miage.pandemie.business.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author bach
 */
public interface ServeurChat extends Remote{
    void Connect(ClientChat s,String User) throws RemoteException;
    void Desconnect(ClientChat s,String User) throws RemoteException;
    void Getmessage(String s,String User) throws RemoteException;
}
