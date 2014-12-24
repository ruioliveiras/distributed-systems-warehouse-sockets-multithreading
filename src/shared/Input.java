/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;



/**
 * Classe que abstrai a utilizaÃ§Ã£o da classe Scanner, escondendo todos os
 * problemas relacionados com excepÃ§Ãµes, e que oferece mÃ©todos simples e
 * robustos para a leitura de valores de tipos simples.
 *
 * -----  UtilizaÃ§Ã£o: Exemplos
 *
 * int i = Input.lerInt();
 * String linha = Input.lerString();
 * double raio = Input.lerDouble();
 * ---------------------------------------
 *
 * @author F. MÃ¡rio Martins
 * @version 1.0 (6/2006)
 */
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {

 /**
  * MÃ©todos de Classe
  */
 
 public static String lerString() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     String txt = "";
     while(!ok) {
         try {
             txt = input.nextLine();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Texto InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return txt;
  } 

 
 public static int lerInt() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     int i = 0; 
     while(!ok) {
         try {
             i = input.nextInt();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Inteiro InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return i;
  } 
  
  public static double lerDouble() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     double d = 0.0; 
     while(!ok) {
         try {
             d = input.nextDouble();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return d;
  }  
  
   public static float lerFloat() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     float f = 0.0f; 
     while(!ok) {
         try {
             f = input.nextFloat();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return f;
  }  
  
   public static boolean lerBoolean() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     boolean b = false; 
     while(!ok) {
         try {
             b = input.nextBoolean();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Booleano InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return b;
  } 
  
  public static short lerShort() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     short s = 0; 
     while(!ok) {
         try {
             s = input.nextShort();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Short InvÃ¡lido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return s;
  }   
}
