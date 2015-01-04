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
import shared.SimpleAutoCloseable;
import shared.SimpleExeption;

/**
 *
 * @author Jose
 */
public class Client implements Lock, SimpleAutoCloseable{

    private final ReentrantLock lock = new ReentrantLock();
    private final String nome, password;
    private final HashMap<String, TipoTarefa> tarefas;
    private final HashMap<String, Tarefa> tarefasExec;
    private int tarefasExecCod;
    
    /** Constructs a new Client
     * 
     */
    public Client() {
        this.tarefasExecCod = 0;
        this.nome = "";
        this.password = "";
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }

    /** Constructs a new Client with a name and a password
     * 
     * @param nome name of the Client
     * @param password password of the Client
     */
    public Client(String nome, String password) {
        this.tarefasExecCod = 0;
        this.nome = nome;
        this.password = password;
        this.tarefas = new HashMap<>();
        this.tarefasExec = new HashMap<>();
    }

    /** Gets the name of the client
     * 
     * @return the name of the client
     */
    public String getNome() {
        return nome;
    }

    /** Gets the password of the client
     * 
     * @return the password of the client
     */
    public String getPassword() {
        return password;
    }
    
    /** Returns all TipoTarefas from the client
     * 
     * @return list of all TipoTarefas from the client
     */
    public String[] allTipoTarefas() {
            String[] t = new String[tarefas.size()];
            int i = 0;
            for (TipoTarefa va : tarefas.values()) {
                t[i++] = va.getNome();
            }
            return t;
        
    }
    
    /** Returns all executing Tarefa from the client
     * 
     * @return list of all Tarefa from the client
     */
    public String[] allTarefas() {
            String[] t = new String[tarefasExec.size()];
            int i = 0;
            for (Tarefa va : tarefasExec.values()) {
                t[i++] = va.getCodigo();
            }
            return t;
    }
    
    /** Adds a TipoTarefa
     * 
     * @param t TipoTarefa to be added
     */
    public void addTipoTarefa(TipoTarefa t) {
            tarefas.put(t.getNome(), t);
        
    }
    
    /** Adds a Tarefa
     * 
     * @param t Tarefa to be added
     * @return the code of Tarefa
     */
    public String addTarefa(Tarefa t) {
            tarefasExecCod++;
            t.setCodigo(this.nome + "_" + tarefasExecCod);
            t.setConditionFinished(lock.newCondition());
            t.setConditionReady(lock.newCondition());
            tarefasExec.put(t.getCodigo(), t);
            return t.getCodigo();
    }
    
    /** Gets a TipoTarefa
     * 
     * @param tipoTarefa the identifier of the tipoTarefa
     * @return 
     * @throws SimpleExeption 
     */
    public TipoTarefa getTipoTarefa(String tipoTarefa) throws SimpleExeption {
            TipoTarefa tt = tarefas.get(tipoTarefa);
            if (tt == null) {
                throw new SimpleExeption(3, "TipoTarefa", "Tipo de tareafa não Existe");
            }
            return tt;
    }
    
    /** Gets a Tarefa
     * 
     * @param codTarefa code of the Tarefa
     * @return the Tarefa
     * @throws SimpleExeption 
     */
    public Tarefa getTarefa(String codTarefa) throws SimpleExeption{
            Tarefa t = tarefasExec.get(codTarefa);
            if (t == null) {
                throw new SimpleExeption(3, "Tarefa", "codigo tarefa não Existe");
            }
            return t;
    }
    
    /** Removes a Tarefa
     * 
     * @param codTarefa code of Tarefa
     * @return
     * @throws SimpleExeption 
     */
    public Tarefa remTarefa(String codTarefa) throws SimpleExeption{
            Tarefa t = tarefasExec.remove(codTarefa);
            if (t == null){
                throw new SimpleExeption(3,"Tarefa","codigo de tarefa não existe");
            }
            return t;
    }
    
    /**
     * 
     * @param codTarefa code of Tarefa
     * @throws SimpleExeption 
     */
    public void waitToFinished(String codTarefa) throws SimpleExeption{
        try {
            Tarefa t = getTarefa(codTarefa);
            t.getConditionFinished().await();
        } catch (InterruptedException ex) {
            throw new SimpleExeption(0, "INTERRUPT", "Interrupt exception have ocurred");
        } 
    }
    
    /**
     * 
     * @param codTarefa code of Tarefa
     * @throws SimpleExeption 
     */
    public void waitToReady(String codTarefa) throws SimpleExeption{
        try {
            Tarefa t = getTarefa(codTarefa);
            t.getConditionReady().await();
        } catch (InterruptedException ex) {
            throw new SimpleExeption(0, "INTERRUPT", "Interrupt exception have ocurred");
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

    @Override
    public void close() throws SimpleExeption {
        lock.unlock();
    }

}
