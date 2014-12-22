/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author ruioliveiras
 */
public class SimpleExecption extends Exception{
    protected int level;
    protected String code;
    protected String simpleMessage;
    
    /**
     * 
     * @param lvl 1- critical 
     *            2-critical but can keep running 
     *            3-just-to know
     * @param code
     * @param simpleMessage 
     */
    public SimpleExecption(int lvl,String code, String simpleMessage) {
        super("[" + code + "]" + simpleMessage);
        this.level = lvl;
        this.code = code;
        this.simpleMessage = simpleMessage;
    }

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public String getCode() {
        return code;
    }   

    public int getLevel() {
        return level;
    }
}
