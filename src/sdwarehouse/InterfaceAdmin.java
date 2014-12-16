/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdwarehouse;

import java.util.Scanner;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class InterfaceAdmin {
    
    private static Menu menumain, menucliente, menuware, menuselected; 
    
    private InterfaceAdmin(){}   
    
    public static void main(String[] args) {
        
        
        carregarMenus();
        
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: menuclientes();
                        break;
                case 2: menuwareh();
                        break;
            }
        } while (menumain.getOpcao()!=0);
    }
    
    
    
    private static void carregarMenus() {
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
    
    
    private static void menuclientes(){
        do {
            menucliente.executa();
            switch (menucliente.getOpcao()) {
                case 1: registarUser();
                        break;
                case 2: editDB();
                        break;
                case 3: listUser();
                        break;
                case 4: selectUser();
                        break;
            }
        } while (menucliente.getOpcao()!=0);
    }
    
    
    private static void menuwareh() {
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
    
    
    private static void selectUser(){
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
    
    
    private static void registarUser(){}
    
    
    private static void editDB(){}
    
    
    private static void listUser(){}

      
    private static void addTarefa(String user){}
    
    
    private static void editTarefa(String user){}
    
    
    private static void closeTarefa(String user){}
    
    
    private static void statusTarefa(String user){}
    
    
    private static void readyTarefa(String user){}
   
    
    private static void finishedTarefa(String user){}
    
    
    private static void listTarefa(String user){}
    
    
    private static void addObj(){}
    
    
    private static void remObj(){}
    
    
    private static void altObj(){}
    
    
    private static void listObj(){}
}


