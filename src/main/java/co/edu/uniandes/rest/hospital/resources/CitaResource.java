/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.hospital.dtos.CitaDTO;
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

/**
 *
 * @author jc.useche10
 */
@Path("medicos/{idMedico: \\d+}/turnos/{idTurno: \\d+}/citas")
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
    public List<CitaDTO> getCitas (@PathParam("idTurno") Long idTurno) throws CitaException 
    {
        return cita.getCitas(idTurno);
    }
    
    /**
     * Obtiene el cita con el id especificado por parametro
     * @param id id del cita que se quiere buscar
     * @return el cita con el id que entra por parametro
     * @throws CitaException si no existe un cita con el id especificado o no existe la lista
     */
    @GET
    @Path ("{id: \\d+}")
    public CitaDTO getCita (@PathParam("idTurno") Long idTurno, @PathParam ("id") Long id) throws CitaException
    {
        return cita.getCita(idTurno, id);
    }
    
    /**
     * Crea un nuevo cita y lo agrea a la lista
     * @param newCita cita que se quiere agregat
     * @return Cita nuevo
     * @throws CitaException si ya existe un cita con el ese id
     */
    @POST
    public CitaDTO createCita (@PathParam("idTurno") Long idTurno, CitaDTO newCita) throws CitaException
    {
        return cita.createCita(idTurno,newCita);
    }
    
    /**
     * Borra el cita con el id especificado
     * @param id ide del cita que se quiere borrar.
     * @throws CitaException si no existe un cita con el id especificado
     */
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteCita (@PathParam("idTurno") Long idTurno, @PathParam("id") int id) throws CitaException
    {
        cita.deleteCita(idTurno, id);
    }
    
    /**
     * Actualiza el cita con el id especificado
     * @param id id del cita que se quiere actualizar
     * @param i índice de la hora que se quiere actualizar
     * @return el cita actualizado
     * @throws CitaException Si la información del cita está incompleta, el cita con el id no existe o se ingresa un índice fuera del rango
     */
    @PUT
    @Path ("{id: \\d+}")
    public CitaDTO updateCita (@PathParam("idTurno") Long idTurno,@PathParam("id") int id, @QueryParam ("i") Integer i, CitaDTO nuevaCita) throws CitaException
    {
        return cita.updateCita(idTurno, id, nuevaCita);
    }
}
