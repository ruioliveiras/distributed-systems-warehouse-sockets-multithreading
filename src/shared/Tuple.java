/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;

/**
 *
 * @author ruioliveiras
 */
public class Tuple<A,B> implements ComunicationSerializer.Serializer{
    private A a;
    private B b;

    public Tuple() {
    }

    public Tuple(A key, B value) {
        this.a = key;
        this.b = value;
    }
    
    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return a.toString() + ">" + b.toString();
    }

    @Override
    public String serialize(ComunicationSerializer ser) throws SimpleExecption {
        return ser.serialize(a) + ">" + ser.serialize(b);
    }

    @Override
    public void descerialize(ComunicationSerializer ser,String from) throws SimpleExecption {
        String s[] = from.split(">");
        if (s.length == 2){
            a = ser.descerialize(s[0], a);
            b = ser.descerialize(s[1], b);
        } else {
            throw new SimpleExecption(1, "Serializer", "Tuple with invalid argumes" + from);
        }
    }

    @Override
    public Tuple clone() {
       return new Tuple(a, b);
    }
    
}
