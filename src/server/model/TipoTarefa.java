/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import shared.Tuple;

/**
 *
 * @author Jose
 */
public class TipoTarefa 
{
    private final String nome;
    //possivel problema apesar de ser de leitura, 
    //private final HashMap<Item, Integer> items;
    private Item[] items;
    private Integer[] quatys;
    
    public TipoTarefa(String nome, Item[] items,Integer[] quatys )
    {
        this.nome = nome;
        this.items = items;
        this.quatys = quatys;
    }
    
    public TipoTarefa(TipoTarefa f)
    {
        this.nome = f.getNome();
        this.items = f.getItems();
        this.quatys = f.getQuatys();
    }
    
    public String getNome()
    {
        return this.nome;
    }

    
    public Tuple<Item[],Integer[]> getItensTuple()
    {
        return new Tuple<>(items, quatys);
    }    

    public Item[] getItems() {
        return items;
    }

    public Integer[] getQuatys() {
        return quatys;
    }
    
//    
//    public HashMap<Item, Integer> getItens()
//    {
//        HashMap<Item, Integer> ret = new HashMap<Item, Integer>();
//        
//        for (Map.Entry<Item, Integer> entry : items.entrySet()) 
//        {
//            ret.put(entry.getKey(), entry.getValue());      
//        }
//        
//        return ret;
//    }

    
    @Override
    public TipoTarefa clone()
    {
        return new TipoTarefa(this);
    }

//    public void addItems(Item[] objetos, Integer[] integer) {
//        int i =0 ;
//        for (Item objeto : objetos) {
//            items.put(objeto, integer[i++]);
//        }
//    }


    
}
