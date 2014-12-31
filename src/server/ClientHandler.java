/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ComunicationSocket;
import shared.Facede;
import shared.SimpleExeption;

/**
 *
 * @author ruioliveiras s
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server parent;
    private ComunicationSocket cs;
    private Facede data;
    private boolean autenticado;

    public ClientHandler(Server parent, Socket socket, Facede facade) throws IOException {
        this.parent = parent;
        this.data = facade;
        this.socket = socket;
        this.autenticado = false;
    }

    private void init() throws IOException {
        cs = new ComunicationSocket(socket);
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
        
        int messageCode = -500;
        try {
            do {
                
                messageCode = cs.readMessage();
                try{
                    forwarder(messageCode);
                }catch(SimpleExeption se){
                    if (se.getLevel()> 1){
                        response(false, se.getMessage());
                    }else {
                        throw se;
                    }
                }
            } while (true);
        } catch (SimpleExeption e) {
            try{
                response(false, "Erro interno");
                socket.shutdownInput();
                socket.shutdownOutput();
            }catch(Exception e1){}
           System.err.println(messageCode+ " - "+e.getMessage());
        }
    }

    private void forwarder(int messageCode) throws SimpleExeption {
        String str = "string";
        Integer i = 1;
        Integer[] iArray = {1};
        String[] strArray = {"a"};
        
        //Esta autenticado
        if (messageCode != 1 && messageCode != 17 && !autenticado){
            throw new SimpleExeption(3, "AUtenticação", "Nao pode efeturar esta ação porque nao esta autenticado");
        }
        
        switch (messageCode) {
            case 1: response(data.addUser(cs.pop(str), cs.pop(str)),"");
                return ; //function for code 1;
            case 2:// response(data.editUser(cs.popString(), cs.popString()),"");
                break ;//function for code 2;
            case 3: response(data.listUser(cs.pop(str)),"");
                return ;//function for code 3;
            case 4:// response(data.addObj(cs.popString()),"");
                break;
            case 5:// response(data.remObj(cs.popString()),"");
                break;
            case 6: response(data.supplyObj(cs.pop(str), cs.pop(i)),"");
                return;
            case 7: response(data.listObj(),"");
                return;
            case 8: response(data.addTipoTarefa(cs.pop(str), cs.pop(str), cs.pop(strArray), cs.pop(iArray)),"");
                return;
            case 9:// response(data.editTipoTarefa(cs.popString(), cs.popString(), cs.popArray("#"), cs.popArrayInt("#")),"");
                return;
            case 10: response(data.openTarefa(cs.pop(str), cs.pop(str)),"");
                return;
            case 11: response(data.closeTarefa(cs.pop(str), cs.pop(str)),"");
                return;
            case 12: response(data.statusTarefa(cs.pop(str), cs.pop(str)),"");
                return;
            case 13: response(data.readyTarefa(cs.pop(str), cs.pop(str)),"");
                return;
            case 14: response(data.finishedTarefa(cs.pop(str), cs.pop(str)),"");
                return;
            case 15: response(data.listTarefa(cs.pop(str)),"");
                return;
            case 16: response(data.listTipoTarefa(cs.pop(str)),"");
                return;
            case 17:Boolean b = data.login(cs.pop(str),cs.pop(str)); 
                autenticado = b;
                response(b,"");
                return;
        }
        throw new SimpleExeption(1, "ClienHANDLER", "Invalid message code" + messageCode);
    }

    public void response(Boolean a, String message) throws SimpleExeption{
        cs.sendOK(a, message);
    }
    
    public <T> void response(T a, String message) throws SimpleExeption{
        cs.sendOK(true, a);
    }
}
