/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DataFacede implements Facede {

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
        return clients.allClientNames();
    }

    @Override
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption {
        wareHouse.supply(nome, quantidade);
        return true;
    }

    @Override
    public Tuple<String[], Integer[]> listObj() throws SimpleExecption {
        return wareHouse.getAllObj();
    }

    @Override
    public Boolean addTipoTarefa(String user, String tipoTarefa, String[] objs, Integer[] quants) throws SimpleExecption {
        Item[] items = wareHouse.getItems(objs);
        Client c = clients.getCliente(user);
        try {
            TipoTarefa t = new TipoTarefa(tipoTarefa, items, quants);
            c.addTipoTarefa(t);
            return true;
        } finally {
            c.unlock();
        }
    }

    @Override
    public String[] listTipoTarefa(String user) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            return c.allTipoTarefas();
        } finally {
            c.unlock();
        }
    }

    @Override
    public String openTarefa(String user, String tipoTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            TipoTarefa tt = c.getTipoTarefa(tipoTarefa);
            Tarefa t = new Tarefa(tt);
            c.addTarefa(t);
            Thread thr = new Thread(this.warehouseWant(t));
            thr.start();
            return t.getCodigo();
        } finally {
            c.unlock();
        }
    }
    
    private Runnable warehouseWant(final Tarefa t) {
        return new Runnable() {
           @Override
            public void run() {
               try {
                   wareHouse.want(t);
                   t.setEstado("working");
               } catch (SimpleExecption ex) {
                   t.setEstado("Exection occurred:" + ex.getMessage());
               }
            }
        };
    }

    @Override
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            Tarefa t = c.remTarefa(codTarefa);
            wareHouse.dontWantMore(t);
            return true;
        } finally {
            c.unlock();
        }
    }

    @Override
    public String statusTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            return c.getTarefa(codTarefa).getEstado();
        } finally {
            c.unlock();
        }
    }

    @Override
    public Boolean login(String username, String password) throws SimpleExecption {
        Client c = clients.getCliente(username);
        try {
            if (!c.getPassword().equals(password)) {
                throw new SimpleExecption(2, "LOGIN", "invalid login or password");
            }
            return true;
        } finally {
            c.unlock();
        }
    }

    @Override
    public Boolean readyTarefa(String user, String codTarefa) throws SimpleExecption {

        Client c = clients.getCliente(user);
        try {
            c.waitToReady(codTarefa);
            return true;
        } finally {
            c.unlock();
        }
    }

    @Override
    public Boolean finishedTarefa(String user, String codTarefa) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            c.waitToFinished(codTarefa);
            return true;
        } finally {
            c.unlock();
        }
    }

    @Override
    public String[] listTarefa(String user) throws SimpleExecption {
        Client c = clients.getCliente(user);
        try {
            return c.allTarefas();
        } finally {
            c.unlock();
        }
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
