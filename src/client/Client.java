/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
public class Client {
    private Socket cliente;
    private BufferedReader br;
    private PrintWriter bw;
  
    /**
     *
     * @throws IOException
     */
    public Client() throws IOException {
        cliente = new Socket("localhost", 1050);

        InputStreamReader isr = new InputStreamReader( cliente.getInputStream());
        br = new BufferedReader(isr);
        
        OutputStreamWriter osr=new OutputStreamWriter( cliente.getOutputStream());
        bw = new PrintWriter(osr);
    }
    
    
    /*methods */
    public int sendValue(int value) throws IOException{
        bw.println(value);
        bw.flush();
        String str = br.readLine();
        int ret;
        try{
            ret = Integer.parseInt(str);
        }catch (NumberFormatException nf){
            ret = 0;
        }
        
        return ret;
    }
    
    public void sms(String s){
        sendMessage(1, s);
    }
    
    private void sendMessage(int messageCode, String ... attrs){
        bw.print(messageCode + "," + attrs.length + ",");
        for (String attr : attrs) {
            bw.print(attr + ",");
        }
        bw.println("");
        bw.flush();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Client rc = new Client();
        while(true) {
            
            rc.sms("ola ");
            Thread.sleep(1000l);
        }
     //    System.out.println(rc.sendValue(1));
//        System.out.println(rc.sendValue(2));
//        System.out.println(rc.sendValue(3));
//        System.out.println(rc.sendValue(4));
//        System.out.println(rc.sendValue(5));
//        System.out.println(rc.sendValue(6));
        
    }
}
