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

    public Tarefa(TipoTarefa f) {
        super(f);
        this.estado = "waiting";
    }

    public void setEstado(String s) {
        this.estado = s;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCodigo() {
        return codigo;
    }

    /** Warning this method should be called with Client lock
     * 
     * @param string 
     */
    public void setCodigo(String string) {
        this.codigo = string;
    }
    
    /** Warning this method should be called with Client lock
     * 
     * @param finished 
     */
    void setConditionFinished(Condition finished) {
        this.finished = finished;
    }

    
    /** Warning this method should be called with Client lock
     * 
     * @param finished 
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
