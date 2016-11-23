    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.CitaDTO;
import co.edu.uniandes.rest.hospital.dtos.CitaDetailDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDetailDTO;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Nicolas
 */
@Path("")
@Produces("application/json")
public class MedicoResource {
    
    
    @Inject
    private IMedicoLogic medico;
    
    @Inject
    private ICitaLogic cita;
    
 
 /**
  * retorna una lista con los medicos y sus datos de entity a dto
  * @return lista de medicos
  * @throws MedicoException si no hay medicso en la lista 
  */ 
    
    public List<MedicoDetailDTO> getMedicosDTO(List <MedicoEntity> entityList)
    {
      List<MedicoDetailDTO> list = new ArrayList();
       for (MedicoEntity entity : entityList) {
            list.add(new MedicoDetailDTO(entity));
        }
        return list;
    
    }
    
    
    @GET
    @Path("medico")
    public List<MedicoDetailDTO> getMedicos()
    {
      return getMedicosDTO(medico.getMedicos());
    }
    /**
     * Retorna un medico dado su ID
     * @param id id del medico buscado
     * @return medico con el id especificado
     * @throws MedicoException cuando no existe un medico con el id
     */
    @GET
    @Path("medico/{id: \\d+}") 
    public MedicoDTO getMedicoID(@PathParam("id")Long id)throws MedicoException
    {
        return new MedicoDetailDTO(medico.getMedico(id));
    }
    
    /**
     * Crea un nuevo medico
     * @param medi medico a agregar
     * @return medico agregado
     * @throws MedicoException cuando ya existe un medico con el id dado 
     */
    @POST
    @Path("medico")
    public MedicoDTO createMedico(MedicoDTO medi)throws HospitalLogicException
    {
        
     return new MedicoDetailDTO(medico.createMedico(medi.toEntity()));
    }
    
    
    /**
     * Borra un medico
     * @param id
     * @throws MedicoException si no existe el medico que se quiere eliminar
     */
    @DELETE
    @Path("medico/{id: \\d+}")
    public void deleteMedico(@PathParam("id")Long id) throws MedicoException
    {
        medico.deleteMedico(id);
    }
    
    /**
     * Actualiza un medico dado su id
     * @param medicoN Medico con los nuevos datos
     * @throws MedicoException
     * @return medico actualizado
     */
    @PUT
    @Path ("medico/{id: \\d+}")
    public MedicoDTO updateMedico(MedicoDTO medicoN) throws MedicoException
    {
        return  new MedicoDetailDTO(medico.updateMedico(medicoN.toEntity()));
    }
    
  
    
    
    
    

    
    
    
    
    
}
