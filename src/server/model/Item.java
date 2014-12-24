/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Jose
 */
public class Item {
    private int quantidade;
    private String nome;
    private Lock l;
    private Condition nVazio;
    private Condition mCondition;
    private int cCounter;
    
    public Item(Condition condition){
        this.cCounter = 0;
        this.quantidade = 0;
        this.nome = "";
        this.l = new ReentrantLock();
        this.nVazio = l.newCondition();
        this.mCondition = condition;
    }
    
    public Item(Condition condition, String nome, int quantidade){
        this.cCounter = 0;
        this.quantidade = quantidade;
        this.nome = nome;
        this.l = new ReentrantLock();
        this.nVazio = l.newCondition();
        this.mCondition = condition;
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

    public void myWait() throws InterruptedException{
        int snap;
        cCounter++;
        do{
            snap = cCounter;
            mCondition.await();
        }while (snap > 0);

        cCounter--;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
}
