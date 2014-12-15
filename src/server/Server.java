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

/**
 *
 * @author ruioliveiras
 */
public class Server {
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

    
    public static void main(String[] args) throws IOException {
        Server rs = new Server();
        rs.start();
    }
}
