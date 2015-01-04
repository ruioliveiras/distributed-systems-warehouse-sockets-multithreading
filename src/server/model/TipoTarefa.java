/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

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
    private final Item[] items;
    private final Integer[] quatys;
    
    /** Constructs a new TipoTarefa with the name, the items and the quantity needed for each Item
     * 
     * @param nome the name
     * @param items the items
     * @param quatys the quantity
     */
    public TipoTarefa(String nome, Item[] items,Integer[] quatys )
    {
        this.nome = nome;
        this.items = items;
        this.quatys = quatys;
    }
    
    /** Constructs a new TipoTarefa from another TipoTarefa
     * 
     * @param f TipoTarefa to be copied
     */
    public TipoTarefa(TipoTarefa f)
    {
        this.nome = f.getNome();
        this.items = f.getItems();
        this.quatys = f.getQuatys();
    }
    
    /** Gets the name of TipoTarefa
     * 
     * @return the name of TipoTarefa
     */
    public String getNome()
    {
        return this.nome;
    }

    /**
     * 
     * @return a tuple with the name of the the items and the respective amount
     */
    public Tuple<Item[],Integer[]> getItensTuple()
    {
        return new Tuple<>(items, quatys);
    }    

    /** Gets the Items of TipoTarefa
     * 
     * @return the list with the Items of TipoTarefa
     */
    public Item[] getItems() {
        return items;
    }

    /** Gets the quantity needed for each Item
     * 
     * @return the list with the quantity needed for each Item
     */
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
