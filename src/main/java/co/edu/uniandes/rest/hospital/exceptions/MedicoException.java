/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.exceptions;

/**
 *
 * @author n.simmonds10
 */
public class MedicoException extends Exception {

    /**
     * Creates a new instance of <code>MedicoException</code> without detail
     * message.
     */
    public MedicoException() {
    }

    /**
     * Constructs an instance of <code>MedicoException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MedicoException(String msg) {
        super(msg);
    }
}
