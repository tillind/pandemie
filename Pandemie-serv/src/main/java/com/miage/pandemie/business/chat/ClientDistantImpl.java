/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.pandemie.business.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author bach
 */
public class ClientDistantImpl extends UnicastRemoteObject implements ClientDistant{

    public ClientDistantImpl()throws RemoteException{
    }

    @Override
    public void Message(String s,String Usr) throws RemoteException
    {
        System.out.println(Usr+" Say: "+s);
    }

    public void RemoveUser(String s){
        System.out.println(s);
    }

    public void AddUser(String s){
     System.out.println(s);
    }
}
