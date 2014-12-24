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
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;
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
    private final ComunicationSerializer serializer;

    public ComunicationSocket(Socket socket) throws IOException {
        this.serializer = new ComunicationSerializer();
        this.socket = socket;
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        br = new BufferedReader(isr);

        OutputStreamWriter osr = new OutputStreamWriter(socket.getOutputStream());
        bw = new PrintWriter(osr);
    }

    public <T> void sendMessage(int messageCode, T... attrs) throws SimpleExecption {
        bw.print(messageCode + "," + attrs.length + ",");
        for (T attr : attrs) {
            bw.print(serializer.serialize(attr) + ",");
        }
        bw.println("");
        bw.flush();
    }

    
    public int readMessage() throws SimpleExecption {
        String[] str;
        try {
            String aux = br.readLine();
            if (aux == null){
                //comunication ended;
                socket.shutdownOutput(); 
                socket.shutdownInput();
                return -1;
            }
            str = aux.split(",");

            int messageCode, size;

            messageCode = Integer.parseInt(str[0]);
            size = Integer.parseInt(str[1]);
            attrs = new String[size];

            for (int i = 0; i < size && i + 2 < str.length; i++) {
                attrs[i] = str[i + 2];
            }
            popIndex = 0;
            return messageCode;

        } catch (IOException | NumberFormatException ex) {
            throw new SimpleExecption(1, "SOCKET", "error while reading");
        }
    }
    
    public <T> void sendOK(boolean isOk, T message) throws SimpleExecption {
        String sIsOk = (isOk) ? "OK" : "NOTOK";
        sendMessage(-1, sIsOk, serializer.serialize(message));
    }

    public boolean readOK() throws SimpleExecption {
//        try {
        readMessage();
        String sIsOk = pop("String");
        if (sIsOk.equals("OK")) {
            return true;
        } else {
            throw new SimpleExecption(3, "SOCKET", pop("String"));
        }
//        } catch (Exception ex) {
//            throw new SimpleExecption(1, "SOCKET", "error while reading OK message");
//        }
    }

    public <T> T get(int index, T t) throws SimpleExecption {
        if (index < attrs.length && index >= 0){
            return serializer.descerialize(attrs[index], t);
        }
        throw  new SimpleExecption(1, "SOCKET", "Invalid number arguments: " + index);
    }
    
    public <T> T pop(T t) throws SimpleExecption {
       return get(popIndex++, t);
    }
}
