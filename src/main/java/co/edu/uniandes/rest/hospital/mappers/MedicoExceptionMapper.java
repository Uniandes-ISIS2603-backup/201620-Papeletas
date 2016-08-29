/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mappers;

import co.edu.uniandes.rest.hospital.exceptions.ConsultorioException;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MedicoExceptionMapper implements ExceptionMapper<MedicoException> {
    
    @Override
    public Response toResponse(MedicoException ex) {
        return Response
				.status(Response.Status.NOT_FOUND)	
				.entity(ex.getMessage())			
				.type("text/plain")
				.build();
    }
	
}
