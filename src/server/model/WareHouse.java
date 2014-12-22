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

/**
 *
 * @author Jose
 */
public class WareHouse 
{
    private HashMap<String, Item> armazem;
    private ReentrantLock hashLock;
    private Condition cond;
    
    public WareHouse()
    {
        this.armazem = new HashMap<String, Item>();
        this.hashLock = new ReentrantLock();
        cond = hashLock.newCondition();
    }
    
    
    public void supply(String nome, int quant)
    {
        hashLock.lock();
        
        try
        {
            if(armazem.containsKey(nome))
            {
                Item aux = armazem.get(nome);
                hashLock.unlock();
                aux.add(quant);
            }
            else
            {
                armazem.put(nome, new Item(nome, quant));
            }
        }finally{ hashLock.unlock(); }
    }
    
    public void requisicao(Tarefa t)
    {
        HashMap<Item, Integer> tarefas = t.getItens();
        Item aux;
        
        for (Map.Entry<Item, Integer> entry : tarefas.entrySet()) 
        {
            hashLock.lock();
            try
            {
                if( armazem.containsKey(entry.getKey().getNome()) )
                    aux = armazem.get(entry.getKey().getNome());
                else
                {
                    armazem.put( entry.getKey().getNome(), new Item(entry.getKey().getNome(), 0) );
                    aux = armazem.get(entry.getKey().getNome());
                }
            }finally{ hashLock.unlock(); }
            
            aux.retrieve(entry.getValue());
        }
    }
    
    public void devolver()
    {
        
    }
}
