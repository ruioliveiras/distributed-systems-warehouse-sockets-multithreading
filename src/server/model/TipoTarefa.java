/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import server.model.Item;
import shared.KeyValue;

/**
 *
 * @author Jose
 */
public class TipoTarefa 
{
    private String nome;
    private HashMap<String, Integer> itens;

    public TipoTarefa()
    {
        this.nome = "";
        this.itens = new HashMap<String, Integer>();
    }
    
    public TipoTarefa(String nome)
    {
        this.nome = nome;
        this.itens = new HashMap<String, Integer>();
    }
    
    public TipoTarefa(TipoTarefa f)
    {
        this.nome = f.getNome();
        this.itens = f.getItens();
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public void addItem(String it, int quant)
    {
        this.itens.put(it, quant);
    }

    public HashMap<String, Integer> getItens()
    {
        HashMap<String, Integer> ret = new HashMap<String, Integer>();
        
        for (Map.Entry<String, Integer> entry : itens.entrySet()) 
        {
            ret.put(entry.getKey(), entry.getValue());      
        }
        
        return ret;
    }
    
    public TipoTarefa clone()
    {
        return new TipoTarefa(this);
    }

    public void addItems(String[] objetos, Integer[] integer) {
        int i =0 ;
        for (String objeto : objetos) {
            itens.put(objeto, integer[i++]);
        }
    }
}
