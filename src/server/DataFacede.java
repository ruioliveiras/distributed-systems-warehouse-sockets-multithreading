/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.Cliente;
import server.model.Item;
import server.model.TipoTarefa;
import server.model.Tarefa;
import server.model.WareHouse;
import shared.Facede;
import shared.KeyValue;
import shared.SimpleExecption;

/**
 *
 * @author ruioliveiras
 */
public class DataFacede implements Facede{
    
    private final WareHouse wareHouse;
    
    private final HashMap<String, Cliente> clients;
    private final ReentrantLock clientsLock;

    
    public DataFacede() {
        this.clients = new HashMap<>();
        this.clientsLock = new ReentrantLock();
        this.wareHouse = new WareHouse();
    }
    
    private Cliente getCliente(String user) throws SimpleExecption {
        Cliente c = this.clients.get(user);
        if (c == null){
            throw new SimpleExecption(3, "User", "Utilizador não Existe");
        }
        return c;
    }
    
    private TipoTarefa getTipoTarefa(String user,String name) throws SimpleExecption{
        Cliente c = getCliente(user);
        TipoTarefa tt = c.getTipoTarefa(name);
        if (tt == null){
            throw new SimpleExecption(3, "User", "Tipo de tarefa não Existe");
        }
        return tt;
    }
   
    private Tarefa getTarefa(String user,String name) throws SimpleExecption{
        Cliente c = getCliente(user);
        Tarefa t = c.getTarefa(name);
        if (t == null){
            throw new SimpleExecption(3, "User", "Codigo de tarefa não Existe");
        }
        return t;
    }
    
    
    @Override
    public Boolean addUser(String username, String password) throws SimpleExecption {
        Cliente c = new Cliente(username, password);
        clientsLock.lock();
        try {
            clients.put(username, c);
        } finally {
            clientsLock.unlock();
        }
        return true;
    }

    @Override
    public String[] listUser(String username) throws SimpleExecption {
        clientsLock.lock();
        try {
            int i = 0 ;
            String[] ret = new String[clients.size()];
            for (Cliente value : clients.values()) {
                ret[i] = value.getNome();
                i++;
            }
            return ret;
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption {
       wareHouse.supply(nome, quantidade);
       return true;
    }

    @Override
    public KeyValue<String[],Integer[]> listObj() throws SimpleExecption {
        return wareHouse.getAllObj();
    }

    @Override
    public Boolean addTipoTarefa(String user, String tipoTarefa,String[] objs,Integer[] quants) throws SimpleExecption {
        TipoTarefa t = new TipoTarefa(tipoTarefa);
        t.addItems(objs,quants);
        clientsLock.lock();
        try {
            Cliente c = getCliente(user);
            c.addTipoTarefa(t);
            return true;
        } finally {
            clientsLock.unlock();
        }
    }
    
    @Override
    public String[] listTipoTarefa(String user) throws SimpleExecption {
        String ret[];
        clientsLock.lock();
        try {
            Cliente c = getCliente(user);
            return c.getTipoTarefas();
        } finally {
            clientsLock.unlock();
        }
     }
    
    @Override
    public String openTarefa(String user, String tipoTarefa) throws SimpleExecption {
        clientsLock.lock();
        try {
            Cliente c = getCliente(user);
            TipoTarefa tt = getTipoTarefa(user, tipoTarefa);
            Tarefa t = new Tarefa(tt);
            wareHouse.want(t);
            c.addTarefa(t);
            return t.getCodigo();
        } catch (InterruptedException ex) {
            throw new SimpleExecption(0, "System Interrupted", "Need to close");
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExecption {
        clientsLock.lock();
        try {
            Tarefa te = getTarefa(user, codTarefa);
            wareHouse.dontWantMore(te);
            return true;
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public String statusTarefa(String user, String codTarefa) throws SimpleExecption {
        clientsLock.lock();
        try {
            Tarefa te = getTarefa(user, codTarefa);
            return te.getEstado();
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public Boolean login(String username, String password) throws SimpleExecption {
        clientsLock.lock();
        try {
            Cliente c = getCliente(username);
            return c.getPassword().equals(password);
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public Boolean readyTarefa(String user, String codTarefa) throws SimpleExecption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean finishedTarefa(String user, String codTarefa) throws SimpleExecption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] listTarefa(String user) throws SimpleExecption {
        clientsLock.lock();
        try {
            Cliente c = getCliente(user);
            return c.getTarefas();
        } finally {
            clientsLock.unlock();
        }
    }

    @Override
    public String[][] listAllTarefa(String user) throws SimpleExecption {
        String[] ret[];
        clientsLock.lock();
        try {
            int i=0;
            ret = new String[clients.size()][];
            for (Cliente value : clients.values()) {
                ret[i++] = value.getTipoTarefas();
            }
            return ret;
        } finally {
            clientsLock.unlock();
        }
    
    }


    
}
