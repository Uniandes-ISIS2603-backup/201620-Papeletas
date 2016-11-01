/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IPacienteLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.CitaDetailDTO;
import co.edu.uniandes.rest.hospital.dtos.PacienteDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author df.castro12
 */

@Path("/pacientes/{pacienteId: \\d+}/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteCitaResource {
    
    @Inject
    private IPacienteLogic pacienteLogic;
    
    @Inject
    private ICitaLogic citaLogic;
    
     /**
     * Convierte una lista de CitaEntity a una lista de CitaDetailDTO.
     *
     * @param entityList Lista de CitaEntity a convertir.
     * @return Lista de CitaDetailDTO convertida.
     *
     */
    private List<CitaDetailDTO> citasListEntity2DTO(List<CitaEntity> entityList) {
        List<CitaDetailDTO> list = new ArrayList<>();
        for (CitaEntity entity : entityList) {
            list.add(new CitaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CitaDetailDTO a una lista de CitaEntity.
     *
     * @param dtos Lista de CitaDetailDTO a convertir.
     * @return Lista de CitaEntity convertida.
     *
     */
    private List<CitaEntity> citasListDTO2Entity(List<CitaDetailDTO> dtos) {
        List<CitaEntity> list = new ArrayList<>();
        for (CitaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    public void existsPaciente(Long pacienteId) {
        PacienteDetailDTO dept = new PacienteDetailDTO(pacienteLogic.getPaciente(pacienteId));
        if (dept == null) {
            throw new WebApplicationException(404);
        }
    }
    
    /**
     * Obtiene una colección de instancias de CitaDetailDTO asociadas a una
     * instancia de Paciente
     *
     * @param pacienteId Identificador de la instancia de Paciente
     * @return Colección de instancias de CitaDetailDTO asociadas a la
     * instancia de Paciente
     *
     */
    @GET
    @Path("citas")
    public List<CitaDetailDTO> listCitas(@PathParam("pacienteId") Long pacienteId) {
        existsPaciente(pacienteId);
        return citasListEntity2DTO(pacienteLogic.getCitas(pacienteId));
    }
    
        /**
     * Obtiene una instancia de Cita asociada a una instancia de Paciente
     *
     * @param pacienteId Identificador de la instancia de Paciente
     * @param citaId Identificador de la instancia de Cita
     * @return la instancia de CitaDetailDTO
     *
     */
    @GET
    @Path("citas/{citaId: \\d+}")
    public CitaDetailDTO getCitas(@PathParam("pacienteId") Long pacienteId, @PathParam("citaId") Long citaId) {
        existsPaciente(pacienteId);
        return new CitaDetailDTO(pacienteLogic.getCita(pacienteId, citaId));
    }

    /**
     * Asocia un Cita existente a un Paciente
     *
     * @param pacienteId Identificador de la instancia de Paciente
     * @param citaId Identificador de la instancia de Cita
     * @return Instancia de CitaDetailDTO que fue asociada a Paciente
     *
     */
    @POST
    @Path("citas/{citaId: \\d+}")
    public CitaDetailDTO addCita(@PathParam("pacienteId") Long pacienteId, @PathParam("citaId") Long citaId) throws HospitalLogicException {
        existsPaciente(pacienteId);
        CitaEntity cita = citaLogic.getCita(citaId);
        return new CitaDetailDTO(pacienteLogic.addCita(pacienteId, cita));
    }
    
    /**
     * Desasocia un Employee existente de un Department existente
     *
     * @param departmentId Identificador de la instancia de Department
     * @param employeeId Identificador de la instancia de Employee
     *
     */
    @DELETE
    @Path("citas/{citaId: \\d+}")
    public void removeCita(@PathParam("pacienteId") Long pacienteId, @PathParam("citaId") Long citaId) {
        existsPaciente(pacienteId);
        pacienteLogic.removeCita(pacienteId, citaId);
    }
}
