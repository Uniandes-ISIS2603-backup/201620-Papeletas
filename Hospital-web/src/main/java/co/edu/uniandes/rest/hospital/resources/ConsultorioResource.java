/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.papeletas.hospital.api.IConsultorioLogic;
import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.ConsultorioDetailDTO;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
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
    
    @Inject
    private IConsultorioLogic consultorioLogic;
    
    /**
     * Obtiene la lista de consultorios, de entity a dto
     */
    private List<ConsultorioDetailDTO> listEntity2DTO (List <ConsultorioEntity> entityList) 
    {
        List<ConsultorioDetailDTO> list = new ArrayList <> (); 
        for (ConsultorioEntity entity : entityList) {
            list.add(new ConsultorioDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ConsultorioDetailDTO> getConsultorios () {
        return listEntity2DTO(consultorioLogic.getConsultorios());
    }
    
    /**
     * Obtiene el consultorio con el id especificado por parametro
     * @param id id del consultorio que se quiere buscar
     * @return el consultorio con el id que entra por parametro
     */
    @GET
    @Path ("{id: \\d+}")
    public ConsultorioDetailDTO getConsultorio (@PathParam ("id") Long id) {
        return new ConsultorioDetailDTO(consultorioLogic.getConsultorio(id));
    }
    
    @GET
    @Path ("/byNumber")
    public ConsultorioDetailDTO getConsultorio (@QueryParam("number") Integer number) {
        return new ConsultorioDetailDTO(consultorioLogic.getConsultorioByNumber(number));
    }
    
    /**
     * Crea un nuevo consultorio y lo agrea a la lista
     * @param newConsultorio consultorio que se quiere agregat
     * @return Consultorio nuevo
     */
    @POST
    public ConsultorioDetailDTO createConsultorio (ConsultorioDetailDTO newConsultorio) throws HospitalLogicException
    {
        return new ConsultorioDetailDTO(consultorioLogic.createConsultorio(newConsultorio.toEntity()));
    }
    
    /**
     * Borra el consultorio con el id especificado
     * @param id ide del consultorio que se quiere borrar.
     */
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteConsultorio (@PathParam("id") Long id)
    {
        consultorioLogic.deleteConsultorio(id);
    }
    
    /**
     * Actualiza el consultorio con el id especificado
     * @param id id del consultorio que se quiere actualizar
     * @param consul consultorio actualizado
     * @return el consultorio actualizado
     */
    @PUT
    @Path ("{id: \\d+}")
    public ConsultorioDetailDTO updateConsultorio (@PathParam("id") Long id, ConsultorioDetailDTO consul)
    {
        ConsultorioEntity entity = consul.toEntity();
        entity.setId(id);
        return new ConsultorioDetailDTO(consultorioLogic.updateConsultorio(entity));
    }
    
}