/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.util.concurrent.locks.Condition;

/**
 *
 * @author Jose
 */
public class Tarefa extends TipoTarefa {

    private String estado;
    private String codigo;
    private Condition ready;
    private Condition finished;

    /** Constructs a new Tarefa
     * 
     * @param f 
     */
    public Tarefa(TipoTarefa f) {
        super(f);
        this.estado = "waiting";
    }

    /** Sets the state
     * 
     * @param s state to be set
     */
    public void setEstado(String s) {
        this.estado = s;
    }

    /** Gets the state
     * 
     * @return current state
     */
    public String getEstado() {
        return this.estado;
    }

    /** Gets the code of Tarefa
     * 
     * @return the code of Tarefa
     */
    public String getCodigo() {
        return codigo;
    }

    /** Warning this method should be called with Client lock
     * 
     * @param string code to be set
     */
    public void setCodigo(String string) {
        this.codigo = string;
    }
    
    /** Warning this method should be called with Client lock
     * 
     * @param finished condition to be set
     */
    void setConditionFinished(Condition finished) {
        this.finished = finished;
    }

    
    /** Warning this method should be called with Client lock
     * 
     * @param ready condition to be set 
     */
    void setConditionReady(Condition ready) {
        this.ready = ready;
    }

    
    /** Warning this method should be called with Client lock
     * 
     * @return 
     */
    public Condition getConditionFinished() {
        return finished;
    }

    
    /** Warning this method should be called with Client lock
     * 
     * @return 
     */
    public Condition getConditionReady() {
        return ready;
    }
}
