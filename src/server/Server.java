/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.Facede;
import shared.SimpleExeption;
import shared.Interface;

/**
 *
 * @author ruioliveiras
 */
public class Server implements Runnable{
    private ServerSocket ss;
    private Map<Integer,ClientHandler> requests;
    private Facede facade;
    
    
    public Server() throws IOException {
        ss = new ServerSocket(1050);
        facade = new DataFacede();
    }
    
    public void start() throws IOException{
        while(true){
            Socket cn = ss.accept();
            ClientHandler rn = new ClientHandler(Server.this, cn, facade);
            Thread t = new Thread(rn);           
            t.start();
        }
        
    }

    public Facede getFacade() {
        return facade;
    }

    @Override
    public void run() {
        try {
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initFacade(Facede f) throws SimpleExeption{
        f.addUser("admin", "admin");
        f.addUser("rui", "admin");
        f.addUser("pedro", "admin");
        f.addUser("azevedo", "admin");
        
        f.supplyObj("ratos", 12);
        f.supplyObj("teclados", 12);
        f.supplyObj("monitor", 12);
        f.supplyObj("portatil", 12);
        f.supplyObj("mochila", 12);
        f.supplyObj("calculadora", 12);
        
        String[] a = new String[2];
        Integer[] q = new Integer[2];
        a[0] = "ratos"; a[1] = "teclados"; q[0] = 3; q[1] = 4;
        f.addTipoTarefa("admin", "montar",a,q);
        
        System.out.println(Arrays.toString(f.listUser("admin")));
        System.out.println(Arrays.toString(f.listTipoTarefa("admin")));
        System.out.println(Arrays.toString(f.listTarefa("admin")));
        System.out.println(f.openTarefa("admin", "montar"));
        System.out.println(f.statusTarefa("admin", "admin_1"));
        System.out.println(Arrays.toString(f.listTarefa("admin")));

    }
    
    
    public static void main(String[] args) throws IOException, SimpleExeption {
        final Server rs = new Server();
        initFacade(rs.getFacade());
        Thread t = new Thread(rs);
        t.start();
        Interface ui = new Interface(rs.getFacade());
        ui.start();
    }

}
