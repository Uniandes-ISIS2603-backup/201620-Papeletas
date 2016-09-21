/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.hospital.dtos.CitaDTO;
import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;
import co.edu.uniandes.rest.hospital.exceptions.CitaException;
import co.edu.uniandes.rest.hospital.mocks.CitaMock;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static org.glassfish.jersey.server.model.Parameter.Source.PATH;

/**
 *
 * @author jc.useche10
 */
@Path("")
@Produces("application/json")
@Consumes("application/json")
public class CitaResource {
     /**
     * Mock del cita
     */
    CitaMock cita = new CitaMock();
    
    /**
     * Obtiene la lista de citas
     * @return lista de citas
     * @throws CitaException Si la lista no existe
     */
    @GET
    @Path ("medico/{idMedico: \\d+}/citas")
    public List<CitaDTO> getCitas (@PathParam("idMedico") Long idMedico) throws CitaException 
    {
        return cita.getCitas(idMedico);
    }
    
    /**
     * Obtiene el cita con el id especificado por parametro
     * @param id id del cita que se quiere buscar
     * @return el cita con el id que entra por parametro
     * @throws CitaException si no existe un cita con el id especificado o no existe la lista
     */
    @GET
    @Path ("medico/{idMedico: \\d+}/citas/{id: \\d+}")
    public CitaDTO getCita (@PathParam("idMedico") Long idMedico, @PathParam ("id") Long id) throws CitaException
    {
        return cita.getCita(idMedico, id);
    }
    
    /**
     * Crea un nuevo cita y lo agrea a la lista
     * @param newCita cita que se quiere agregat
     * @return Cita nuevo
     * @throws CitaException si ya existe un cita con el ese id
     */
    @POST
    @Path ("medico/{idMedico: \\d+}/citas")
    public CitaDTO createCita (@PathParam("idMedico") Long idMedico, CitaDTO newCita) throws CitaException
    {
        return cita.createCita(idMedico,newCita);
    }
    
    /**
     * Borra el cita con el id especificado
     * @param id ide del cita que se quiere borrar.
     * @throws CitaException si no existe un cita con el id especificado
     */
    @DELETE
    @Path ("medico/{idMedico: \\d+}/citas/{id: \\d+}")
    public void deleteCita (@PathParam("idMedico") Long idMedico, @PathParam("id") int id) throws CitaException
    {
        cita.deleteCita(idMedico, id);
    }
}
