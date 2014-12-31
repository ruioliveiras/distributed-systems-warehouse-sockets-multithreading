/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.Arrays;
import shared.Facede;
import shared.SimpleExeption;
import shared.Interface;

/**
 *
 * @author ruioliveiras
 */
public class MainClient {
    
        
    public static void initFacade(Facede f) throws SimpleExeption{
//        f.addUser("admin", "admin");
//        f.addUser("rui", "admin");
//        f.addUser("pedro", "admin");
//        f.addUser("azevedo", "admin");
//        
//        f.supplyObj("ratos", 12);
//        f.supplyObj("teclados", 12);
//        f.supplyObj("monitor", 12);
//        f.supplyObj("portatil", 12);
//        f.supplyObj("mochila", 12);
//        f.supplyObj("calculadora", 12);
//        
//        String[] a = new String[2];
//        Integer[] q = new Integer[2];
//        a[0] = "ratos"; a[1] = "teclados"; q[0] = 3; q[1] = 4;
//        f.addTipoTarefa("admin", "montar",a,q);
//        
//        System.out.println(Arrays.toString(f.listUser("admin")));
//        System.out.println(Arrays.toString(f.listTipoTarefa("admin")));
//        System.out.println(Arrays.toString(f.listTarefa("admin")));
//        System.out.println(f.openTarefa("admin", "montar"));
//        System.out.println(f.statusTarefa("admin", "admin_1"));
//        System.out.println(Arrays.toString(f.listTarefa("admin")));
        /* teste login*/
        System.out.println((f.login("admin","admin")));
        
        
    }
    
    
    
    public static void main(String[] args) throws IOException, SimpleExeption {
        Client c = new Client();
        initFacade(c);
        Interface ui = new Interface(c);
        ui.start();
    }
}
