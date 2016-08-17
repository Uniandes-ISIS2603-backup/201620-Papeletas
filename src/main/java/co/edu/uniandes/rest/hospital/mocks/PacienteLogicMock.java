/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;
import co.edu.uniandes.rest.cities.exceptions.HospitalLogicException;
/**
 *
 * @author df.castro12
 */
public class PacienteLogicMock {
    
    	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(PacienteLogicMock.class.getName());
	
	// listado de ciudades
    private static ArrayList<PacienteDTO> pacientes;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public PacienteLogicMock() {

    	if (pacientes == null) {
            pacientes = new ArrayList<>();
            pacientes.add(new PacienteDTO(1L, "Diego", "Castro",18,10));
            pacientes.add(new PacienteDTO(2L, "Manuela", "Welch",19,8));
            pacientes.add(new PacienteDTO(3L, "Nicolas", "Ramirez",18,6));
            
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de pacientes");
    	logger.info("pacientes" + pacientes );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de pacientes
	 * @throws HospitalLogicException cuando no existe la lista en memoria  
	 */    
    public List<PacienteDTO> getPacientes() throws HospitalLogicException {
    	if (pacientes == null) {
    		logger.severe("Error interno: lista de pacientes no existe.");
    		throw new HospitalLogicException("Error interno: lista de pacientes no existe.");    		
    	}
    	
    	logger.info("retornando todas las ciudades");
    	return pacientes;
    }

 

    /**
     * Agrega un paciente a la lista.
     * @param newPaciente paciente a adicionar
     * @throws HospitalLogicException cuando ya existe un paciente con el id suministrado
     * @return paciente agregado
     */
    public PacienteDTO createPaciente(PacienteDTO newPaciente) throws HospitalLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + newPaciente);
    	
    	// el nuevo paciente tiene id ?
    	if ( newPaciente.getId() != null ) {
	    	// busca el paciente con el id suministrado
	        for (PacienteDTO city : pacientes) {
	        	// si existe un paciente con ese id
	            if (Objects.equals(city.getId(), newPaciente.getId())){
	            	logger.severe("Ya existe un paciente con ese id");
	                throw new HospitalLogicException("Ya existe un paciente con ese id");
	            }
	        }
	        
	    // el nuevo paciente no tiene id ? 
    	} else {

    		// genera un id para el paciente
    		logger.info("Generando id paa la nueva ciudad");
    		long newId = 1;
	        for (PacienteDTO paciente : pacientes) {
	            if (newId <= paciente.getId()){
	                newId =  paciente.getId() + 1;
	            }
	        }
	        newPaciente.setId(newId);
    	}
    	
        // agrega el paciente
    	logger.info("agregando paciente " + newPaciente);
        pacientes.add(newPaciente);
        return newPaciente;
    }
    /**
     * Elimina al paciente con el id dado
     * @param id ID del paciente a eliminar
     * @return boolean si indica si se elimino
     */
    public boolean deletePaciente(Long id){
        return pacientes.remove(id);
    }
    /**
     * Modifica un paciente
     * @param id ID del paciente a modificar
     * @param dto los nuevos datos del paciente
     * @return paciente modificado
     */
    public PacienteDTO updatePaciente(Long id,PacienteDTO dto){
        boolean out = false;
        int i = 0;
        int index = 0;
        while(i<pacientes.size() && !out){
            if(pacientes.get(i).getId()==id){
                index = i;
                out = true;
            }
        }
        return pacientes.set(i, dto);
    }
    /**
     * Obtiene un paciente
     * @param id ID del paciente a obtener
     * @return el paciente deseada
     */
    public PacienteDTO getPaciente(Long id){
        for (int i=0; i<pacientes.size(); i++){
            if(pacientes.get(i).getId()==id){
                return pacientes.get(i);
            }
        }
        return null;
    }
    
}
