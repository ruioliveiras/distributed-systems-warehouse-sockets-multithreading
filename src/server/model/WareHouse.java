/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import shared.Tuple;

/**
 *
 * @author Jose
 */
public class WareHouse 
{   
    private final HashMap<String, Client> clientes;
    private final HashMap<String, Item> armazem;
    private final ReentrantLock clientsLock;
    private final ReentrantLock armazemLock;
    private Condition cond;
    
    
    public WareHouse()
    {
        this.armazem = new HashMap<>();
        this.clientes = new HashMap<>();
        this.armazemLock = new ReentrantLock();
        this.clientsLock = new ReentrantLock();
        cond = armazemLock.newCondition();
    }
    
    private Item getOrCreate(String nome){
        Item item = armazem.get(nome);
        if (item == null) {
            item = new Item(armazemLock.newCondition(), nome, 0);
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
       
    public void want(Tarefa t) throws InterruptedException {
        //Item[] ps = new Item[t.];
        HashMap<String, Integer> itens = t.getItens();
        armazemLock.lock();
        try{
            boolean needToRepeat = true;
            while(needToRepeat){
                needToRepeat = false;
                for (Map.Entry<String, Integer> entry : itens.entrySet()) {   
                    String itemName = entry.getKey();
                    Item item = getOrCreate(itemName);
                    if (item.getQuantidade() <= entry.getValue() ){
                        while(item.getQuantidade()<= entry.getValue()) { 
                            item.myWait();
                        }
                        needToRepeat = true;
                        break;
                    }
                }
            }
            
            // if it are there then is have run all the array and 
            
            for (Map.Entry<String, Integer> entry : itens.entrySet()) {   
                Item item = armazem.get(entry.getKey());
                item.retrieve(entry.getValue());
            }
            t.setEstado("working");
        } finally {
            armazemLock.unlock();
        }
    }
    
    public void dontWantMore(Tarefa t)
    {
        HashMap<String, Integer> itens = t.getItens();
        
        for (Map.Entry<String, Integer> entry : itens.entrySet()) 
        {
            this.supply(entry.getKey(), entry.getValue());
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

    public Item[] getItems(String[] objs) {
        clientsLock.lock();
        try {
            Item[] res = new Item[objs.length];
            int i = 0;
            for (String obj : objs) {
                res[i] = armazem.get(obj);
                if (res[i] == null){
                    res[i] = new Item(armazemLock.newCondition(),obj, 0);
                    armazem.put(obj, res[i]);
                }
                i++;
            }
            return res;
        } finally {
            clientsLock.unlock();
        }
    }

}
