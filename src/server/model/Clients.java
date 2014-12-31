/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import shared.SimpleExeption;

/**
 *
 * @author ruioliveiras
 */
public class Clients {
    private final HashMap<String, Client> clients;
    private final ReentrantLock clientsLock;

    public Clients() {
        this.clients = new HashMap<>();
        this.clientsLock = new ReentrantLock();
    }
    
    public Client getCliente(String user) throws SimpleExeption {
        clientsLock.lock();
        try {
            Client c = this.clients.get(user);
            if (c == null){
                throw new SimpleExeption(3, "User", "Utilizador não Existe");
            }
            c.lock();
            return c;
        } finally {
            clientsLock.unlock();
        }
    }
    
    public void addClient(Client c){
        clientsLock.lock();
        try {
            clients.put(c.getNome(), c);
        } finally {
            clientsLock.unlock();
        }
    }
    
    public String[] allClientNames(){
        clientsLock.lock();
        try {
            int i = 0 ;
            String[] ret = new String[clients.size()];
            for (Client value : clients.values()) {
                ret[i] = value.getNome();
                i++;
            }
            return ret;
        } finally {
            clientsLock.unlock();
        }
    }
}
