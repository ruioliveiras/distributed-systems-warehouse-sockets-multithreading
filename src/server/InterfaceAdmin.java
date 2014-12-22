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
import java.util.Scanner;

public class InterfaceAdmin {
    
    private InterfaceAdmin(){}
    
    private static Menu menumain, menucliente, menuware;    
    
    public static void main(String[] args) {
        
        
        carregarMenus();
        /*
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: inserirEmp();
                        break;
                case 2: consultarEmp();
                        break;
                case 3: totalSalarios();
                        break;
                case 4: totalGestores();
                        break;
                case 5: totalPorTipo();
                        break;
                case 6: totalKms();
            }
        } while (menumain.getOpcao()!=0);*/
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
                           "Requesitar Pedido de Conclusao de Tarefa",
                           "Lista de Tarefas"};

    String [] opsware = {"Adicionar Objeto",
                         "Retirar Objeto",
                         "Alterar Quantidade de Objeto",
                         "Lista de Objetos"};
    
    menumain = new Menu(ops);
    menucliente = new Menu(opsclient);
    menuware = new Menu(opsware);
    }
}
