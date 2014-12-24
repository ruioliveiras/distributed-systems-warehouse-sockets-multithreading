/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

/**
 *
 * @author Jose
 */
public class Tarefa extends TipoTarefa {

    private String estado;
    private String codigo;

    public Tarefa() {
        super();

    }

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

    public void setCodigo(String string) {
        this.codigo = string;
    }

    public String getCodigo() {
        return codigo;
    }

}
