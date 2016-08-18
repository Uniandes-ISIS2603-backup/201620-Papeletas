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
 * Horario logic Mock
 * Mock del recurso horario
 * @author ac.cabezas716
 */
public class HorarioLogicMock {
    /**
     * Objeto para presentar logs de las operaciones
     */
    private final static Logger logger = Logger.getLogger(HorarioLogicMock.class.getName());

    /**
     * Horario  
     */
    private static HorarioDTO horario;
    
    
    public HorarioLogicMock(){
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	logger.info("Inicializa el horario");
    	logger.info("Horario " + horario );
        if(horario == null){
            horario = new HorarioDTO(meses.DICIEMBRE, 2016);
        }
    }
    
    /**
     * Retorna el horario 
     * @return 
     */
    public HorarioDTO darHorario(){
        logger.info("Recibiendo solicitud de dar horario.");
        
        logger.info("Retornando horario.");
        return horario;
    }
    
    /**
     * Crea un nuevo Horario
     * @param pHorario
     * @return Horario creado
     */
    public HorarioDTO crearHorario(HorarioDTO pHorario){
        logger.info("Recibiendo solicitud de crear nuevo horario " + pHorario);
        
        horario = new HorarioDTO(pHorario.getMonth() , pHorario.getYear());
        logger.info("Horario modificado " + horario);
        return pHorario; 
    }
    
    /**
     * Actualiza el horario actual
     * @param pHorario 
     * @return 
     */
    public HorarioDTO actualizarHorario(HorarioDTO pHorario){
        logger.info("Recibiendo solicitud de actualizar horario " + pHorario);
        horario = pHorario;
        return horario;
    }
    
    /**
     * Borra el horario actual
     */
    public void borrarHorario(){
        logger.info("Recibiendo solicitud de eliminar horario.");
        
        logger.info("Eliminando horario.");
        horario = new HorarioDTO(meses.ENERO, 1997);
        
        logger.info("Horario eliminado y sustituido por el deafult: " + horario);
        
    }
}
