/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.exceptions.CitaException;
import co.edu.uniandes.rest.hospital.dtos.CitaDTO;
import co.edu.uniandes.rest.hospital.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc.useche10
 */
public class CitaMock {
   
   // objeto para presentar logs de las operaciones
   private final static Logger logger = Logger.getLogger(CitaMock.class.getName());
   // listado de citas
   private static ArrayList<CitaDTO> citas;
   
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public CitaMock() {
        
        if (citas == null) {
            citas = new ArrayList<>();
            citas.add(new CitaDTO(01,new Date(), new MedicoDTO("Juan Lara", 5L, "20/04-29/04", "Oftalmologo"),new ConsultorioDTO(1L, false),new PacienteDTO(1L, "Diego", "Castro",18,10)));
           
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de citas");
    	logger.info("citas" + citas );
    }   
    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria  
	 */    
    public List<CitaDTO> getCitas() throws CitaException {
    	if (citas == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new CitaException("Error interno: lista de ciudades no existe.");    		
    	}
    	
    	logger.info("retornando todas las ciudades");
    	return citas;
    }
    
     /**
     * Agrega una cita la lista.
     * @param newCita ciudad a adicionar
     * @throws CitaException cuando ya existe una cita con el id suministrado
     * @return cita agregada
     */
    public CitaDTO createCita(CitaDTO newCita) throws CitaException {
    	logger.info("recibiendo solicitud de agregar cita " + newCita);
    	
    	// la nueva cita tiene id ?
    	if ( newCita.getId() != 0 ) {
	    	// busca la cita con el id suministrado
	        for (CitaDTO cita : citas) {
	        	// si existe una cita con ese id
	            if (Objects.equals(cita.getId(), newCita.getId())){
	            	logger.severe("Ya existe una cita con ese id");
	                throw new CitaException("Ya existe una cita con ese id");
	            }
	        }
	        
	    // la nueva cita no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id paa la nueva cita");
    		long newId = 1;
	        for (CitaDTO cita : citas) {
	            if (newId <= cita.getId()){
	                newId =  cita.getId() + 1;
	            }
	        }
	        newCita.setId(newId);
    	}
    	
        // agrega la ciudad
    	logger.info("agregando ciudad " + newCita);
        citas.add(newCita);
        return newCita;
    }
    
    public CitaDTO updateCita(long id, CitaDTO pCita)
    {
        CitaDTO cita = null;
        boolean encontrado = false;
        for(int i = 0; i<citas.size()&& !encontrado;i++)
        {
           if(citas.get(i).getId()==id)
           {
               citas.get(i).setFecha(pCita.getFecha());
               citas.get(i).setDuracionMins(pCita.getDuracionMins());
               citas.get(i).setFueCompletada(pCita.getFueCompletada());
               citas.get(i).setHayCita(pCita.getHayCita());
               
               cita = citas.get(i);
               encontrado = true;
           }
        }
        return cita;
    }

    public CitaDTO getCita(long id) 
    {
        CitaDTO cita = null;
        boolean encontrado = false;
        for(int i = 0; i<citas.size()&& !encontrado;i++)
        {
           if(citas.get(i).getId()==id)
           {
               cita= citas.get(i);
               encontrado = true;
           }
        }
        return cita;
    }

    public void deleteCita(long id) 
    {
        CitaDTO ciudad = null;
        boolean encontrado = false;
        for(int i = 0; i<citas.size()&& !encontrado;i++)
        {
           if(citas.get(i).getId()==id)
           {
               ciudad = citas.get(i);
               encontrado = true;
           }
        }
        citas.remove(ciudad);
    }
}
