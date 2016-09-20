/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.TurnoDTO;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ac.cabezas716
 */
public class TurnoLogicMock {
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(HorarioLogicMock.class.getName());
	
	// listado de turnos
    private static ArrayList<TurnoDTO> turnos;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public TurnoLogicMock() {

    	if (turnos == null) {
            turnos = new ArrayList();
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de turnos");
    	logger.info("horarioss" + turnos );
    }    
    
	/**
	 * Obtiene el listado de turnos. 
	 * @return lista de turnos
	 * @throws TurnoLogicException cuando no existe la lista en memoria  
	 */    
    public List<TurnoDTO> getHorarios() throws TurnoLogicException {
    	if (turnos == null) {
    		logger.severe("Error interno: lista de turnos no existe.");
    		throw new TurnoLogicException("Error interno: lista de turnos no existe.");    		
    	}
    	
    	logger.info("retornando todos los horarios");
    	return turnos;
    }
    public TurnoDTO getTurno(Long pId)throws TurnoLogicException{
        logger.info("recibiendo solicitud de buscar turno por id " + pId);
        for (TurnoDTO actual : turnos) {
            if(actual.getId().equals(pId)){
                logger.info("Turno encontrado: " + actual);
                return actual;
            }
                
        }
        logger.severe("No existe un turno con ese nombre");
        throw new TurnoLogicException("No existe un turno con ese nombre");
    }
    public TurnoDTO createTurno(TurnoDTO newTurno) throws TurnoLogicException {
    	logger.info("recibiendo solicitud de agregar turno " + newTurno);
    	
    	// el nuevo turno tiene id ?
    	if (newTurno.getId() != null) {
	    	// busca el turno con el id suministrado
	        for (TurnoDTO t : turnos) {
	        	// si existe un turno con ese id
	            if (t.getId().equals(newTurno.getId())){
	            	logger.severe("Ya existe un turno con ese id");
	                throw new TurnoLogicException("Ya existe un turno con ese id");
	            }
	        }
	        
	    // el nuevo turno no tiene id ? 
    	} else {

    		// genera un id para el turno
    		logger.info("Generando id para el nuevo turno");
    		Long newId = 1L;
	        for (TurnoDTO turno : turnos) {
	            if (newId <= turno.getId()){
	                newId =  turno.getId() + 1;
	            }
	        }
	        newTurno.setId(newId);
    	}
    	
        // agrega el horario
    	logger.info("agregando horario " + newTurno);
        turnos.add(newTurno);
        return newTurno;
    }
    
    public void deleteTurno(Long pId)throws TurnoLogicException{
        logger.info("recibiendo solicitud de eliminar turno con id " + pId);
        for(int i = 0; i < turnos.size(); i++){
            TurnoDTO actual = turnos.get(i);
            if(actual.getId().equals(pId)){
                logger.info("Eliminando turno: " + actual);
                turnos.remove(i);
                return;
            }    
        }
        logger.severe("No existe un turno con ese id");
        throw new TurnoLogicException("No existe un turno con ese id");
    }
    
        
    
    public TurnoDTO updateTurno(Long pId, TurnoDTO pTurno)throws TurnoLogicException{
        logger.info("recibiendo solicitud de actualizar turno con id " + pId);
        Long pos = null;
        for(int i = 0; i < turnos.size(); i++){
            TurnoDTO actual = turnos.get(i);
            if(actual.getId().equals(pId)){
                logger.info("Turno a cambiar encontrada");
                pos = Long.valueOf(i);
            }
            else if(actual.getId().equals(pTurno.getId())){
                logger.severe("El id del nuevo turno ya esta en uso");
                throw new TurnoLogicException("El id del nuevo turno ya esta en uso");
            }
        }
        if(pos!= -1){
            logger.info("Cambiando turno");
            turnos.set((int)(long)pos, pTurno);
            return pTurno;
        }
        logger.severe("No existe una turno con ese id");
        throw new TurnoLogicException("No existe una turno con ese id");
    }
}
