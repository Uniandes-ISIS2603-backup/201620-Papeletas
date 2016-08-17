/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.rest.cities.dtos.HorarioDTO;
import co.edu.uniandes.rest.hospital.mocks.HorarioMock;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ac.cabezas716
 */
@Path("horario")
@Produces("application/json")
public class HorarioResource {
    HorarioMock horarioLogica = new HorarioMock();
    
    @GET
    public HorarioDTO getHorario(){
        return horarioLogica.darHorario();
    }
    
    @POST
    public HorarioDTO createHorario(int pMes, int pAño){
        return horarioLogica.crearHorario(pMes, pAño);
    }
    
    @PUT
    public HorarioDTO putHorario(HorarioDTO pHorario){
        return horarioLogica.actualizarHorario(pHorario);
    }
   
    
    @DELETE
    public void deleteHorario(){
        horarioLogica.borrarHorario();
    }
    
    
}