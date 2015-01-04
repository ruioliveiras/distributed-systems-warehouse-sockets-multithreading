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
import shared.SimpleExeption;

/**
 *
 * @author Jose
 */
public class Item {

    private int quantidade;
    private final String nome;
    private final Lock myLock;
    private final Condition myCond;

    /** Contructs a new Item
     * 
     */
    public Item() {
        this.quantidade = 0;
        this.nome = "";
        this.myLock = new ReentrantLock();
        this.myCond = myLock.newCondition();
    }

    /** Constructs a new Item with the name and the quantity
     * 
     * @param nome name of the Item
     * @param quantidade quantity of the Item
     */
    public Item(String nome, int quantidade) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.myLock = new ReentrantLock();
        this.myCond = myLock.newCondition();
    }

    /** Gets the name of the Item
     * 
     * @return the name of the Item
     */
    public String getNome() {
        return nome;
    }

    /** Gets the quantity of the Item
     * 
     * @return the quantity of the Item
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    /** Adds a certain ammount to an item 
     * 
     * @param quantidade ammount to be added
     */
    public void add(int quantidade) {
        myLock.lock();
        try {
            this.quantidade += quantidade;
            myCond.signalAll();
        } finally {
            myLock.unlock();
        }
    }

    /**
     * 
     * @throws SimpleExeption 
     */
    public void itemWait() throws SimpleExeption {
        myLock.lock();
        try {
            try {
                myCond.await();
            } catch (InterruptedException ex) {
                throw new SimpleExeption(0, "INTERRUPTION", "interruption inside item");
            }
        } finally {
            myLock.unlock();
        }
    }
    /** Retrieves a certain ammount of an Item
     * 
     * @param quantidade ammount to be retrieved
     * @return True if there is enough ammount to be retrived, False otherwise
     */
    public boolean retrieve(int quantidade) {

        myLock.lock();
        try {
            if (this.quantidade >= quantidade){
                this.quantidade -= quantidade;
                return true;
            }else{
                return false;
            }
        } finally {
            myLock.unlock();
        }
    }

    /**
     * Faz o hashCode do item so com a string do nome
     *
     * @return nome.hashCode
     */
    @Override
    public int hashCode() {
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
