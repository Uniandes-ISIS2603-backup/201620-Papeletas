/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.HorarioDTO;
import co.edu.uniandes.rest.hospital.dtos.HorarioDTO.meses;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ac.cabezas716
 */
public class HorarioMock {
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(HorarioMock.class.getName());
     
    private HorarioDTO horario;
    
    
    public HorarioMock(){
        horario = new HorarioDTO();
    }
    
    public HorarioDTO darHorario(){
        return horario;
    }
    
    public HorarioDTO crearHorario(int pMes, int pAño){
        horario = new HorarioDTO(meses.values()[pMes] , pAño);
        return horario; 
    }
    
    public HorarioDTO actualizarHorario(HorarioDTO pHorario){
        horario = pHorario;
        return horario;
    }
    
    public void borrarHorario(){
        horario = new HorarioDTO();
    }
}
