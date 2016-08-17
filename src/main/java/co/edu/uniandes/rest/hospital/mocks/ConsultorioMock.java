/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.cities.exceptions.ConsultorioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jf.mendez11
 */
public class ConsultorioMock {
    
    /**
     * Logger de la clase
     */
    private final static Logger logger = Logger.getLogger(ConsultorioMock.class.getName());
    /**
     * Número de consultorios en el hospital
     */
    private static final int NUM_CONSULTORIOS = 23;
    
    /**
     * Lista de consultorios
     */
    private static ArrayList <ConsultorioDTO> consultorios;
    
    /**
     * Constructor de la clase
     */
    public ConsultorioMock ()
    {
        if (consultorios == null)
        {
            consultorios = new ArrayList<> ();
            long l = 1L;
            for (int i = 0; i < NUM_CONSULTORIOS; i++)
            {
                boolean libre = false;
                //Para probar la clase, los consultorios con id par están libres
                if (l % 2 == 0) libre = true;
               
                ConsultorioDTO consultorio = new ConsultorioDTO(l++, libre);
                
                consultorios.add(consultorio);
            }
        }
        logger.setLevel(Level.INFO);
        
        logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + consultorios );
        
    }
    
    /**
     * Obtiene la lista de consultorios
     * @return lista de consultorios
     * @throws ConsultorioException Si la lista no existe
     */
    public List<ConsultorioDTO> getConsultorios () throws ConsultorioException
    {
        if (consultorios == null)
        {
            throw new ConsultorioException("La lista de consultorios no existe.");
        }
        return consultorios;
    }
    
    /**
     * Crea un nuevo consultorio y lo agrea a la lista
     * @param newConsultorio consultorio que se quiere agregat
     * @return Consultorio nuevo
     * @throws ConsultorioException si ya existe un consultorio con el ese id
     */
    public ConsultorioDTO createConsultorio (ConsultorioDTO newConsultorio) throws ConsultorioException
    {
        if (newConsultorio.getId() != null)
        {
            for (ConsultorioDTO consultorio : consultorios)
            {
                if (Objects.equals(consultorio.getId(), newConsultorio.getId()))
                {
                    throw new ConsultorioException("Ya existe un consultorio con ese id o número.");
                }
            }
        }
        else
        {

            long newId = 1;
            for (ConsultorioDTO consultorio : consultorios)
            {
                if (newId <= consultorio.getId())
                    newId = consultorio.getId() + 1;
            }
            newConsultorio.setId(newId);
        }
        
        if (newConsultorio.getDisponibilidad() == null)
        {
            boolean [] disponibilidad = new boolean [ConsultorioDTO.NUM_CUARTOS_DE_HORA_DIA];
            
            for (int i = 0; i < ConsultorioDTO.NUM_CUARTOS_DE_HORA_DIA; i++)
                disponibilidad[i]= false;
            
            newConsultorio.setDisponibilidad(disponibilidad);
        }
        
        consultorios.add(newConsultorio);
        return newConsultorio;
    }
    
    /**
     * Obtiene el consultorio con el id especificado por parametro
     * @param id id del consultorio que se quiere buscar
     * @return el consultorio con el id que entra por parametro
     * @throws ConsultorioException si no existe un consultorio con el id especificado o no existe la lista
     */
    public ConsultorioDTO getConsultorioId (Long id) throws ConsultorioException
    {
        if (consultorios == null)
        {
            throw new ConsultorioException("No existe una lista de consultorios.");
        }
        ConsultorioDTO consul = null;
        for (ConsultorioDTO consultorio : consultorios)
        {
            if (Objects.equals(consultorio.getId(), id))
                consul = consultorio;
        }
        if (consul == null)
        {
            throw new ConsultorioException("No existe un consultorio con ese id.");
        }
        return consul;
    }
    
    /**
     * Borra el consultorio con el id especificado
     * @param id ide del consultorio que se quiere borrar.
     * @throws ConsultorioException si no existe un consultorio con el id especificado
     */
    public void deleteConsultorio (Long id) throws ConsultorioException
    {
        boolean exists = false;
        for (ConsultorioDTO consultorio : consultorios)
        {
            if (Objects.equals(consultorio.getId(), id))
            {
                consultorios.remove(consultorio);
                exists = true;
            }
        }
        if (!exists)
        {
            throw new ConsultorioException("No existe un consultorio con el id especificado");
        }
    }
    
    /**
     * Actualiza el consultorio con el id especificado
     * @param id id del consultorio que se quiere actualizar
     * @param i índice de la hora que se quiere actualizar
     * @param consultorio consultorio actualizado
     * @return el consultorio actualizado
     * @throws ConsultorioException Si la información del consultorio está incompleta, el consultorio con el id no existe o se ingresa un índice fuera del rango
     */
    public ConsultorioDTO updateConsultorio (Long id, Integer i, ConsultorioDTO consultorio) throws ConsultorioException
    {
        if (consultorio.getId() == null)
        {
            throw new ConsultorioException("No se tiene el id actualizado del consultorio");
        }
        
        boolean exists = false;
        for (ConsultorioDTO consul : consultorios)
        {
            if (Objects.equals(consul.getId(), id))
            {
                exists = true;
                consul.setId(consultorio.getId());
                consul.setLibre(consultorio.isLibre());
                if (i != null)
                {
                    if (i < 0 || i > 95)
                        throw new ConsultorioException ("Debe ingresar un número entre 0 y 95");
                    consul.changeDisponibilidad(i);
                }
            }
        }
        if (!exists)
        {
            throw new ConsultorioException ("No existe un consultorio con ese id.");
        }
        return consultorio;
    }
}
