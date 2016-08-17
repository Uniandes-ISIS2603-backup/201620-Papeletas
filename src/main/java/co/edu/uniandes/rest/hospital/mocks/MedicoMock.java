/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author n.simmonds10
 */
public class MedicoMock {
     /**
     * Logger de la clase
     */
    private final static Logger logger = Logger.getLogger(MedicoMock.class.getName());
    
    /**
     * Constante con el nunmero de medicos
     */
    
    private final static int NUM_MED=103;
    
    /**
     * Arraylisto con los medicos
     */
    private ArrayList<MedicoDTO> medicos;
    
    
    /**
     * String con la disponibiliidad de los medicos
     */
    private String disponibilidad;
    
    
    /**
     * Asociacion con la clase MEdicoDTO
     */
    
    private MedicoDTO medicoDTO;
    
    
    public MedicoMock ()
    {
        if(medicos==null)
        {
            medicos=new ArrayList();    
            long id=1L;
            
            //solo se van a creR 10 medicos para la prueba
            for(int i=0;i<10;i++)
            {
                medicoDTO = new MedicoDTO("Medico"+i, id, "02/0"+(i++)+"02/0"+(i+=2), "especialidad"+i);
                medicos.add(medicoDTO);
                id++;
            }
            
        }
         logger.setLevel(Level.INFO);
        
        logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + medicos );
    }
    
    
    
      
    /**
     * Lista de los medicos
     * @return lista de consultorios
     * @throws ConsultorioException Si la lista no existe
     */
    public List<MedicoDTO> getMedico () throws MedicoException
    {
        if (medicos == null)
        {
            throw new MedicoException("la lista de los medicos esta vacia");
        }
        return medicos;
    }
    
    
    public MedicoDTO createMedico(MedicoDTO medico) throws MedicoException
    {
        if(medico.getId()!=null)
        {
            for(MedicoDTO medic:medicos)
            {
                if(Objects.equals(medic.getId(),medico.getId()))
                    throw new MedicoException("Ya existe un medico con ese id");
            }
        }
        else
        {
            long nid = 1;
            for (MedicoDTO medic : medicos)
            {
                if (nid <= medico.getId())
                    nid = medico.getId() + 1;
            }
            medico.setId(nid);
            medicos.add(medico);
        }
        return medico;
    }
    
    public MedicoDTO getMedID(long id)throws MedicoException
    {
        MedicoDTO med=null;
        if(medicos==null)
            throw new MedicoException("La lista de medicos esta vacia");
        else
        {
            for(MedicoDTO medic:medicos)
            {
                if(Objects.equals(medic.getId(),id))
                        med=medic;        
            }
            if(med==null)
                throw new MedicoException("No hay medicso con el id dado");
        }
        return med;
    }
    
    public void deleteMedico(long id)throws MedicoException
    {
        if(medicos==null)
        {
            throw new MedicoException("la lista de medicos esta vacia");
        }
        else
        {
            for(MedicoDTO medic:medicos)
            {
                if(Objects.equals(medic.getId(), id))
                {
                    medicos.remove(medic);
                    return;
                }
            }
            throw new MedicoException("El medico que desea eliminar non existe");
        }
    }
    
    
    public MedicoDTO updateMedico(MedicoDTO medicoN)throws MedicoException
    {
        if(medicos==null)
        {
            throw new MedicoException("La lista de medicso esta vacia");
        }
        else
        {
            boolean ya=false;
            for(MedicoDTO medic:medicos)
            {
                if(Objects.equals(medic.getId(),medicoN.getId()))
                {
                    medic.setDisponibilidad(medicoN.getDisponibilidad());
                    medic.setEspecialidad(medicoN.getEspecializacion());
                    medic.setNombre(medicoN.getNombre());
                    ya=true;
                    
                }
            }
            if(!ya)
                throw new MedicoException("No existe el medico con el id dado para actualizar");
        }
        return medicoN;
    }
}
