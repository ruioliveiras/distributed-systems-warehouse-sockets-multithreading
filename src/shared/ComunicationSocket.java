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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruioliveiras
 */
public class ComunicationSocket {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter bw;
    private String[] attrs;
    private int popIndex;

    public ComunicationSocket(Socket socket) throws IOException {
        this.socket = socket;
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        br = new BufferedReader(isr);

        OutputStreamWriter osr = new OutputStreamWriter(socket.getOutputStream());
        bw = new PrintWriter(osr);
    }

    public void sendMessage(int messageCode, String... attrs) {
        bw.print(messageCode + "," + attrs.length + ",");
        for (String attr : attrs) {
            bw.print(attr + ",");
        }
        bw.println("");
        bw.flush();
    }

    public int readMessage() throws SimpleExecption {
        String[] str;
        try {
            str = br.readLine().split(",");

            int messageCode, size;

            messageCode = Integer.parseInt(str[0]);
            size = Integer.parseInt(str[1]);
            attrs = new String[size];

            for (int i = 0; i < size; i++) {
                attrs[i] = str[i + 2];
            }
            popIndex = 0;
            return messageCode;

        } catch (IOException | NumberFormatException ex) {
            throw new SimpleExecption(1, "SOCKET", "error while reading");
        }
    }

    public void sendOK(boolean isOk, String message) {
        String sIsOk = (isOk) ? "OK" : "NOTOK";
        sendMessage(-1, "OK", message);
    }
    
    public void sendOK(boolean isOk, String[] message) {
        String sIsOk = (isOk) ? "OK" : "NOTOK";
        sendMessage(-1, "OK", String.join("#", message));
    }
    
    public boolean readOK() throws SimpleExecption {
        try {
            readMessage();
            String sIsOk = popString();
            if (sIsOk.equals("OK")) {
                return true;
            } else {
                throw new SimpleExecption(3, "SOCKET", popString());
            }
        } catch (Exception ex) {
            throw new SimpleExecption(1, "SOCKET", "error while reading OK message");
        }
    }

    public void sendSimple(boolean isOk, String message) {
        String sIsOk = (isOk) ? "OK" : "NOTOK";
        sendMessage(-1, "OK", message);
    }
    
    public int getInt(int i) throws SimpleExecption {
        try{
            return Integer.parseInt(attrs[i]);
        }catch(ArrayIndexOutOfBoundsException e){
            throw new SimpleExecption(2, "SOCKET", "getInt out of bounds" );
        }catch(NumberFormatException e){
            throw new SimpleExecption(2, "SOCKET", "GetInt but isn't int" );
        }
    }

    public String getString(int i) throws SimpleExecption {
        try{
            return attrs[i];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new SimpleExecption(2, "SOCKET", "GetString out of bounds" );
        }
    }

    public int popInt() throws SimpleExecption {
        return getInt(popIndex++);
    }

    public String popString() throws SimpleExecption {
        return getString(popIndex++);
    }

    public String[] popStringAll() throws SimpleExecption {
        String[] res = new String[attrs.length - popIndex];
        for (int i = 0; i < attrs.length - attrs.length; i++) {
            res[i] = attrs[i + attrs.length];
        }
        return res;
    }

    public String[] popArray(String delimiter) throws SimpleExecption {
        String s = popString();
        return s.split(delimiter);
    }
}
