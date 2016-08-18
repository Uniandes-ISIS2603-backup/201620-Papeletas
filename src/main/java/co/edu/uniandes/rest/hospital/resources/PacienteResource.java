/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;
import co.edu.uniandes.rest.hospital.mocks.PacienteLogicMock;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;


/**
 *
 * @author df.castro12
 */
@Path("pacientes")
@Produces("application/json")
public class PacienteResource {
    PacienteLogicMock pacienteLogic = new PacienteLogicMock();

    /**
     * Obtiene el listado de pacientes.
     *
     * @return lista de pacientes
     * @throws HospitalLogicException excepción retornada por la lógica
     */
    @GET
    public List<PacienteDTO> getPacientes() throws HospitalLogicException {
        return pacienteLogic.getPacientes();
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
    public PacienteDTO createPaciente(PacienteDTO paciente) throws HospitalLogicException {
        return pacienteLogic.createPaciente(paciente);
    }
    /**
     * Modificar un paciente
     * @param id ID del paciente a modificar
     * @param dto los datos del paciente a modificar
     * @return paciente modificada
     */
    @PUT
    @Path("{id: \\d+}")    
    public PacienteDTO updatePaciente(@PathParam("id") Long id, PacienteDTO dto) {
        return pacienteLogic.updatePaciente(id, dto);
 }
    /**
     * Elimina un paciente
     * @param id ID del paciente a eliminar
     * @return  boolean si indica si el paciente fue eliminado
     */
    @DELETE
    @Path("{id: \\d+}") 
    public boolean deletePaciente(@PathParam("id") Long id){
        return pacienteLogic.deletePaciente(id);
    }
    /**
     * Obtiene un paciente
     * @param id ID del paciente a obtener
     * @return el paciente deseado
     */
    @GET
    @Path("{id: \\d+}") 
    public PacienteDTO getPacienteId(@PathParam("id") Long id){
        return pacienteLogic.getPaciente(id);
    }
}
