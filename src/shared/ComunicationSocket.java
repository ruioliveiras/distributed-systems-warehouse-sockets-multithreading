/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ruioliveiras
 */
public class ComunicationSocket {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter bw;
    private String[] attrs;

    public ComunicationSocket(Socket socket) throws IOException {
        this.socket = socket;
        InputStreamReader isr = new InputStreamReader( socket.getInputStream());
        br = new BufferedReader(isr);
        
        OutputStreamWriter osr=new OutputStreamWriter( socket.getOutputStream());
        bw = new PrintWriter(osr);
    }

    
    public void sendMessage(int messageCode, String ... attrs){
        bw.print(messageCode + "," + attrs.length + ",");
        for (String attr : attrs) {
            bw.print(attr + ",");
        }
        bw.println("");
        bw.flush();
    }
    
    public int readMessage() throws IOException, NumberFormatException{
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
