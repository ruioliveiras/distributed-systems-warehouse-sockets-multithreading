/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ruioliveiras
s */
public class ClientHandler implements Runnable{

    private Server parent;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter bw;
    private String[] attrs;
    
    public ClientHandler(Server parent, Socket socket) throws IOException {
        this.parent = parent;
        this.socket = socket;
    }
    
    private void init() throws IOException{
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        br = new BufferedReader(isr);
        OutputStreamWriter osr=new OutputStreamWriter(socket.getOutputStream());
        bw = new PrintWriter(osr);
    }
    
    
    @Override
    public void run() {
        try {
            init();
        } catch (IOException ex) {
            return;
        }
        //Loop while not EOF with switch case of commands
        //      for each case their will be a method defined in this class
        try {
            boolean r;
            do{
                int messageCode = readMessage();
                r = forwarder(messageCode);
            }while(r);
        } catch (Exception e) {
            //error stop comunication
        }
    }
    
    private boolean forwarder(int messageCode) throws Exception{
       
        switch(messageCode){
            case 1:
               return false; //function for code 1;
            case 2:
               return false;//function for code 2;
            case 3:
               return false;//function for code 3;
            default: 
                return false;
        }
    }
    
    private int readMessage() throws IOException, NumberFormatException{
        String[] str = br.readLine().split(",");
        int messageCode, size;
        
        messageCode = Integer.parseInt(str[0]);
        size = Integer.parseInt(str[1]);
        attrs = new String[size];
        
        for (int i = 0; i < size; i++) {
            attrs[i] = str[i+2];
        }
        return messageCode;
    }
   
    
}