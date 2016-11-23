/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;
import  co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.rest.hospital.dtos.CitaDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jc.useche10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/medico/{idMedico: \\d+}/citas")
public class CitaResource {
    
    @Inject
    private ICitaLogic citaLogic;

    @Inject
    private IMedicoLogic medicoLogic;

    @PathParam("idMedico")
    private Long medicoId;
    /**
     * Convierte una lista de CitaEntity a una lista de
     * CitaDetailDTO
     *
     * @param entityList Lista de CitaEntity a convertir
     * @return Lista de CitaDetailDTO convertida
     *
     */
    private List<CitaDetailDTO> listEntity2DTO(List<CitaEntity> entityList) {
        List<CitaDetailDTO> list = new ArrayList<>();
        for (CitaEntity entity : entityList) {
            list.add(new CitaDetailDTO(entity));
        }
        return list;
    }
/**
    public void existsMedico(Long citaId) {
        MedicoDetailDTO cita = new MedicoDetailDTO(citaLogic.getMedico(citaId));
        if (cita == null) {
            throw new WebApplicationException("La medico no existe", 404);
        }
    }
*/
    public void existsCita(Long citaId) {
        CitaDetailDTO cita = new CitaDetailDTO(citaLogic.getCita(citaId));
        if (cita == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }

    /**
     * Obtiene los datos de los Citas de un medico a partir del ID del
     *  Medico
     *
     *
     * @return Lista de CitaDetailDTO con los datos del Cita
     * consultado
     *
     */
    @GET
    public List<CitaDetailDTO> getCitas() {
        //existsMedico(medicoId);

        List<CitaEntity> citas = citaLogic.getCitas(medicoId);

        return listEntity2DTO(citas);
    }

    /**
     * Obtiene los datos de una instancia de Cita a partir de su ID
     * asociado a un Medico
     *
     * @param citaId Identificador de la instancia a consultar
     * @return Instancia de CitaDetailDTO con los datos del Cita
     * consultado
     *
     */
    @GET
    @Path("{citaId: \\d+}")
    public CitaDetailDTO getCita(@PathParam("citaId") Long citaId) {
       // existsMedico(medicoId);
        CitaEntity entity = citaLogic.getCita(citaId);
        if (entity.getMedico() != null && !medicoId.equals(entity.getMedico().getId())) {
            throw new WebApplicationException(404);
        }

        return new CitaDetailDTO(entity);
    }

    /**
     * Asocia un Cita existente a un Medico
     *
     * @param dto Objeto de CitaDetailDTO con los datos nuevos
     * @return Objeto de CitaDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
        public CitaDetailDTO createCita(CitaDetailDTO dto) throws HospitalLogicException {
        //existsMedico(medicoId);
        return new CitaDetailDTO(citaLogic.createCita(medicoId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Cita.
     *
     * @param citaId Identificador de la instancia de Cita a
     * modificar
     * @param dto Instancia de CitaDetailDTO con los nuevos datos.
     * @return Instancia de CitaDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{citaId: \\d+}")
    public CitaDetailDTO updateCita(@PathParam("citaId") Long citaId, CitaDetailDTO dto) {
        //existsMedico(medicoId);
        existsCita(citaId);
        CitaEntity entity = dto.toEntity();
        entity.setId(citaId);
        CitaEntity oldEntity = citaLogic.getCita(citaId);
        return new CitaDetailDTO(citaLogic.updateCita(medicoId, entity));
    }

    /**
     * Elimina una instancia de Cita de la base de datos.
     *
     * @param citaId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{citaId: \\d+}")
    public void deleteCita(@PathParam("citaId") Long citaId) {

       // existsMedico(medicoId);
        existsCita(citaId);
        citaLogic.deleteCita(citaId);
    }
}
