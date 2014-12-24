/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.lang.reflect.Array;

/**
 *
 * @author ruioliveiras
 */
public class ComunicationSerializer {
    
    public interface Serializer{
        public String serialize(ComunicationSerializer ser) throws SimpleExecption;
        public void descerialize(ComunicationSerializer ser, String from) throws SimpleExecption;
        public Serializer clone();
    }
    
    public <T> String serialize(T from) throws SimpleExecption {
        if (from instanceof String){
            return (String)from;
        } else if(from instanceof Integer) {
            return Integer.toString((Integer)from);
        } else if(from instanceof Serializer) {
            return ((Serializer) from).serialize(this);
        } else if (from.getClass().isArray()){
            return serialize((T[]) from);
        }
        throw new SimpleExecption(0, "Serializer", "Can't serialize: " + from.getClass());
    }
    
    public <T> String serialize(T[] a) throws SimpleExecption {
        String ret = "";
        int i;
        for (i = 0; i < a.length - 1; i++) {
            ret += serialize(a[i]) + "#";
        }
        if (i < a.length) {
            ret += serialize(a[i]);
        }
        return ret;
    }
    
    public Integer descerialize(String from, Integer to) throws SimpleExecption{
        try {
            return Integer.parseInt(from);
        } catch (Exception e) {
            throw new SimpleExecption(2, "SOCKET", "GetInt but isn't int");
        }
    }

    public String descerialize(String from, String to){
        return from;
    }
    
    public <T> T descerialize(String from, T to) throws SimpleExecption {
        if (to instanceof String){
            return (T) descerialize(from, (String) to);
        } else if(to instanceof Integer) {
            return (T) descerialize(from, (Integer) to);
        } else if(to instanceof Serializer) {
            if (from == null){
                return null;
            }
            Serializer aux = ((Serializer) to).clone();
            aux.descerialize(this, from);
            return (T) aux;
        } else if (to.getClass().isArray()){
            return (T) descerialize(from, (T[]) to);
        }
        return to;
    }
    
    public <T> T[] descerialize(String from, T[] to) throws SimpleExecption{
        if (from == null) {
            return (T[]) Array.newInstance(to.getClass().getComponentType(), 0);
        }
        String[] aux = from.split("#");
        T[] my = (T[]) Array.newInstance(to.getClass().getComponentType(), aux.length);
        for (int i = 0; i < aux.length; i++) {
           my[i] = descerialize(aux[i], to[0]);
        }
        return my;
    }
}
