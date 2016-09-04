/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mappers;

import co.edu.uniandes.rest.hospital.exceptions.CitaException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jc.useche10
 */
public class CitaExceptionMapper implements ExceptionMapper<CitaException> {
    
    
    	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */

    @Override
    public Response toResponse(CitaException ex) {
        return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
    }
}
