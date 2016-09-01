/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.HorarioDTO;
import co.edu.uniandes.rest.hospital.exceptions.HorarioLogicException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Horario logic Mock
 * Mock del recurso horario
 * @author ac.cabezas716
 */
public class HorarioLogicMock {
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(HorarioLogicMock.class.getName());
	
	// listado de horarios
    private static ArrayList<HorarioDTO> horarios;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public HorarioLogicMock() {

    	if (horarios == null) {
            horarios = new ArrayList();
            HorarioDTO nuevo1 = new HorarioDTO(HorarioDTO.TipoUsuario.MEDICO, "Jose Amortegui");
            nuevo1.addJornada(HorarioDTO.DiaSemana.LUNES, 7, 19);
            nuevo1.addJornada(HorarioDTO.DiaSemana.JUEVES, 10, 14);
            horarios.add(nuevo1);
            HorarioDTO nuevo2 = new HorarioDTO(HorarioDTO.TipoUsuario.CONSULTORIO, "204_Cardiología");
            nuevo2.addEvento(new Date(472574600000L),new Date(1472575500000L));
            horarios.add(nuevo2);
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de horarios");
    	logger.info("ciudades" + horarios );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<HorarioDTO> getHorarios() throws HorarioLogicException {
    	if (horarios == null) {
    		logger.severe("Error interno: lista horarios no existe.");
    		throw new HorarioLogicException("Error interno: lista de horarios no existe.");    		
    	}
    	
    	logger.info("retornando todos los horarios");
    	return horarios;
    }
    
    public HorarioDTO createCity(HorarioDTO newHorario) throws HorarioLogicException {
    	logger.info("recibiendo solicitud de agregar horario" + newHorario);
    	
    	// el nuevo horario tiene nombre ?
    	if ( newHorario.getNombre().equals("") ) {
	    	// busca el horario con el nombre suministrado
	        for (HorarioDTO horario : horarios) {
	        	// si existe un horario con ese nombre
	            if (horario.getNombre().equals(newHorario.getNombre())){
	            	logger.severe("Ya existe un horario con ese nombre");
	                throw new HorarioLogicException("Ya existe un horario con ese nombre");
	            }
	        }
	        
	    
    	} else {

    		logger.severe("Se deben ingresar los nombres de los propietarios de los horarios");
	        throw new HorarioLogicException("Ya existe un horario con ese nombre");
    	}
    	
        // agrega la ciudad
    	logger.info("agregando horario " + newHorario);
        horarios.add(newHorario);
        return newHorario;
    }
    
        
    public void deleteHorario(String pNombre)throws HorarioLogicException{
        logger.info("recibiendo solicitud de eliminar el horario con nombre " + pNombre);
        for(int i = 0; i < horarios.size(); i++){
            HorarioDTO actual = horarios.get(i);
            if(actual.getNombre().equals(pNombre)){
                logger.info("Eliminando horario: " + actual);
                horarios.remove(i);
                return;
            }    
        }
        logger.severe("No existe un horario con ese nombre");
        throw new HorarioLogicException("No existe un horario con ese nombre");
    }
    
    public HorarioDTO updateHorario(String pNombre, HorarioDTO pHorario)throws HorarioLogicException{
        logger.info("recibiendo solicitud de actualizar horario con nombre " + pNombre);
        int pos = -1;
        for(int i = 0; i < horarios.size(); i++){
            HorarioDTO actual = horarios.get(i);
            if(actual.getNombre().equals(pNombre)){
                logger.info("Horario a cambiar encontrada");
                pos = i;
            }
            else if(actual.getNombre().equals(pHorario.getNombre())){
                logger.severe("El nombre dle nuevo horario ya esta en uso");
                throw new HorarioLogicException("El nombre dle nuevo horario ya esta en uso");
            }
        }
        if(pos!= -1){
            logger.info("Cambiando horario");
            horarios.set(pos, pHorario);
            return pHorario;
        }
        logger.severe("No existe una horario con ese nombre");
        throw new HorarioLogicException("No existe una horario con ese nombre");
    }
}
