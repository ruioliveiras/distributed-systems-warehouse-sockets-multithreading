/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/** O objectivo desta interface é definir o que uma UI pode precisar para
 * imprimir ao longo da sua execução
 * Dai tentar retornar sempre tipos simples.
 * Esta interface será tambem usada por as camadas de comunicação
 *
 * @author ruioliveiras
 */
public interface Facede {
    
    /* FOR USER */
    public Boolean addUser(String username, String password) throws SimpleExecption;
   
    public Boolean editUser(String username, String password) throws SimpleExecption;

    public String[] listUser(String username) throws SimpleExecption;
    
    /*FOR WAREHOUSE*/
    public Boolean addObj(String nome) throws SimpleExecption;

    public Boolean remObj(String nome) throws SimpleExecption;

    public Boolean supplyObj(String nome, int quantidade) throws SimpleExecption;

    public String[] listObj() throws SimpleExecption;
    
    /*FOR TAREFA*/
    public Boolean addTipoTarefa(String user, String tipoTarefa, String objetos[]) throws SimpleExecption;
    
    public Boolean editTipoTarefa(String user, String tipoTarefa, String objetos[]) throws SimpleExecption;
    
    public Boolean openTarefa(String user, String tipoTarefa) throws SimpleExecption;
    
    public Boolean closeTarefa(String user, String codTarefa) throws SimpleExecption;
    
    public Boolean statusTarefa(String user, String codTarefa) throws SimpleExecption;
    
    public Boolean readyTarefa(String user,  String codTarefa) throws SimpleExecption;
    
    public Boolean finishedTarefa(String user,  String codTarefa) throws SimpleExecption;
     
    public String[] listTarefa(String user) throws SimpleExecption;
    
    public String[] listTipoTarefa(String user) throws SimpleExecption;
}
