/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.model;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Jose
 */
public class Cliente 
{
    private final ReentrantLock clientsLock = new ReentrantLock();
    private String nome, password;
    private final HashMap<String, TipoTarefa> tarefas;
    private final HashMap<String, Tarefa> tarefasExec;
    private int tarefasExecCod;
    
    public Cliente() {
        this.tarefasExecCod = 0;
        this.nome = "";
        this.password = "";
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }
    
    public Cliente(String nome, String password) {
        this.tarefasExecCod = 0;
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

    public String[] getTipoTarefas() {
        String[] t = new String[tarefas.size()];
        int i = 0;
        for (TipoTarefa va : tarefas.values()) {
            t[i++] = va.getNome();
        }
        return t;
    }

    public String[] getTarefas() {
        String[] t = new String[tarefasExec.size()];
        int i = 0;
        for (TipoTarefa va : tarefasExec.values()) {
            t[i++] = va.getNome();
        }
        return t;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTipoTarefa(TipoTarefa t) {
        tarefas.put(t.getNome(), t);
    }

    public TipoTarefa getTipoTarefa(String tipoTarefa) {
        return tarefas.get(tipoTarefa);
    }

    public Tarefa getTarefa(String codTarefa) {
        return tarefasExec.get(codTarefa);
    }
    
    public String addTarefa(Tarefa t){
        tarefasExecCod++;
        t.setCodigo(this.nome + "_" + tarefasExecCod);
        tarefasExec.put(t.getCodigo(), t);
        return t.getCodigo();
    }
    
}
