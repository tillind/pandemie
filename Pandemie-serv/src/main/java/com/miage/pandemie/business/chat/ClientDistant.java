/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.pandemie.business.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alex
 */
public interface ClientDistant extends Remote{
    void Message(String s,String User) throws RemoteException;
    void RemoveUser(String s) throws RemoteException;
    void AddUser(String s) throws RemoteException;
}
