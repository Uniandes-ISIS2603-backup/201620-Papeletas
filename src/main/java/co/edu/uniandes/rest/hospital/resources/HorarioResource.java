/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.hospital.dtos.HorarioDTO;
import co.edu.uniandes.rest.hospital.exceptions.HorarioLogicException;
import co.edu.uniandes.rest.hospital.mocks.HorarioLogicMock;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

/**
 *
 * @author ac.cabezas716
 */
@Path("horarios")
@Produces("application/json")
@Consumes("application/json")
public class HorarioResource {
    
    HorarioLogicMock horarioLogica = new HorarioLogicMock();
    
    @GET
    public List<HorarioDTO> getHorarios() throws HorarioLogicException{
        return horarioLogica.getHorarios();
    }
    /*
    @POST
    public HorarioDTO createHorario(HorarioDTO pHorario){
        return horarioLogica.crearHorario(pHorario);
    }
    
    @PUT
    public HorarioDTO putHorario(HorarioDTO pHorario){
        return horarioLogica.actualizarHorario(pHorario);
    }
    
    @DELETE
    public void deleteHorario(){
        horarioLogica.borrarHorario();
    }
    */
    
}
