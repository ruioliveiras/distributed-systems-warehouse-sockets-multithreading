/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import shared.SimpleExeption;
import shared.Tuple;

/**
 *
 * @author Jose
 */
public class WareHouse 
{   
    private final HashMap<String, Item> armazem;
    private final ReentrantLock armazemLock;
    
    public WareHouse()
    {
        this.armazem = new HashMap<>();
        this.armazemLock = new ReentrantLock();
    }
    
    private Item getOrCreate(String nome){
        Item item = armazem.get(nome);
        if (item == null) {
            item = new Item(nome, 0);
            armazem.put(nome, item);
        }
        return item;
    }
    
    public void supply(String nome, int quant)
    {
        armazemLock.lock();
        try
        {
            Item it = getOrCreate(nome);
            it.add(quant);
        }finally{ armazemLock.unlock(); }
    }
    
    /** Apesar de estar dentro de WareHouse esta função nao precisa de controlo 
     * de concorrencia do armazemLock porque nao usa o hashMap, tem acesso direto
     * aos items.
     * 
     * @param t
     * @throws SimpleExeption 
     */
    public void want(Tarefa t) throws SimpleExeption {
        Tuple<Item[],Integer[]> tq = t.getItensTuple();
        Item[] items = tq.getA();
        Integer[] quaty = tq.getB();
        int i = 0, iWait;
        
        do{
            i =0; iWait = -1; 
            for (i = 0; i < items.length; i++) {
                if (! items[i].retrieve(quaty[i])){
                    iWait = i;
                    break;
                }
            }
            // devolve se nao consegui ter todos:
            if (i < items.length){
                for (i = 0; i < iWait; i++) {
                    items[i].add(quaty[i]);
                }
                //este wait nao causo deadlock aqui no armazem porque o armazemLock
                //nao esta a ser usado
                items[iWait].itemWait();
            }
            
        }while(iWait != -1);
    }
    
    public void dontWantMore(Tarefa t)
    {
        Item[] it = t.getItems();
        Integer[] qu = t.getQuatys();
        
        for (int i = 0; i < it.length; i++) {
            it[i].add(qu[i]);
        }
    }
 
    public Tuple<String[],Integer[]> getAllObj() {
        String[] values = new String[armazem.size()];
        Integer[] quats = new Integer[armazem.size()];
        int i = 0;
        for (Item va : armazem.values()) {
            values[i] = va.getNome();
            quats[i] = va.getQuantidade();
            i++;
        }
        return new Tuple<>(values,quats);
    }

    /** Get Items just to Have you reference to items, the items are not locked
     *  
     * @param objs
     * @return 
     */
    public Item[] getItems(String[] objs) {
        armazemLock.lock();
        try {
            Item[] res = new Item[objs.length];
            int i = 0;
            for (String obj : objs) {
                res[i] = getOrCreate(obj);
                i++;
            }
            return res;
        } finally {
            armazemLock.unlock();
        }
    }

}
