/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdwarehouse;

/**
 *
 * @author Jose
 */
public class TarefaExecucao extends Tarefa
{
    private Tarefa tarefa;
    private String estado;
        
    public TarefaExecucao()
    {
        this.tarefa = new Tarefa();
        this.estado = "unknown";
    }
    
    public TarefaExecucao(Tarefa f)
    {
        this.tarefa = new Tarefa(f);
        this.estado = "Requisição objetos";
    }
    
    public void setEstado(String s){ this.estado = s; }
    public String getEstado(){ return this.estado; }
    
    public Tarefa getTarefa(){ return this.tarefa.clone(); }
}
