/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.papeletas.hospital.api.IPacienteLogic;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import co.edu.uniandes.rest.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;
import co.edu.uniandes.rest.hospital.dtos.PacienteDetailDTO;
import java.util.ArrayList;

import javax.ws.rs.Path;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;


/**
 *
 * @author df.castro12
 */

@Path("")
@Produces("application/json")
public class PacienteResource {
    
    @Inject
    private IPacienteLogic pacienteLogic;
    
     /**
     * Convierte una lista de PacienteEntity a una lista de PacienteDetailDTO.
     *
     * @param entityList Lista de CompanyEntity a convertir.
     * @return Lista de CompanyDetailDTO convertida.
     *
     */
    private List<PacienteDetailDTO> listEntity2DTO(List<PacienteEntity> entityList) {
        List<PacienteDetailDTO> list = new ArrayList<>();
        for (PacienteEntity entity : entityList) {
            list.add(new PacienteDetailDTO(entity));
        }
        return list;
    }

     /**
     * Obtiene el listado de pacientes.
     *
     * @return lista de pacientes
     */
    @GET
    @Path("pacientes")
    public List<PacienteDetailDTO> getPacientes() {
        return listEntity2DTO(pacienteLogic.getPacientes());
    }

   
    /**
     * Agrega un paciente
     *
     * @param paciente paciente a agregar
     * @return datos del paciente a agregar
     * @throws HospitalLogicException cuando ya existe un paciente con el id
     * suministrado
     */
    @POST
    @Path("pacientes")
    public PacienteDTO createPaciente(PacienteDetailDTO paciente) throws co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException{
        return new PacienteDetailDTO(pacienteLogic.createPaciente(paciente.toEntity()));
    }
    /**
     * Modificar un paciente
     * @param id ID del paciente a modificar
     * @param dto los datos del paciente a modificar
     * @return paciente modificada
     */
    @PUT
    @Path("pacientes/{pacienteId: \\d+}")    
    public PacienteDetailDTO updatePaciente(@PathParam("pacienteId") Long id, PacienteDetailDTO dto) {
        PacienteEntity entity = dto.toEntity();
        entity.setId(id);
        return new PacienteDetailDTO(pacienteLogic.updatePaciente(entity));
 }
    /**
     * Elimina un paciente
     * @param id ID del paciente a eliminar
     * @return  boolean si indica si el paciente fue eliminado
     */
    @DELETE
    @Path("pacientes/{pacienteId: \\d+}") 
    public void deletePaciente(@PathParam("pacienteId") Long id){
        pacienteLogic.deletePaciente(id);
    }
    /**
     * Obtiene un paciente
     * @param id ID del paciente a obtener
     * @return el paciente deseado
     */
    @GET
    @Path("pacientes/{pacienteId: \\d+}") 
    public PacienteDTO getPacienteId(@PathParam("pacienteId") Long id){
        return new PacienteDetailDTO(pacienteLogic.getPaciente(id));
    }
}
