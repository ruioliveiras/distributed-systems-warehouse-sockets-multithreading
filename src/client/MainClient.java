/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.SimpleExecption;
import shared.Interface;

/**
 *
 * @author ruioliveiras
 */
public class MainClient {
    
    public static void main(String[] args) throws IOException {
        Client c = new Client();
        Interface ui = new Interface(c);
        try {
            ui.start();
        } catch (SimpleExecption ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
