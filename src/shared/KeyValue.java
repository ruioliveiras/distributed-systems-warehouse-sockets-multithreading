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
public class KeyValue<A,B> {
    private A key;
    private B value;

    public KeyValue(A key, B value) {
        this.key = key;
        this.value = value;
    }
    
    public A getKey() {
        return key;
    }

    public B getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key+ "$" + value;
    }
}
