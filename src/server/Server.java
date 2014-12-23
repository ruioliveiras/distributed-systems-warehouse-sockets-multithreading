/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.SimpleExecption;
import shared.Interface;

/**
 *
 * @author ruioliveiras
 */
public class Server implements Runnable{
    private ServerSocket ss;
    private Map<Integer,ClientHandler> requests;
    
    
    public Server() throws IOException {
        ss = new ServerSocket(1050);
    }
    
    public void start() throws IOException{
        while(true){
            Socket cn = ss.accept();
            ClientHandler rn = new ClientHandler(Server.this, cn);
            Thread t = new Thread(rn);           
            t.start();
        }
        
    }

    @Override
    public void run() {
        try {
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args) throws IOException {
        Server rs = new Server();
        Thread t = new Thread(rs);
        t.start();
        Interface ui = new Interface(new DataFacede());
        try {   
            ui.start();
        } catch (SimpleExecption e) {
            System.out.println("Execption "+ e.getMessage() + " Server will stop");
        }
    }

}
