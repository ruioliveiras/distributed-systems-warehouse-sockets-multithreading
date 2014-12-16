/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class InterfaceAdmin {
    
    private static Menu menumain, menucliente, menuware; 
    
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
                           "Criar Tarefa",
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
                case 4: addTarefa();
                        break;
                case 5: editTarefa();
                        break;
                case 6: closeTarefa();
                        break;
                case 7: statusTarefa();
                        break;
                case 8: readyTarefa();
                        break;
                case 9: finishedTarefa();
                        break;
                case 10:listTarefa();
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
    
    
    private static void registarUser(){};
    
    
    private static void editDB(){};
    
    
    private static void listUser(){};
    
    
    private static void addTarefa(){};
    
    
    private static void editTarefa(){};
    
    
    private static void closeTarefa(){};
    
    
    private static void statusTarefa(){};
    
    
    private static void readyTarefa(){};
    
    
    private static void finishedTarefa(){};
    
    
    private static void listTarefa(){};
    
    
    private static void addObj(){};
    
    
    private static void remObj(){};
    
    
    private static void altObj(){};
    
    
    private static void listObj(){};
}


