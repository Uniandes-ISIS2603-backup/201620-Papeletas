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

 
/*
    
    public CityDTO createCity(CityDTO newCity) throws CityLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);
    	
    	// la nueva ciudad tiene id ?
    	if ( newCity.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (CityDTO city : horarios) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newCity.getId())){
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new CityLogicException("Ya existe una ciudad con ese id");
	            }
	        }
	        
	    // la nueva ciudad no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para la nueva ciudad");
    		long newId = 1;
	        for (CityDTO city : horarios) {
	            if (newId <= city.getId()){
	                newId =  city.getId() + 1;
	            }
	        }
	        newCity.setId(newId);
    	}
    	
        // agrega la ciudad
    	logger.info("agregando ciudad " + newCity);
        horarios.add(newCity);
        return newCity;
    }

    public CityDTO getCity(long pId)throws CityLogicException{
        logger.info("recibiendo solicitud de buscar ciudad con id " + pId);
        for (CityDTO city : horarios) {
            if(city.getId() == pId){
                logger.info("Ciudad encontrada: " + city);
                return city;
            }
                
        }
        logger.severe("No existe una ciudad con ese id");
        throw new CityLogicException("No existe una ciudad con ese id");
    }
    
    public void deleteCity(long pId)throws CityLogicException{
        logger.info("recibiendo solicitud de eliminar ciudad con id " + pId);
        for(int i = 0; i < horarios.size(); i++){
            CityDTO actual = horarios.get(i);
            if(actual.getId() == pId){
                logger.info("Eliminando ciudad: " + actual);
                horarios.remove(i);
                return;
            }    
        }
        logger.severe("No existe una ciudad con ese id");
        throw new CityLogicException("No existe una ciudad con ese id");
    }
    
    public CityDTO updateCity(long pId, CityDTO pCity)throws CityLogicException{
        logger.info("recibiendo solicitud de actualizar ciudad con id " + pId);
        int pos = -1;
        for(int i = 0; i < horarios.size(); i++){
            CityDTO actual = horarios.get(i);
            if(actual.getId() == pId){
                logger.info("Ciudad a cambiar encontrada");
                pos = i;
            }
            else if(actual.getId() == pCity.getId()){
                logger.severe("El id de la nueva ciudad ya esta en uso");
                throw new CityLogicException("El id de la nueva ciudad ya esta en uso");
            }
        }
        if(pos!= -1){
            logger.info("Cambiando ciudad");
            horarios.set(pos, pCity);
            return pCity;
        }
        logger.severe("No existe una ciudad con ese id");
        throw new CityLogicException("No existe una ciudad con ese id");
    }*/
}
