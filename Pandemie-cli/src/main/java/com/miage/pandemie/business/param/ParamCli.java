/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.pandemie.business.param;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author alex
 */
public class ParamCli {
    private String ip;
    private String portChat;
    private String name;
    
    public ParamCli() throws UnknownHostException{
        ip ="127.0.0.1";
        portChat="1099";
        name=InetAddress.getLocalHost().getHostName();
    }
    
    public ParamCli(String ip,String portchat,String name){
        this.ip = ip;
        this.portChat = portchat;
        this.name = name;
        
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the portChat
     */
    public String getPortChat() {
        return portChat;
    }

    /**
     * @param portChat the portChat to set
     */
    public void setPortChat(String portChat) {
        this.portChat = portChat;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "ParamCli [ ip :"+this.ip+", portchat :"+this.portChat+", name :"+this.name+" ]";
    }
}
