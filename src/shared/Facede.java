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
     * @throws SimpleExecption 
     */
    public Boolean login(String username, String password) throws SimpleExecption;
   
    /* FOR USER */
    /**
     * 
     * @param username String with the username
     * @param password String with the password
     * @return True if success, false if it is not
     * @throws SimpleExecption 
     */
    public Boolean addUser(String username, String password) throws SimpleExecption;
   
    /**
     * 
     * @param username String with the username
     * @param password String with the password
     * @return return true if succes, false if it is not
     * @throws SimpleExecption 
     */
   // public Boolean editUser(String username, String password) throws SimpleExecption;
    
    /**
     * Return 
     * @param username String with the username
     * @return the list of all users
     * @throws SimpleExecption 
     */
    public String[] listUser(String username) throws SimpleExecption;
    
    
    /** Add new tipoTarefa for an existent user;
     * 
     * @param user the identifier of the user
     * @param tipoTarefa the identifier of the tipoTarefa
     * @param objetos The objects and his quanties  
     * @param quants  
     * @return
     * @throws SimpleExecption 
     */
    public Boolean addTipoTarefa(String user, String tipoTarefa, String objetos[], Integer quants[]) throws SimpleExecption;
    
    /** Get The list of Tarefas of an User;
     * 
     * @param user
     * @return
     * @throws SimpleExecption 
     */
    public String[] listTipoTarefa(String user) throws SimpleExecption;
    
    
    /*FOR WAREHOUSE*/
    /**
     * Create new Object with Zero quantiy
     * @param nome
     * @return Success boolean
     * @throws SimpleExecption 
     */
    //public Boolean addObj(String nome) throws SimpleExecption;

    /**
     * Delete an Object
     * @param nome name of the object
     * @return Success boolean
     * @throws SimpleExecption 
     */
    //public Boolean remObj(String nome) throws SimpleExecption;

    /**
     * Add more items 
     * @param nome
     * @param quantidade
     * @return
     * @throws SimpleExecption 
     */
    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption;

    /**
     * Get all systems Objets with his actual quanty
     * @return 
     * @throws SimpleExecption 
     */
    public Tuple<String[],Integer[]> listObj() throws SimpleExecption;
    
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


    
    /** Ask to open a new Tarefa, that returns a codTarefa.
     * 
     * @param user
     * @param tipoTarefa
     * @return codTarefa
     * @throws SimpleExecption 
     */
    public String openTarefa(String user, String tipoTarefa) throws SimpleExecption;

    /** Close a previous opened Tarefa.
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExecption 
     */
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExecption;
    
    /** Check the status of an Opened Tarefa
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExecption 
     */
    public String statusTarefa(String user, String codTarefa) throws SimpleExecption;
    
    /** Ask the server to be notify when the Opened Tarefa is Ready to Work
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExecption 
     */
    public Boolean readyTarefa(String user,  String codTarefa) throws SimpleExecption;
    
    /** Ask the server to be notify when some Tarefa be closed.
     * 
     * @param user
     * @param codTarefa
     * @return
     * @throws SimpleExecption 
     */
    public Boolean finishedTarefa(String user,  String codTarefa) throws SimpleExecption;
     
    /** Get the list of opened Tarefas of an user;
     * 
     * @param user
     * @return
     * @throws SimpleExecption 
     */
    public String[] listTarefa(String user) throws SimpleExecption;
    
    /**
     * Get the list of opened Tarefas all the users
     * 
     * @param user
     * @return
     * @throws SimpleExecption 
     */
    public String[][] listAllTarefa(String user) throws SimpleExecption;
}
