package co.edu.uniandes.rest.hospital.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import co.edu.uniandes.rest.hospital.exceptions.EspecializacionException;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;



public class EspecializacionMock
{
	private final static Logger logger = Logger.getLogger(EspecializacionMock.class.getName());
	
	/**
	 * Lista de esepcialidades en el hospital
	 */	
	private static ArrayList<EspecializacionDTO> especialidades;
	
	/**
	 * 
	 * @return lista de especialidades en el hospital
	 * @throws EspecializacionException
	 */
	public List<EspecializacionDTO> getSpecialties() throws EspecializacionException
	{
    	if (especialidades == null)
    	{
    		logger.severe("Error interno: lista de especialidades no existe.");
    		throw new EspecializacionException("Error interno: lista de especialidades no existe.");    		
    	}
    	
    	logger.info("retornando todas las especialidades");
    	return especialidades;
    }
	
	
	public EspecializacionDTO createSpecialty(EspecializacionDTO specialty) throws EspecializacionException
	{
    	logger.info("recibiendo solicitud de agregar especialidad" + specialty);
    	
    	{
    		logger.info("Generando id para la especializacion");
	        specialty.setId(especialidades.size());
    	}
    	
    	logger.info("agregando especialidad" + specialty);
        especialidades.add(specialty);
        return specialty;
    }
}