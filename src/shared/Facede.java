/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.util.Map;

/** O objectivo desta interface é definir o que uma UI pode precisar para
 * imprimir ao longo da sua execução
 * Dai tentar retornar sempre tipos simples.
 * Esta interface será tambem usada por as camadas de comunicação
 *
 * @author ruioliveiras
 */
public interface Facede {
    
    /*Authentication*/
    /**
     * 
     * @param username String with the username
     * @param password String with the password
     * @return True if is logged, False if is not logged
     * @throws SimpleExeption 
     */
    public Boolean login(String username, String password) throws SimpleExeption;
   
    /* FOR USER */
    /**
     * 
     * @param username String with the username
     * @param password String with the password
     * @return True if success, false if it is not
     * @throws SimpleExeption 
     */
    public Boolean addUser(String username, String password) throws SimpleExeption;
   
    /**
     * 
     * @param username String with the username
     * @param password String with the password
     * @return return true if succes, false if it is not
     * @throws SimpleExecption 
     */
   // public Boolean editUser(String username, String password) throws SimpleExeption;
    
    /**
     * Return 
     * @param username String with the username
     * @return the list of all users
     * @throws SimpleExeption 
     */
    public String[] listUser(String username) throws SimpleExeption;
    
    
    /** Add new tipoTarefa for an existent user;
     * 
     * @param user the identifier of the user
     * @param tipoTarefa the identifier of the tipoTarefa
     * @param objetos The objects and his quanties  
     * @param quants  
     * @return
     * @throws SimpleExeption 
     */
    public Boolean addTipoTarefa(String user, String tipoTarefa, String objetos[], Integer quants[]) throws SimpleExeption;
    
    /** Get The list of Tarefas of an User;
     * 
     * @param user
     * @return
     * @throws SimpleExeption 
     */
    public String[] listTipoTarefa(String user) throws SimpleExeption;
    
    
    /*FOR WAREHOUSE*/
    /**
     * Create new Object with Zero quantiy
     * @param nome
     * @return Success boolean
     * @throws SimpleExecption 
     */
    //public Boolean addObj(String nome) throws SimpleExeption;

    /**
     * Delete an Object
     * @param nome name of the object
     * @return Success boolean
     * @throws SimpleExecption 
     */
    //public Boolean remObj(String nome) throws SimpleExeption;

    /**
     * Add more items 
     * @param nome
     * @param quantidade
     * @return
     * @throws SimpleExeption 
     */
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExeption;

    /**
     * Get all systems Objets with his actual quanty
     * @return 
     * @throws SimpleExeption 
     */
    public Tuple<String[],Integer[]> listObj() throws SimpleExeption;
    
    /*FOR TAREFA*/
    
    /** Edit new tipoTarefa for an existent user;
     * (Same as addTipoTarefa)
     * @param user
     * @param tipoTarefa
     * @param objetos
     * @param quants
     * @return
     * @throws SimpleExecption 
     */
    //public Boolean editTipoTarefa(String user, String tipoTarefa, String objetos[], Integer quants[]) throws SimpleExecption;    
    /*FOR TAREFA*/
    
    /** Edit new tipoTarefa for an existent user;
     * (Same as addTipoTarefa)
     * @param user
     * @param tipoTarefa
     * @param objetos
     * @param quants
     * @return
     * @throws SimpleExecption 
     */
    //public Boolean editTipoTarefa(String user, String tipoTarefa, String objetos[], Integer quants[]) throws SimpleExeption;


    
    /** Ask to open a new Tarefa, that returns a codTarefa.
     * 
     * @param user
     * @param tipoTarefa
     * @return codTarefa
     * @throws SimpleExeption 
     */
    public String openTarefa(String user, String tipoTarefa) throws SimpleExeption;

    /** Close a previous opened Tarefa.
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExeption 
     */
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExeption;
    
    /** Check the status of an Opened Tarefa
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExeption 
     */
    public String statusTarefa(String user, String codTarefa) throws SimpleExeption;
    
    /** Ask the server to be notify when the Opened Tarefa is Ready to Work
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExeption 
     */
    public Boolean readyTarefa(String user,  String codTarefa) throws SimpleExeption;
    
    /** Ask the server to be notify when some Tarefa be closed.
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExeption 
     */
    public Boolean finishedTarefa(String user,  String codTarefa) throws SimpleExeption;
     
    /** Get the list of opened Tarefas of an user;
     * 
     * @param user
     * @return
     * @throws SimpleExeption 
     */
    public String[] listTarefa(String user) throws SimpleExeption;
    
    /**
     * Get the list of opened Tarefas all the users
     * 
     * @param user
     * @return
     * @throws SimpleExeption 
     */
    public String[][] listAllTarefa(String user) throws SimpleExeption;
}
