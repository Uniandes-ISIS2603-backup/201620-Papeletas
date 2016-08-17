/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.resources;

import co.edu.uniandes.rest.cities.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.cities.exceptions.ConsultorioException;
import co.edu.uniandes.rest.cities.mocks.ConsultorioMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author jf.mendez11
 */
@Path("consultorio")
@Produces("application/json")
public class ConsultorioResource {
    
    /**
     * Mock del consultorio
     */
    ConsultorioMock consultorio = new ConsultorioMock();
    
    /**
     * Obtiene la lista de consultorios
     * @return lista de consultorios
     * @throws ConsultorioException Si la lista no existe
     */
    @GET
    public List<ConsultorioDTO> getConsultorios () throws ConsultorioException 
    {
        return consultorio.getConsultorios();
    }
    
    /**
     * Obtiene el consultorio con el id especificado por parametro
     * @param id id del consultorio que se quiere buscar
     * @return el consultorio con el id que entra por parametro
     * @throws ConsultorioException si no existe un consultorio con el id especificado o no existe la lista
     */
    @GET
    @Path ("{id: \\d+}")
    public ConsultorioDTO getConsultorio (@PathParam ("id") Long id) throws ConsultorioException
    {
        return consultorio.getConsultorioId(id);
    }
    
    /**
     * Crea un nuevo consultorio y lo agrea a la lista
     * @param newConsultorio consultorio que se quiere agregat
     * @return Consultorio nuevo
     * @throws ConsultorioException si ya existe un consultorio con el ese id
     */
    @POST
    public ConsultorioDTO createConsultorio (ConsultorioDTO newConsultorio) throws ConsultorioException
    {
        return consultorio.createConsultorio(newConsultorio);
    }
    
    /**
     * Borra el consultorio con el id especificado
     * @param id ide del consultorio que se quiere borrar.
     * @throws ConsultorioException si no existe un consultorio con el id especificado
     */
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteConsultorio (@PathParam("id") Long id) throws ConsultorioException
    {
        consultorio.deleteConsultorio(id);
    }
    
    /**
     * Actualiza el consultorio con el id especificado
     * @param id id del consultorio que se quiere actualizar
     * @param i índice de la hora que se quiere actualizar
     * @param consul consultorio actualizado
     * @return el consultorio actualizado
     * @throws ConsultorioException Si la información del consultorio está incompleta, el consultorio con el id no existe o se ingresa un índice fuera del rango
     */
    @PUT
    @Path ("{id: \\d+}")
    public ConsultorioDTO updateConsultorio (@PathParam("id") Long id, @QueryParam ("i") Integer i, ConsultorioDTO consul) throws ConsultorioException
    {
        return consultorio.updateConsultorio(id, i, consul);
    }
    
}
