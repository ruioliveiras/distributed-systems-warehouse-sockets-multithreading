/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdwarehouse;

import java.util.HashMap;

/**
 *
 * @author Jose
 */
public class Cliente 
{
    private String nome, password;
    private HashMap<String, Tarefa> tarefas;
    private HashMap<String, TarefaExecucao> tarefasExec;

    public Cliente() {
        this.nome = "";
        this.password = "";
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }
    
    public Cliente(String nome, String password) {
        this.nome = nome;
        this.password = password;
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, Tarefa> getTarefas() {
        return tarefas;
    }

    public HashMap<String, TarefaExecucao> getTarefasExec() {
        return tarefasExec;
    }
    
}
