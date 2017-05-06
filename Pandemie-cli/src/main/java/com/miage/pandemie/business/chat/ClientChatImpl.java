/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miage.pandemie.business.chat;

import com.miage.pandemie.controller.BoardController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author bach
 */
public class ClientChatImpl extends UnicastRemoteObject implements ClientChat{

    transient BoardController control;
    
    public ClientChatImpl(BoardController ctrl)throws RemoteException{
        super();
        this.control=ctrl;
    }

    @Override
    public void Message(String s,String Usr) throws RemoteException
    {
        this.control.addMessageChat("["+Usr+"] :"+s);
    }

    @Override
    public void RemoveUser(String s){
        this.control.addMessageChat("["+s+"] : est déconnectée ");
    }

    @Override
    public void AddUser(String s){
       this.control.addMessageChat("["+s+"] : est connectée ");
    }
}
