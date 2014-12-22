
/**
 * Write a description of class EmpregadosApp here.
 * 
 * @author José Creissac Campos
 * @version 1.0
 */

import server.Menu;
import java.io.IOException;
import java.util.Scanner;

public class EmpregadosApp {}
    /*
    // Construtor privado (n�o queremos inst�ncias!...)
    private EmpregadosApp() {}

    // instance variables - replace the example below with your own
    private static Menu menumain, menuad;

    // M�todo principal
    public static void main(String[] args) {
        
        
        carregarMenus();
        carregarDados();
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
        } while (menumain.getOpcao()!=0);
        
        System.out.println("Até breve!...");
    }
    
    // M�todos auxiliares
    
    private static void carregarMenus() {
        String[] ops = {"Adicionar Empregado",
                        "Consultar Empregado",
                        "Calcular total de salários",
                        "Calcular total de Gestores",
                        "Calcular total de empregados por tipo",
                        "Calcular total de Kms percorridos"};
        String [] opsad = {"Adicionar Gestor",
                           "Adicionar Motorista",
                           "Adicionar Empregado Normal"
                          };

        menumain = new Menu(ops);
        menuad = new Menu(opsad);
    }
    
    private static void carregarDados() {
        try {
            tab = EmpresaZeca.leObj("estado.tabemp");
        }
        catch (IOException e) {
            System.out.println("Oops... "+e.getMessage());
            tab = new Empresa();
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Oops... "+e.getMessage());
            tab = new Empresa();
        }
        catch (ClassCastException e) {
            System.out.println("Oops... "+e.getMessage());
            tab = new Empresa();
        }
    }
    
    private static void inserirEmp() {
        Empregado emp;
        Scanner is = new Scanner(System.in);
            
        menuad.executa();
        if (menuad.getOpcao() != 0) {
            String cod, nome;
            int dias;
            double aux;
            
            System.out.print("Código: ");
            cod = is.next();
            System.out.print("Nome: ");
            nome = is.next();
            System.out.print("Dias de trabalho: ");
            dias = is.nextInt();
            
            switch (menuad.getOpcao()) {
                case 1: System.out.print("Prémio: ");
                        aux = is.nextDouble();
                        emp = new Gestor(cod, nome, dias, aux);
                        break;
                case 2: System.out.print("Kms: ");
                        aux = is.nextDouble();
                        emp = new Motorista(cod, nome, dias, aux);
                        break;
                default: emp = new Normal(cod, nome, dias);
                        break;
            }
            try {            
                tab.addEmpregado(emp);
            }
            catch(EmpregadoExisteException e){  
               //escolher o que fazer para tratar o erro...
            }
        } else {
            System.out.println("Inserção cancelada!");
        }
        is.close();
    }
    
    private static void consultarEmp() {
        String cod;
        Empregado emp;
        Scanner is = new Scanner(System.in);
        
        System.out.print("Código: ");
        cod = is.next();
        try {
            emp = tab.getEmpregado(cod);
            System.out.println(emp.toString());
        } 
        catch (NaoExisteEmpregadoException e) {
            System.out.println(e.getMessage());
        }
        is.close();
    }
    
    private static void totalSalarios() {
        System.out.println("Total de salários: "+tab.totalSalarios()+"!");
    }
    
    private static void totalGestores() {
        System.out.println("Op��o ainda n�o implementada.");
    }
    
    private static void totalPorTipo() {
        System.out.println("Op��o ainda n�o implementada.");
    }
    
    private static void totalKms() {
        System.out.println("Op��o ainda n�o implementada.");
    }*/


