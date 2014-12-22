/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jose
 */
public class Tarefa 
{
    private String nome;
    private HashMap<Item, Integer> itens;

    public Tarefa()
    {
        this.nome = "";
        this.itens = new HashMap<Item, Integer>();
    }
    
    public Tarefa(String nome)
    {
        this.nome = nome;
        this.itens = new HashMap<Item, Integer>();
    }
    
    public Tarefa(Tarefa f)
    {
        this.nome = f.getNome();
        this.itens = f.getItens();
    }
    
    public String getNome(){ return this.nome; }
    public void setNome(String s) { this.nome = s; }
    
    public void addItem(Item it, int quant)
    {
        this.itens.put(it, quant);
    }
    
    public HashMap<Item, Integer> getItens()
    {
        HashMap<Item, Integer> ret = new HashMap<Item, Integer>();
        
        for (Map.Entry<Item, Integer> entry : this.itens.entrySet()) 
        {
            ret.put(entry.getKey(), entry.getValue());      
        }
        
        return ret;
    }
    
    public Tarefa clone(){ return new Tarefa(this); }
}
