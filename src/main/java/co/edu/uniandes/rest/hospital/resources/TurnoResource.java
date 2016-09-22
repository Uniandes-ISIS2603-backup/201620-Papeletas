/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.hospital.dtos.TurnoDTO;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;
import co.edu.uniandes.rest.hospital.mocks.TurnoLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Clase que implementa el recurso REST correspondiente a "turnos".
 *
 * @author ac.cabezas716
 */
@Path("")
public class TurnoResource {

    TurnoLogicMock turnoLogicMock = new TurnoLogicMock();

    /**
     * Obtiene el listado general de turnos.
     *
     * @return lista de turnos
     * @throws TurnoLogicException excepción retornada por la lógica
     */
    @Path("turnos")
    @GET
    public List<TurnoDTO> getTurnos() throws TurnoLogicException {
        return turnoLogicMock.getTurnos();
    }

    /**
     * Obtiene el listado de turnos de un médico.
     *
     * @return lista de turnos del médico
     * @throws TurnoLogicException excepción retornada por la lógica
     */
    @Path("medico/{idMedico: \\d+}/turnos")
    @GET
    public List<TurnoDTO> getTurnos(@PathParam("idMedico") Long idMedico) throws TurnoLogicException {
        return turnoLogicMock.getTurnos(idMedico);
    }

    /**
     * Obtiene un turno
     *
     * @param id identificador del turno
     * @return turno encontrado
     * @throws TurnoLogicException cuando el turno no existe
     */
    @Path("medico/{idMedico: \\d+}/turnos/{id: \\d+}")
    @GET
    public TurnoDTO getTurno(@PathParam("id") Long id) throws TurnoLogicException {
        return turnoLogicMock.getTurno(id);
    }

    /**
     * Agrega un turno
     *
     * @param turno turno a agregar
     * @return datos del turno a agregar
     * @throws TurnoLogicException cuando ya existe un turno con el id
     * suministrado
     */
    @Path("medico/{idMedico: \\d+}/turnos")
    @POST
    public TurnoDTO createTurno(@PathParam("idMedico") Long idMedico, TurnoDTO turno) throws TurnoLogicException {
        return turnoLogicMock.createTurno(idMedico, turno);
    }

    /**
     * Actualiza los datos de un turno
     *
     * @param id identificador del turno a modificar
     * @param turno turno a modificar
     * @return datos del turno modificado
     * @throws TurnoLogicException cuando no existe un turno con el id
     * suministrado
     */
    @PUT
    @Path("medico/{idMedico: \\d+}/turnos/{id: \\d+}")
    public TurnoDTO updateTurno(@PathParam("id") Long id, TurnoDTO turno) throws TurnoLogicException {
        return turnoLogicMock.updateTurno(id, turno);
    }

    /**
     * Elimina los datos de un turno
     *
     * @param id identificador del turno a eliminar
     * @throws TurnoLogicException cuando no existe un turno con el id
     * suministrado
     */
    @DELETE
    @Path("medico/{idMedico: \\d+}/turnos/{id: \\d+}")
    public void deleteTurno(@PathParam("id") Long id) throws TurnoLogicException {
        turnoLogicMock.deleteTurno(id);
    }

}
