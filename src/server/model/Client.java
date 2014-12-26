/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.SimpleExecption;

/**
 *
 * @author Jose
 */
public class Client implements Lock{

    private final ReentrantLock lock = new ReentrantLock();
    private final String nome, password;
    private final HashMap<String, TipoTarefa> tarefas;
    private final HashMap<String, Tarefa> tarefasExec;
    private int tarefasExecCod;

    public Client() {
        this.tarefasExecCod = 0;
        this.nome = "";
        this.password = "";
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }

    public Client(String nome, String password) {
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

    public String[] allTipoTarefas() {
            String[] t = new String[tarefas.size()];
            int i = 0;
            for (TipoTarefa va : tarefas.values()) {
                t[i++] = va.getNome();
            }
            return t;
        
    }

    public String[] allTarefas() {
            String[] t = new String[tarefasExec.size()];
            int i = 0;
            for (Tarefa va : tarefasExec.values()) {
                t[i++] = va.getCodigo();
            }
            return t;
            }

    public void addTipoTarefa(TipoTarefa t) {
            tarefas.put(t.getNome(), t);
        
    }

    public String addTarefa(Tarefa t) {
            tarefasExecCod++;
            t.setCodigo(this.nome + "_" + tarefasExecCod);
            t.setConditionFinished(lock.newCondition());
            t.setConditionReady(lock.newCondition());
            tarefasExec.put(t.getCodigo(), t);
            return t.getCodigo();
    }

    public TipoTarefa getTipoTarefa(String tipoTarefa) throws SimpleExecption {
            TipoTarefa tt = tarefas.get(tipoTarefa);
            if (tt == null) {
                throw new SimpleExecption(3, "TipoTarefa", "Tipo de tareafa não Existe");
            }
            return tt;
    }

    public Tarefa getTarefa(String codTarefa) throws SimpleExecption{
            Tarefa t = tarefasExec.get(codTarefa);
            if (t == null) {
                throw new SimpleExecption(3, "Tarefa", "codigo tarefa não Existe");
            }
            return t;
    }

    public Tarefa remTarefa(String codTarefa) throws SimpleExecption{
            Tarefa t = tarefasExec.remove(codTarefa);
            if (t == null){
                throw new SimpleExecption(3,"Tarefa","codigo de tarefa não existe");
            }
            return t;
    }

    public void waitToFinished(String codTarefa) throws SimpleExecption{
        try {
            Tarefa t = getTarefa(codTarefa);
            t.getConditionFinished().await();
        } catch (InterruptedException ex) {
            throw new SimpleExecption(0, "INTERRUPT", "Interrupt exception have ocurred");
        } 
    }

    public void waitToReady(String codTarefa) throws SimpleExecption{
        try {
            Tarefa t = getTarefa(codTarefa);
            t.getConditionReady().await();
        } catch (InterruptedException ex) {
            throw new SimpleExecption(0, "INTERRUPT", "Interrupt exception have ocurred");
        }
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        lock.unlock();
    }

    @Override
    public Condition newCondition() {
        return lock.newCondition();
    }

}
