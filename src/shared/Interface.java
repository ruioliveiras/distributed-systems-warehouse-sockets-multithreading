package shared;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import shared.Facede;
import shared.Menu;
import server.model.Cliente;

import java.util.Scanner;
import shared.SimpleExecption;

/**
 *
 * @author Tomás Ferreira
 *
 */
public class Interface {///implements Facede{

    public  Menu menumain, menucliente, menuware, menuselected, menulogreg; 
    private String user;
    private Facede f;
            
    public Interface(Facede f){
        this.f = f;
    }
    
    public void start() throws SimpleExecption {
        carregarMenus();
        
        do {
            menulogreg.executa();
            switch (menulogreg.getOpcao()) {
                case 1: login();
                        break;
                case 2: registar();
                        break;
            }
        } while (menulogreg.getOpcao()!=0);
    }
    
    
    protected void login() throws SimpleExecption{
        Scanner is = new Scanner(System.in);
        String pass;
        
        do{
            System.out.print("Username: ");
            user = is.nextLine();
            System.out.print("Password: ");
            pass = is.nextLine();
        } while(!f.login(user, pass));
        
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: menuuser(user);
                        break;
                case 2: menuwareh();
                        break;
            }
        } while (menumain.getOpcao()!=0);
    }
    
    
    protected void registar()  throws SimpleExecption{
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
        
        Cliente novo = new Cliente(usern, pass);  
        
        f.addUser(usern, pass);
    }
    
    
    protected  void carregarMenus() {
    String[] ops = {"Menu Cliente",
                    "Menu WareHouse"};
    
    String[] logreg = {"Login",
                       "Registar"};
    
    String [] opsclient = {"Registar Utilizador",
                           "Alterar Dados de Utilizador",
                           "Lista de Utilizadores",
                           "Menu de Utilizador"};
    
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
    menulogreg = new Menu(logreg);
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
    
    protected  void menuwareh() throws SimpleExecption{
        do {
            menuware.executa();
            switch (menuware.getOpcao()) {
                case 1: caddObj();
                        break;
                case 2: cremObj();
                        break;
                case 3: caltObj();
                        break;
                case 4: clistObj();
                        break;
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
    
    
    protected void menuuser(String user) throws SimpleExecption{
        do {
            menuselected.executa();
            switch (menuselected.getOpcao()) {
                case 1: caddTarefa(user);
                        break;
                case 2: ceditTarefa(user);
                        break;
                case 3: ccloseTarefa(user);
                        break;
                case 4: cstatusTarefa(user);
                        break;
                case 5: creadyTarefa(user);
                        break;
                case 6: cfinishedTarefa(user);
                        break;
                case 7: clistTarefa(user);
                        break;
            }
        } while (menuselected.getOpcao()!=0);
    }
    
    
    public  boolean userExiste(String user) throws SimpleExecption{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
     
    public  void clistUser() throws SimpleExecption {
        //Porque que o metodo recebe um username como argumento?
        String[] users = f.listUser(user);
        
        System.out.println("\nLista de utilizadores:");
        for(String u: users) System.out.println(u);
    } 
      
     
    public  void caddTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        String[] objetos = null;
        int i=0;
        
        
        System.out.println("\nQual o tipo de tarefa a adicionar?");
        tarefa = is.nextLine();
        
        System.out.println("\nInsira a lista de objetos a utlizar pela tarefa (termine com o caractere 'q':");
        do{
            objetos[i] = is.nextLine();
            i++;
        } while(!(objetos[i-1].equals("q")));
        
        f.addTipoTarefa(user, tarefa, objetos);
    }
        
     
    public  void ceditTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        String[] objetos = null;
        int i=0;
        
        
        System.out.println("\nQual o tipo de tarefa a editar?");
        tarefa = is.nextLine();
        
        System.out.println("\nInsira a lista de objetos a utlizar pela tarefa (termine com o caractere 'q':");
        do{
            objetos[i] = is.nextLine();
            i++;
        } while(!(objetos[i-1].equals("q")));
        
        f.editTipoTarefa(user, tarefa, objetos);
    }
        
     
    public  void ccloseTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a remover?");
        tarefa = is.nextLine();
        
        f.closeTarefa(user, tarefa);
    }
        
     
    public  void cstatusTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a verificar?");
        tarefa = is.nextLine();
        
        f.statusTarefa(user, tarefa);
    }    
    
     
    public  void creadyTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a ser notificada?");
        tarefa = is.nextLine();
        
        f.readyTarefa(user, tarefa); 
    }
       
     
    public  void cfinishedTarefa(String user) throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String tarefa;
        
        
        System.out.println("\nQual o codigo da tarefa a ser notificada?");
        tarefa = is.nextLine();
        
        f.finishedTarefa(user, tarefa); 
    }
    
    
    public  void clistTarefa(String user) throws SimpleExecption {
        String[] tarefas = f.listTarefa(user);
        
        System.out.println("Lista de tareas do utilizador" + user + ":");
        for(String tar: tarefas) System.out.println(tar);
    } 
    
     
    public  void caddObj() throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String objeto;
        
        
        System.out.println("\nQual o nome do objeto a ser adicionado?");
        objeto = is.nextLine();
        
        f.addObj(objeto);
    }
    
     
    public  void cremObj() throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String objeto;
        
        
        System.out.println("\nQual o nome do objeto a ser removido?");
        objeto = is.nextLine();
        
        f.remObj(objeto);
    }
    
     
    public  void caltObj() throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String objeto;
        int q;
        
        
        System.out.println("\nQual o nome do objeto?");
        objeto = is.nextLine();
        
        System.out.println("\nQual quantidade a ser adicionada?");
        q= is.nextInt();
        
        f.supplyObj(objeto, q);
    }
    
     
    public void clistObj() throws SimpleExecption {
        String[] objetos = f.listObj();
        
        System.out.println("Lista de objetos no armazem:");
        for(String obj: objetos) System.out.println(obj);
    } 

     
    public void ceditUser() throws SimpleExecption {
        Scanner is = new Scanner(System.in);
        String usern, npass;
        
        
        System.out.println("\nQual o nome de utilizador");
        usern = is.nextLine();
        
        System.out.println("\nQual a nova palavra pass?");
        npass = is.nextLine();
        
       f.editUser(usern, npass);
    }

}