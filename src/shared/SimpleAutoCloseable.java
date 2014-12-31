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
public interface SimpleAutoCloseable extends AutoCloseable{
    @Override
    public void close() throws SimpleExeption;
}