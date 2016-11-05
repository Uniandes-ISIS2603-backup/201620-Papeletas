/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;


import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.api.ITurnoLogic;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.TurnoDTO;
import co.edu.uniandes.rest.hospital.dtos.TurnoDetailDTO;
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
 * Clase que implementa el recurso REST correspondiente a "turnos".
 *
 * @author ac.cabezas716
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/medico/{idMedico: \\d+}/turnos")
public class TurnoResource {
    
    @Inject
    private ITurnoLogic turnoLogic;

    @Inject
    private IMedicoLogic medicoLogic;

    @PathParam("medicoId")
    private Long medicoId;
    /**
     * Convierte una lista de TurnoEntity a una lista de
     * TurnosDetailDTO
     *
     * @param entityList Lista de TurnoEntity a convertir
     * @return Lista de TurnoDetailDTO convertida
     *
     */
    private List<TurnoDetailDTO> listEntity2DTO(List<TurnoEntity> entityList) {
        List<TurnoDetailDTO> list = new ArrayList<>();
        for (TurnoEntity entity : entityList) {
            list.add(new TurnoDetailDTO(entity));
        }
        return list;
    }
/**
    public void existsMedico(Long pTurnoId) {
        MedicoDetailDTO turno = new MedicoDetailDTO(turnoLogic.getMedico(pTurnoId));
        if (turno == null) {
            throw new WebApplicationException("La medico no existe", 404);
        }
    }
*/
    public void existsTurno(Long pTurnoId) {
        TurnoDetailDTO turno = new TurnoDetailDTO(turnoLogic.getTurno(pTurnoId));
        if (turno == null) {
            throw new WebApplicationException("El Turno no existe", 404);
        }
    }

    /**
     * Obtiene los datos de los Turnos de un medico a partir del ID del
     *  Medico
     *
     *
     * @return Lista de TurnoDetailDTO con los datos del Turno
     * consultado
     *
     */
    @GET
    public List<TurnoDetailDTO> getTurnos() {
        //existsMedico(medicoId);

        List<TurnoEntity> turnos = turnoLogic.getTurnos(medicoId);

        return listEntity2DTO(turnos);
    }

    /**
     * Obtiene los datos de una instancia de Turno a partir de su ID
     * asociado a un Medico
     *
     * @param turnoId Identificador de la instancia a consultar
     * @return Instancia de TurnoDetailDTO con los datos del Turno
     * consultado
     *
     */
    @GET
    @Path("{turnoId: \\d+}")
    public TurnoDTO geTurno(@PathParam("turnoId") Long turnoId) {
       // existsMedico(medicoId);
        TurnoEntity entity = turnoLogic.getTurno(turnoId);
        if (entity.getMedico() != null && !medicoId.equals(entity.getMedico().getId())) {
            throw new WebApplicationException(404);
        }

        return new TurnoDetailDTO(entity);
    }

    /**
     * Asocia un Turno existente a un Medico
     *
     * @param dto Objeto de TurnoDetailDTO con los datos nuevos
     * @return Objeto de TurnoDetailDTO con los datos nuevos y su ID.
     *
     */
    @POST
        public TurnoDetailDTO createTurno(TurnoDetailDTO dto) throws HospitalLogicException {
        //existsMedico(medicoId);
        return new TurnoDetailDTO(turnoLogic.createTurno(medicoId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Turno.
     *
     * @param turnoId Identificador de la instancia de Tunro a
     * modificar
     * @param dto Instancia de TunroDetailDTO con los nuevos datos.
     * @return Instancia de TunroDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{turnoId: \\d+}")
    public TurnoDetailDTO updateTurno(@PathParam("turnoId") Long turnoId, TurnoDetailDTO dto) {
        //existsMedico(medicoId);
        existsTurno(turnoId);
        TurnoEntity entity = dto.toEntity();
        entity.setId(turnoId);
        TurnoEntity oldEntity = turnoLogic.getTurno(turnoId);
        return new TurnoDetailDTO(turnoLogic.updateTurno(medicoId, entity));
    }

    /**
     * Elimina una instancia de Turno de la base de datos.
     *
     * @param turnoId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{turnoId: \\d+}")
    public void deleteTurno(@PathParam("turnoId") Long turnoId) {

       // existsMedico(medicoId);
        existsTurno(turnoId);
        turnoLogic.deleteTurno(turnoId);
    }
}