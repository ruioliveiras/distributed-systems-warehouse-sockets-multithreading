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
import shared.SimpleExeption;

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
    public Boolean addUser(String username, String password) throws SimpleExeption {
        Client c = new Client(username, password);
        clients.addClient(c);
        return true;
    }

    @Override
    public String[] listUser(String username) throws SimpleExeption {
        return clients.allClientNames();
    }

    @Override
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExeption {
        wareHouse.supply(nome, quantidade);
        return true;
    }

    @Override
    public Tuple<String[], Integer[]> listObj() throws SimpleExeption {
        return wareHouse.getAllObj();
    }

    @Override
    public Boolean addTipoTarefa(String user, String tipoTarefa, String[] objs, Integer[] quants) throws SimpleExeption {
        Item[] items = wareHouse.getItems(objs);
        try (Client c = clients.getCliente(user)) {
            TipoTarefa t = new TipoTarefa(tipoTarefa, items, quants);
            c.addTipoTarefa(t);
            return true;
        }
    }

    @Override
    public String[] listTipoTarefa(String user) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            return c.allTipoTarefas();
        }
    }

    @Override
    public String openTarefa(String user, String tipoTarefa) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            TipoTarefa tt = c.getTipoTarefa(tipoTarefa);
            Tarefa t = new Tarefa(tt);
            c.addTarefa(t);
            Thread thr = new Thread(this.warehouseWant(user, t));
            thr.start();
            return t.getCodigo();
        }
    }

    private Runnable warehouseWant(final String user, final Tarefa t) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    // isto acontece porque os items de uma tarefa em execução nao podem ser modificados
                    wareHouse.want(t);
                    try (Client c = clients.getCliente(user)) {
                        t.setEstado("working");
                        t.getConditionReady().signalAll();
                    }
                } catch (SimpleExeption ex) {
                    t.setEstado("Exection occurred:" + ex.getMessage());
                }
            }
        };
    }

    @Override
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            Tarefa t = c.remTarefa(codTarefa);
            wareHouse.dontWantMore(t);
            t.getConditionFinished().signalAll();
            return true;
        }
    }

    @Override
    public String statusTarefa(String user, String codTarefa) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            return c.getTarefa(codTarefa).getEstado();
        }
    }

    @Override
    public Boolean login(String username, String password) throws SimpleExeption {
        try (Client c = clients.getCliente(username)) {
            if (!c.getPassword().equals(password)) {
                throw new SimpleExeption(2, "LOGIN", "invalid login or password");
            }
            return true;
        }
    }

    @Override
    public Boolean readyTarefa(String user, String codTarefa) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            c.waitToReady(codTarefa);
            return true;
        }
    }

    @Override
    public Boolean finishedTarefa(String user, String codTarefa) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            c.waitToFinished(codTarefa);
            return true;
        }
    }

    @Override
    public String[] listTarefa(String user) throws SimpleExeption {
        try (Client c = clients.getCliente(user)) {
            return c.allTarefas();
        }
    }

    @Override
    public String[][] listAllTarefa(String user) throws SimpleExeption {
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
