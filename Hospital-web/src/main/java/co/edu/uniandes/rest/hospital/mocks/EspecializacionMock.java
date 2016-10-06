package co.edu.uniandes.rest.hospital.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import co.edu.uniandes.rest.hospital.exceptions.EspecializacionException;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import java.util.logging.Level;
/**
 * 
 * @author Juan Camilo Lara
 *
 */

public class EspecializacionMock
{
	private final static Logger logger = Logger.getLogger(EspecializacionMock.class.getName());
	
	/**
	 * Lista de esepcialidades en el hospital
	 */	
	private static ArrayList<EspecializacionDTO> especialidades;
        
         public EspecializacionMock()
        {
            if (especialidades == null)
            {
            especialidades = new ArrayList<>();
            especialidades.add(new EspecializacionDTO(1, "Cardiologia"));
            especialidades.add(new EspecializacionDTO(2 , "Endocrinologia"));
            especialidades.add(new EspecializacionDTO(3 , "Neumologia"));
            especialidades.add(new EspecializacionDTO(4 , "Neurologia"));
            especialidades.add(new EspecializacionDTO(5 , "Patologia"));
            especialidades.add(new EspecializacionDTO(6 , "Hematologia"));
            especialidades.add(new EspecializacionDTO(7 , "Oncologia"));
            especialidades.add(new EspecializacionDTO(8 , "Cirugia"));
            especialidades.add(new EspecializacionDTO(9 , "Pediatria"));
            especialidades.add(new EspecializacionDTO(10 , "OB/GYN"));
            especialidades.add(new EspecializacionDTO(11 , "Dermatologia"));
            especialidades.add(new EspecializacionDTO(12 , "Urologia"));
            especialidades.add(new EspecializacionDTO(13 , "Ortopedia y Traumatologia"));
            }
            
            logger.setLevel(Level.INFO);

             // muestra información 
            logger.info("Inicializa la lista de especializacion");
            logger.info("especializacion" + especialidades);
        }
	
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
            for (EspecializacionDTO spec : especialidades)
            {
                if (Objects.equals(spec.getNombre(), specialty.getNombre()))
                {
                    logger.severe("Ya existe una especializacion con ese nombre");
                    throw new EspecializacionException("Ya existe una especializacion con ese nombre");
                }
            }
            
            logger.info("Generando id para la nueva especializacion");
            int newId = 1;
            for (EspecializacionDTO spec : especialidades)
            {
                if (newId <= spec.getId()) {
                    newId = spec.getId() + 1;
                }
            }
            specialty.setId(newId);
    	
    	logger.info("agregando especialidad" + specialty);
        especialidades.add(specialty);
        return specialty;
    }
	
    public EspecializacionDTO getSpecID(int id)throws EspecializacionException
    {
        logger.info("recibiendo solicitud de especialidad con id " + id);
        for (EspecializacionDTO spec : especialidades)
        {
            if (Objects.equals(spec.getId(), id))
            {
                logger.info("retornando editorial " + spec);
                return spec;
            }
        }
        
        logger.severe("No existe especializacion con ese id");
        throw new EspecializacionException("No existe especializacion con ese id");
    }
        
    public void deleteEspecializacion (int id) throws EspecializacionException
    {
        logger.info("recibiendo solictud de eliminar especialidad con id " + id);

        for (EspecializacionDTO spec : especialidades)
        {
            if (Objects.equals(spec.getId(), id))
            {
                logger.info("eliminando epecializacion " + spec);
                especialidades.remove(spec);
                return;
            }
        }

        // no encontró el editorial con ese id ?
        logger.severe("No existe una especializacion con ese id");
        throw new EspecializacionException("No existe una especializacion con ese id");
    }
    public EspecializacionDTO updateEspecializacion (int id, EspecializacionDTO spec) throws EspecializacionException
    {
        
        logger.info("recibiendo solictud de modificar especializacion " + spec);

        // busca el editorial con el id suministrado
        for (EspecializacionDTO especial : especialidades)
        {
            if (Objects.equals(especial.getId(), id)) {

                // modifica el editorial
                especial.setId(spec.getId());
                especial.setNombre(spec.getNombre());

                // retorna el editorial modificada
                logger.info("Modificando especialidad " + especial);
                return especial;
            }
        }

        logger.severe("No existe una especialidad con ese id");
        throw new EspecializacionException("No existe una especialidad con ese id");
    }
}