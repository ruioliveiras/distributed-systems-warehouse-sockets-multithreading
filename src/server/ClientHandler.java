/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.Socket;
import shared.ComunicationSocket;
import shared.Facede;
import shared.SimpleExecption;

/**
 *
 * @author ruioliveiras s
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server parent;
    private ComunicationSocket cs;
    private Facede data;

    public ClientHandler(Server parent, Socket socket) throws IOException {
        this.parent = parent;
        this.socket = socket;
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
        try {
            do {
                
                int messageCode = cs.readMessage();
                try{
                    forwarder(messageCode);
                }catch(SimpleExecption se){
                    if (se.getLevel()> 1){
                        response(false, se.getMessage());
                    }else {
                        throw se;
                    }
                }
            } while (true);
        } catch (Exception e) {
            //error stop comunication
        }
    }

    private void forwarder(int messageCode) throws SimpleExecption {
        switch (messageCode) {
            case 1: response(data.addUser(cs.popString(), cs.popString()),"");
                return ; //function for code 1;
            case 2: response(data.editUser(cs.popString(), cs.popString()),"");
                return ;//function for code 2;
            case 3: response(data.listUser(cs.popString()),"");
                return ;//function for code 3;
            case 4: response(data.addObj(cs.popString()),"");
                return;
            case 5: response(data.remObj(cs.popString()),"");
                return;
            case 6: response(data.supplyObj(cs.popString(), cs.popInt()),"");
                return;
            case 7: response(data.listObj(),"");
                return;
            case 8: response(data.addTipoTarefa(cs.popString(), cs.popString(), cs.popArray("#")),"");
                return;
            case 9: response(data.editTipoTarefa(cs.popString(), cs.popString(), cs.popArray("#")),"");
                return;
            case 10: response(data.openTarefa(cs.popString(), cs.popString()),"");
                return;
            case 11: response(data.closeTarefa(cs.popString(), cs.popString()),"");
                return;
            case 12: response(data.statusTarefa(cs.popString(), cs.popString()),"");
                return;
            case 13: response(data.readyTarefa(cs.popString(), cs.popString()),"");
                return;
            case 14: response(data.finishedTarefa(cs.popString(), cs.popString()),"");
                return;
            case 15: response(data.listTarefa(cs.popString()),"");
                return;
            case 16: response(data.listTipoTarefa(cs.popString()),"");
                return;
        }
    }

    public void response(Boolean a, String message){
        cs.sendOK(a, message);
    }

    public void response(String[] a, String message){
        cs.sendOK(true, a);
    }
}
