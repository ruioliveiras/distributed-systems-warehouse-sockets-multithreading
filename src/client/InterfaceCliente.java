package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import shared.Facede;
import shared.Facede;
import shared.Menu;
import shared.Menu;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class InterfaceCliente implements Facede {
    
    protected  Menu menumain, menucliente, menuware; 
    
    protected InterfaceCliente(){}   
    
    public void start() {
        
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
    
    protected  void carregarMenus() {
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
    
    
    protected  void menuclientes(){
        do {
            menucliente.executa();
            switch (menucliente.getOpcao()) {
                case 1: registarUser();
                        break;
                case 2: editUser();
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
    
    
    protected  void menuwareh() {
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
    
    
    @Override
    public void registarUser(){};
    
    
    @Override
    public  void editUser(){};
    
    
    @Override
    public  void listUser(){};
    
    
    @Override
    public  void addTarefa(){};
    
    
    @Override
    public  void editTarefa(){};
    
    
    @Override
    public  void closeTarefa(){};
    
    
    @Override
    public  void statusTarefa(){};
    
    
    @Override
    public  void readyTarefa(){};
    
    
    @Override
    public  void finishedTarefa(){};
    
    
    @Override
    public  void listTarefa(){};
    
    
    @Override
    public  void addObj(){};
    
    
    @Override
    public  void remObj(){};
    
    
    @Override
    public  void altObj(){};
    
    
    @Override
    public  void listObj(){};
}


