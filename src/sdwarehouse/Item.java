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
public class Item {
    private int quantidade;
    private String nome;

    
    /**
     * Faz o hashCode do item so com a string do nome
     * @return nome.hashCode
     */
    @Override
    public int hashCode() 
    {
        return nome.hashCode();
    }
    
    
}
