package shared;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import shared.Facede;   
import shared.Menu;
import server.model.Client;

import java.util.Scanner;
import shared.SimpleExeption;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class Interface {///implements Facede{

    public  Menu menumain, menucliente, menuware, menuselected, menulogreg,menuamigosSelect; 
    private String user;
    private Facede f;
            
    public Interface(Facede f){
        this.f = f;
    }
    
    public void start(){
        carregarMenus();
        
        do {
            try{
                menulogreg.executa();
                switch (menulogreg.getOpcao()) {
                    case 1: login();
                            break;
                    case 2: registar();
                            break;
                }
            }catch(SimpleExeption s){
                System.err.println(s.getMessage());
            }
        } while (menulogreg.getOpcao()!=0);
    }
    
    
    protected void login() throws SimpleExeption{
        Scanner is = new Scanner(System.in);
        String pass;
        
        do{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
        } while(!f.login(user, pass));
        
        do {
            try{
                menumain.executa();
                switch (menumain.getOpcao()) {
                    case 1: menuuser(user);
                            break;
                    case 2: menuwareh();
                            break;
                    case 3: menuamigos(user);
                }

            }catch(SimpleExeption s){
                System.err.println(s.getMessage());
            }
        } while (menumain.getOpcao()!=0);
    }
    
    
    protected void registar()  throws SimpleExeption{
        Scanner is = new Scanner(System.in);
        boolean sair=false;
        String usern, pass;
        
        do {
            System.out.print("Username: ");
            usern = is.nextLine();

            if (userExiste(usern)) {
                System.out.println("Nome de Utilizador ja existente!\n");
            }
            else sair=true;
        } while(!sair);
        
        System.out.print("Password: ");
        pass = is.nextLine();
        
        Client novo = new Client(usern, pass);  
        
        f.addUser(usern, pass);
    }
    
    
    protected  void carregarMenus() {
    String[] ops = {"Menu area Cliente",
                    "Menu WareHouse",
                    "Menu Amigos,"
                    };
    
    String[] logreg = {"Login", //login
                       "Registar"}; //addUser
    
    String [] opsclient = {"Registar Utilizador", //addUser
                           "Remover Utilizador", // Nops
                           "Lista de Utilizadores", //listUser
                           "Menu de Utilizador"}; 
    
    String [] opsclienteSelect = {"Criar TipoTarefa", //addTipoTarefa
                                  "lista TipoTarefas", 
                                  "Iniciar Tarefa",  //openTarefa
                                  "Terminar Tarefa", //closeTarefa
                                  "Consultar Estado de Tarefa",//statusTarefa
                                  "Requesitar Notificacao de Tarefa Pronta a ser Executada", //readyTarefa
                                  "Requesitar Notificacao de Conclusao de Tarefa", //finishedTarefa
                                  "Listar as minhas de Tarefas"
                                  };//listAllTarefa
    
    String [] opsamigosSelect = {"lista TipoTarefas", 
                                  "Consultar Estado de Tarefa",//statusTarefa
                                  "Requesitar Notificacao de Tarefa Pronta a ser Executada", //readyTarefa
                                  "Requesitar Notificacao de Conclusao de Tarefa", //finishedTarefa
                                  "Listar as minhas de Tarefas", //listTarefa
                                  };//listAllTarefa

    
    String [] opsware = {//"Adicionar Objeto", //supplyObj
                         //"Retirar Objeto", //
                         "Adicionar Quantidade de Objeto", //supplyObj
                         "Lista de Objetos"};// listObj
    
    menumain = new Menu(ops);
    menucliente = new Menu(opsclient);
    menuselected = new Menu(opsclienteSelect);
    menuware = new Menu(opsware);
    menulogreg = new Menu(logreg);
    menuamigosSelect = new Menu(opsamigosSelect);
    }
    
/*    
    protected  void menuclientes() throws Exception{
        do {
            menucliente.executa();
            switch (menucliente.getOpcao()) {
                case 1: caddUser();
                        break;
                case 2: ceditUser();
                        break;
                case 3: clistUser();
                        break;
                case 4: selectUser();
                        break;
            }
        } while (menucliente.getOpcao()!=0);
    }
*/    
    
    protected  void menuwareh() throws SimpleExeption{
        do {
            menuware.executa();
            switch (menuware.getOpcao()) {
                case 1: caltObj();
                        break;
                case 2: clistObj();
                        break;
//                case 3: caltObj();
//                        break;
//                case 4: clistObj();
//                        break;
            }
        } while (menuware.getOpcao()!=0);
    }
    
    
    public void selectUser() throws Exception{
        Scanner is = new Scanner(System.in);
        
        System.out.print("Insira o nome do utilizador a consultar: ");
        user = is.nextLine();
        
        if(userExiste(user)) {
            menuuser(user);
        }
        else {
            System.out.println("Utilizador nao Existe!");
        }
    }
    protected void menuamigos(String user) throws SimpleExeption{
        String friend;
        do{
            System.out.println("Insera nome do cliente ou - para ver os nomes");
            friend = Input.lerString();
            if (friend.equals("-")){
                for (String col : f.listUser(user)) {
                    System.out.println(col);
                }
            }
        }while(friend.equals("-"));
//"lista TipoTarefas", 
//"Consultar Estado de Tarefa",//statusTarefa
//"Requesitar Notificacao de Tarefa Pronta a ser Executada", //readyTarefa
//"Requesitar Notificacao de Conclusao de Tarefa", //finishedTarefa
//"Listar as minhas de Tarefas", //listTarefa
        do{
            menuamigosSelect.executa();
            switch (menuamigosSelect.getOpcao()) {
                case 1: clistTipoTarefa(friend);
                        break;
                case 2: cstatusTarefa(friend);
                        break;
                case 3: creadyTarefa(friend);
                        break;
                case 4: cfinishedTarefa(friend);
                        break;
                case 5: clistTarefa(friend);
                        break;
            }
        } while (menuamigosSelect.getOpcao()!=0);        
    }
    
    protected void menuuser(String user) throws SimpleExeption{
        do {
            menuselected.executa();
            switch (menuselected.getOpcao()) {
                case 1: caddTipoTarefa(user);
                        break;
                case 2: clistTipoTarefa(user);
                        break;
                case 3: ciniciarTarefa(user);//ceditTarefa(user);
                        break;
                case 4: ccloseTarefa(user);
                        break;
                case 5: cstatusTarefa(user);
                        break;
                case 6: creadyTarefa(user);
                        break;
                case 7: cfinishedTarefa(user);
                        break;
                case 8: clistTarefa(user);
                        break;
                case 9: break; // "Listar as tarefas de todos" maybe
            }
        } while (menuselected.getOpcao()!=0);
    }
    
    public  boolean userExiste(String user) throws SimpleExeption{
        return false;
    }
        
     
    public  void clistUser() throws SimpleExeption {
        //Porque que o metodo recebe um username como argumento?
        String[] users = f.listUser(user);
        
        System.out.println("\nLista de utilizadores:");
        for(String u: users) System.out.println(u);
    } 
      
     
    public  void caddTipoTarefa(String user) throws SimpleExeption {
        String tarefa;
        String[] objetos;
        Integer[] numbers;
        
        
        System.out.println("\nQual o tipo de tarefa a adicionar?");
        tarefa = Input.lerString();
        System.out.println("\nQuantos objetos contem?");
        int len = Input.lerInt();
        objetos = new String[len];
        numbers = new Integer[len];
        
        System.out.println("\nLista de objetos: ");
        for (int j = 0; j < len; j++) {
            objetos[j] = Input.lerString();
            numbers[j] = Input.lerInt();
        }

        f.addTipoTarefa(user, tarefa, objetos, numbers);
    }
    
    public void clistTipoTarefa(String user) throws SimpleExeption {
        String[] tiposTarefas = f.listTipoTarefa(user);
        System.out.println("Lista de Tipo de tareas do utilizador" + user + ":");
        for(String tar: tiposTarefas) System.out.println(tar);
    }
                        
    
    public  void ciniciarTarefa(String user) throws SimpleExeption {
        System.out.println("\nQual o tipo de tarefa a Iniciar?");
        String tarefa = Input.lerString();
        
        String codigo = f.openTarefa(user, tarefa);
        System.out.println("\n Tarefa iniciada com sucesso codigo: " + codigo);
    }
        
     
//    public  void ceditTarefa(String user) throws SimpleExeption {
//        Scanner is = new Scanner(System.in);
//        String tarefa;
//        String[] objetos = null;
//        int i=0;
//        
//        
//        System.out.println("\nQual o tipo de tarefa a editar?");
//        tarefa = is.nextLine();
//        
//        System.out.println("\nInsira a lista de objetos a utlizar pela tarefa (termine com o caractere 'q':");
//        do{
//            objetos[i] = is.nextLine();
//            i++;
//        } while(!(objetos[i-1].equals("q")));
//        
//        f.editTipoTarefa(user, tarefa, objetos);
//    }
//{"Criar TipoTarefa", //addTipoTarefa
////"Alterar Tarefa", 
//"Iniciar Tarefa",  //openTarefa
//"Terminar Tarefa", //closeTarefa
//"Consultar Estado de Tarefa",//statusTarefa
//"Requesitar Notificacao de Tarefa Pronta a ser Executada", //readyTarefa
//"Requesitar Notificacao de Conclusao de Tarefa", //finishedTarefa
//"Listar as minhas de Tarefas", //listTarefa
//"Listar as tarefas de todos"};//listAllTarefa

    public  void ccloseTarefa(String user) throws SimpleExeption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a terminar?");
        tarefa = is.nextLine();
        
        f.closeTarefa(user, tarefa);
    }
        
     
    public  void cstatusTarefa(String user) throws SimpleExeption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a verificar?");
        tarefa = is.nextLine();
        
        String status = f.statusTarefa(user, tarefa);
        System.out.println("O status é: "+ status);
    }    
    
     
    public  void creadyTarefa(String user) throws SimpleExeption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a ser notificada?");
        tarefa = is.nextLine();
        
        f.readyTarefa(user, tarefa); 
        System.out.println("Tarefa Em execução");
    }
       
     
    public  void cfinishedTarefa(String user) throws SimpleExeption {
        Scanner is = new Scanner(System.in);
        
        
        System.out.println("\nQual o codigo da tarefa a ser notificada?");
        String tarefa = is.nextLine();
        
        f.finishedTarefa(user, tarefa);
        System.out.println("Tarefa terminada");
    }
    
    
    public  void clistTarefa(String user) throws SimpleExeption {
        String[] tarefas = f.listTarefa(user);
        
        System.out.println("Lista de tareas do utilizador" + user + ":");
        for(String tar: tarefas) System.out.println(tar);
    } 
     
    public  void caltObj() throws SimpleExeption {
        Scanner is = new Scanner(System.in);
        String objeto;
        int q;
        
        
        System.out.println("\nQual o nome do objeto?");
        objeto = is.nextLine();
        
        System.out.println("\nQual quantidade a ser adicionada?");
        q= is.nextInt();
        
        f.supplyObj(objeto, q);
    }
    
     
    public void clistObj() throws SimpleExeption {
        Tuple<String[], Integer[]> objetos = f.listObj();
        
        System.out.println("Lista de objetos no armazem:");
        for (int i = 0; i < objetos.getA().length; i++) {
            System.out.println(objetos.getA()[i] + " - "
                    + objetos.getB()[i]
            );
        }
    } 
}