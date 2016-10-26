/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author jf.mendez11
 */
@ApplicationException(rollback = true)
public class HospitalLogicException extends Exception {
    
    public HospitalLogicException () {
        super();
    }
    
    public HospitalLogicException (String message) {
        super (message);
    }
    
    public HospitalLogicException (Throwable cause) {
        super (cause);
    }
    
    public HospitalLogicException (String message, Throwable cause) {
        super (message, cause);
    }
}
