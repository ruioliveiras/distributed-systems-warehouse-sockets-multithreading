/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.model.Client;
import server.model.Clients;
import server.model.Item;
import server.model.TipoTarefa;
import server.model.Tarefa;
import server.model.WareHouse;
import shared.Facede;
import shared.Tuple;
import shared.SimpleExecption;

/**
 *
 * @author ruioliveiras
 */
public class DataFacede implements Facede{
    
    private final WareHouse wareHouse;
    private final Clients clients;
    
    public DataFacede() {
        this.wareHouse = new WareHouse();
        this.clients = new Clients();
    }
    
    
    @Override
    public Boolean addUser(String username, String password) throws SimpleExecption {
        Client c = new Client(username, password);
        clients.addClient(c);
        return true;
    }

    @Override
    public String[] listUser(String username) throws SimpleExecption {
        Client[] cs = clients.allClient();
        String[] ret = new String[cs.length];
            
        for (int i = 0; i < ret.length; i++) {
            ret[i] = cs[i].getNome();
        }
        return ret;
    }

    @Override
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption {
       wareHouse.supply(nome, quantidade);
       return true;
    }

    @Override
    public Tuple<String[],Integer[]> listObj() throws SimpleExecption {
        return wareHouse.getAllObj();
    }

    @Override
    public Boolean addTipoTarefa(String user, String tipoTarefa,String[] objs,Integer[] quants) throws SimpleExecption {
        Item[] items = wareHouse.getItems(objs);
        Client c = clients.getCliente(user);
        
        TipoTarefa t = new TipoTarefa(tipoTarefa,items,quants);
        c.addTipoTarefa(t);
        return true;
    }
    
    @Override
    public String[] listTipoTarefa(String user) throws SimpleExecption {
        Client c = clients.getCliente(user);
        return c.allTarefas();
    }
    
    @Override
    public String openTarefa(String user, String tipoTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        TipoTarefa tt = c.getTipoTarefa(tipoTarefa);
        Tarefa t = new Tarefa(tt);
        wareHouse.want(t);
        c.addTarefa(t);
        return t.getCodigo();
    }

    @Override
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        Tarefa t = c.remTarefa(codTarefa);
        wareHouse.dontWantMore(t);
        return true;
    }

    @Override
    public String statusTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        return c.getTarefa(codTarefa).getEstado();
    }

    @Override
    public Boolean login(String username, String password) throws SimpleExecption {
        Client c =clients.getCliente(username);
        if(!c.getPassword().equals(password)){
            throw new SimpleExecption(2, "LOGIN", "invalid login or password");
        }
        return true;
    }

    @Override
    public Boolean readyTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        c.waitToReady(codTarefa);
        return true;    
    }

    @Override
    public Boolean finishedTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        c.waitToFinished(codTarefa);
        return true;
    }

    @Override
    public String[] listTarefa(String user) throws SimpleExecption {
        Client c = clients.getCliente(user);
        return c.allTarefas();
    }

    @Override
    public String[][] listAllTarefa(String user) throws SimpleExecption {
//        String[] ret[];
//        clientsLock.lock();
//        try {
//            int i=0;
//            ret = new String[clients.size()][];
//            for (Client value : clients.values()) {
//                ret[i++] = value.allTipoTarefas();
//            }
//            return ret;
//        } finally {
//            clientsLock.unlock();
//        }
//    ret
        return null;
    }


    
}
