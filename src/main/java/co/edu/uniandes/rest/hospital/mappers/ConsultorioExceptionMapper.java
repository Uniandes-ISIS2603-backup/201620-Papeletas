/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.mappers;

import co.edu.uniandes.rest.cities.exceptions.CityLogicException;
import co.edu.uniandes.rest.cities.exceptions.ConsultorioException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jf.mendez11
 */
public class ConsultorioExceptionMapper implements ExceptionMapper<ConsultorioException> {
    
    
    	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */

    @Override
    public Response toResponse(ConsultorioException ex) {
        return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
    }
	
}
