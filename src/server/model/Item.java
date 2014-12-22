/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Item {
    private int quantidade;
    private String nome;
    private Lock l;
    private Condition nVazio;

    public Item(){
    
        this.quantidade = 0;
        this.nome = "";
        this.l = new ReentrantLock();
        this.nVazio = l.newCondition();
    }
    
    public Item(String nome, int quantidade){
    
        this.quantidade = quantidade;
        this.nome = nome;
        this.l = new ReentrantLock();
        this.nVazio = l.newCondition();
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public void add(int quantidade){
    
        l.lock();
        try{
            this.quantidade += quantidade;
            nVazio.signal();
        }
        finally{ l.unlock();}
    }
    
    public void retrieve(int quantidade){
    
        l.lock();
        try{
            while(quantidade<=0){
                try {
                    nVazio.await();
                } catch (InterruptedException ex){}
            }
            this.quantidade -= quantidade;
        }
        finally{ l.unlock();}
    }
    
    
    
    /**
     * Faz o hashCode do item so com a string do nome
     * @return nome.hashCode
     */
    @Override
    public int hashCode() 
    {
        return nome.hashCode();
    }
    
    
}
