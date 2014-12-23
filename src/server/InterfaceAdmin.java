/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import shared.Interface;
import java.util.Scanner;
import shared.Facede;
import shared.Menu;
/**
 *
 * @author Tomás Ferreira
 *
 */
public class InterfaceAdmin{
    
    protected  Menu menumain, menucliente, menuware, menuselected; 
    protected Facede Facade = new DataFacede(); 
    
    protected InterfaceAdmin(){}   
    
    public void start() {
        
        carregarMenus();
        
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: menuClientes();
                        break;
                case 2: menuWarehouse();
                        break;
            }
        } while (menumain.getOpcao()!=0);
    }
    
    protected  void carregarMenus() {
    String[] ops = {"Menu Cliente",
                    "Menu WareHouse"};
    
    String [] opsclient = {"Registar Utilizador",
                           "Alterar Dados de Utilizador",
                           "Lista de Utilizadores",
                           "Selecionar Utilizador"};
    
    String [] opsclienteSelect = {"Criar Tarefa",
                                  "Alterar Tarefa",
                                  "Terminar Tarefa",
                                  "Consultar Estado de Tarefa",
                                  "Requesitar Notificacao de Tarefa Pronta a ser Executada",
                                  "Requesitar Notificacao de Conclusao de Tarefa",
                                  "Lista de Tarefas"};

    String [] opsware = {"Adicionar Objeto",
                         "Retirar Objeto",
                         "Alterar Quantidade de Objeto",
                         "Lista de Objetos"};
    
    menumain = new Menu(ops);
    menucliente = new Menu(opsclient);
    menuselected = new Menu(opsclienteSelect);
    menuware = new Menu(opsware);
    }
    
    
    protected  void menuClientes(){
        do {
            menucliente.executa();
            switch (menucliente.getOpcao()) {
                case 1: addUser();
                        break;
                case 2: editUser();
                        break;
                case 3: listUser();
                        break;
                case 4: menuSelectUser();
                        break;
            }
        } while (menucliente.getOpcao()!=0);
    }
    
    
    protected  void menuWarehouse() {
        do {
            menuware.executa();
            switch (menuware.getOpcao()) {
                case 1: addObj();
                        break;
                case 2: remObj();
                        break;
                case 3: altObj();
                        break;
                case 4: listObj();
                        break;
            }
        } while (menuware.getOpcao()!=0);
    }
    
     
    private void menuSelectUser(){
        Scanner is = new Scanner(System.in);
        
        System.out.print("Insira o nome do utilizador a consultar: ");
        String user = is.nextLine();
        
        if(userExiste()) {
            do {
                menuselected.executa();
                switch (menuselected.getOpcao()) {
                    case 1: addTarefa(user);
                            break;
                    case 2: editTarefa(user);
                            break;
                    case 3: closeTarefa(user);
                            break;
                    case 4: statusTarefa(user);
                            break;
                    case 5: readyTarefa(user);
                            break;
                    case 6: finishedTarefa(user);
                            break;
                    case 7:listTarefa(user);
                            break;
                }
            } while (menuselected.getOpcao()!=0);
        }
        else {
            System.out.println("Utilizador nao Existe!");
        }
    }
    
    private static boolean userExiste(){
        return true;
    }

    /* FOR USER */
    public void addUser(){};
   
    public  void editUser(){};

    public  void listUser(){};
    
    /*FOR WAREHOUSE*/
    public  void addObj(){};

    public  void remObj(){};

    public  void altObj(){};

    public  void listObj(){};
    
    /*FOR TAREFA*/
    public void addTarefa(String user){}
    
    public void editTarefa(String user){}
    
    public void closeTarefa(String user){}
    
    public void statusTarefa(String user){}
    
    public void readyTarefa(String user){}
   
    public void finishedTarefa(String user){}
     
    public void listTarefa(String user){}
    
}


