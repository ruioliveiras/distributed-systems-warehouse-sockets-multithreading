/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jose
 */
public class Cliente 
{
    private String nome, password;
    private HashMap<String, Tarefa> tarefas;
    private HashMap<String, TarefaExecucao> tarefasExec;
    
    
    public Cliente()
    {
        this.nome = "";
        this.password = "";
        this.tarefas = new HashMap<String, Tarefa>();
        this.tarefasExec = new HashMap<String, TarefaExecucao>();
    }
    
    public HashMap<String, TarefaExecucao> getTarefasExec()
    {
        HashMap<String, TarefaExecucao> ret = new HashMap<String, TarefaExecucao>();
        
        for (Map.Entry<String, TarefaExecucao> entry : this.tarefasExec.entrySet()) 
        {
            ret.put(entry.getKey(), entry.getValue());      
        }
        
        return ret;
    }
    
    public void executarTarefa(String nome)
    {
        this.tarefasExec.put("Requisição objetos", new TarefaExecucao(this.tarefas.get(nome)) );
    }
    
    private void requisitarObjetosTarefa(TarefaExecucao t)
    {
        
    }
}
