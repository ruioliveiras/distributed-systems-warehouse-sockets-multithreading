/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.Socket;
import shared.ComunicationSocket;

import shared.Facede;
import shared.Tuple;
import shared.SimpleExecption;

/**
 *
 * @author ruioliveiras
 */
public class Client implements Facede {

    private Socket cliente;
    private ComunicationSocket cs;

    /**
     *
     * @throws IOException
     */
    public Client() throws IOException {
        cliente = new Socket("localhost", 1050);
        cs = new ComunicationSocket(cliente);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client rc = new Client();
        while (true) {
//            rc.sms("ola ");
            Thread.sleep(1000l);
        }
//    System.out.println(rc.sendValue(1));
//        System.out.println(rc.sendValue(2));
//        System.out.println(rc.sendValue(3));
//        System.out.println(rc.sendValue(4));
//        System.out.println(rc.sendValue(5));
//        System.out.println(rc.sendValue(6));
    }

    @Override
    public Boolean addUser(String username, String password) throws SimpleExecption {
        cs.sendMessage(1, username, password);
        return cs.readOK();
    }

    @Override
    public String[] listUser(String username) throws SimpleExecption {
        cs.sendMessage(3, username);
        cs.readOK();
        String str[] = {"str"};
        return cs.pop(str);
    }

    @Override
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption {
        cs.sendMessage(6, nome, Integer.toString(quantidade));
        return cs.readOK();
    }

    @Override
    public Tuple<String[],Integer[]> listObj() throws SimpleExecption {
        String str[] = {"str"}; Integer ints[] = {1};
        Tuple<String[],Integer[]> tu = new Tuple<>(str,ints);
        cs.sendMessage(7);
        cs.readOK();
        return cs.pop(tu);
    }

    @Override
    public Boolean addTipoTarefa(String username, String tipoTarefa, String[] objetos, Integer[] quanty) throws SimpleExecption {
        cs.sendMessage(8, username, tipoTarefa, objetos, quanty);
        return cs.readOK();
    }

    @Override
    public String openTarefa(String username, String tipoTarefa) throws SimpleExecption {
        cs.sendMessage(10, username, tipoTarefa);
        cs.readOK();
        return cs.pop("String");
    }

    @Override
    public Boolean closeTarefa(String username, String codTarefa) throws SimpleExecption {
        cs.sendMessage(11, username, codTarefa);
        return cs.readOK();
    }

    @Override
    public String statusTarefa(String username, String codTarefa) throws SimpleExecption {
        cs.sendMessage(12, username, codTarefa);
        cs.readOK();
        return cs.pop("string");
    }

    @Override
    public Boolean readyTarefa(String username, String codTarefa) throws SimpleExecption {
        cs.sendMessage(13, username, codTarefa);
        return cs.readOK();
    }

    @Override
    public Boolean finishedTarefa(String username, String codTarefa) throws SimpleExecption {
        cs.sendMessage(14, username, codTarefa);
        return cs.readOK();
    }

    @Override
    public String[] listTarefa(String username) throws SimpleExecption {
        cs.sendMessage(15, username);
        cs.readOK();
        String str[] = {"str"};
        return cs.pop(str);
    }

    @Override
    public String[] listTipoTarefa(String username) throws SimpleExecption {
        cs.sendMessage(16, username);
        cs.readOK();
        String str[] = {"str"};
        return cs.pop(str);
    }

    @Override
    public Boolean login(String username, String password) throws SimpleExecption {
        cs.sendMessage(17, username, password);
        return cs.readOK();
    }

    @Override
    public String[][] listAllTarefa(String user) throws SimpleExecption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
