/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.CitaDTO;
import co.edu.uniandes.rest.hospital.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.dtos.PacienteDTO;
import co.edu.uniandes.rest.hospital.exceptions.CitaException;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private final static int NUM_MED = 103;

    /**
     * Arraylist con los medicos
     */
    private static ArrayList<MedicoDTO> medicos;
    
    
    private CitaMock cita;


    public MedicoMock()
    {
        if (medicos == null)
        {
            cita = new CitaMock();
            medicos = new ArrayList<>();

            medicos.add(new MedicoDTO("Nicolas Simmonds", 1L  , new EspecializacionDTO(1, "Cardiologia")));
            medicos.add(new MedicoDTO("Juan Mendez", 2L, new EspecializacionDTO(1,"Cardiologia")));
            medicos.add(new MedicoDTO("Diego Castro", 3L, new EspecializacionDTO(2, "Endocrinologia")));
            medicos.add(new MedicoDTO("Juan Useche", 4L, new EspecializacionDTO(3, "Neumologia")));
            medicos.add(new MedicoDTO("Juan Lara", 5L, new EspecializacionDTO(4, "Neurologia")));
            for(int i=0;i<medicos.size();i++)
            {
                medicos.get(i).setPromedio(0.0);
            }
            
            long l = 1L;
            for (int i = 0; i < medicos.size(); i++) { 
                CitaDTO cit = new CitaDTO(l, Calendar.getInstance().getTime(), medicos.get(i), l);
                cit.setConsultorio(new ConsultorioDTO(l, l));
                cit.setPaciente(new PacienteDTO(l, "nombre" + i, "apellido" + i, (int)((Math.random() + 1)*10) + i, i));
                medicos.get(i).agregarCitaListaEspera(cit);
                l++;
            }
           
        }
        
        logger.info("Inicializa la lista de medicos");
        logger.info("medicos" + medicos);
    }

    /**
     * Lista de los medicos
     *
     * @return lista de medicos
     * @throws MedicoException Si la lista no existe
     */
    public List<MedicoDTO> getMedico() throws MedicoException {
        if (medicos == null) {
            logger.severe("Error interno: lista de medicos no existe.");
            throw new MedicoException("la lista de los medicos esta vacia");
        }
        logger.info("Lista de medicos");
        return medicos;
    }

    /**
     * Crea un nuevo medico
     * @param medico medico a crear
     * @return medico nuevo
     * @throws MedicoException si ya hay un medico con el id dado 
     */
    public MedicoDTO createMedico(MedicoDTO medico) throws MedicoException {
        logger.info("recibiendo solicitud de agrega r medico " + medico);

        if (medico.getId() != null) {
            for (MedicoDTO medic : medicos) {
                if ((medic.getId()==medico.getId())) {
                    throw new MedicoException("Ya existe un medico con ese id");
                }
            }
        } else {
            Long nid = 1L;
            for (MedicoDTO medic : medicos) {
                if (nid <= medic.getId()) {
                    nid = medic.getId() + 1;
                }
            }
            medico.setId(nid);
        }
        medicos.add(medico);
        return medico;
    }

    /**
     * Retorna un medico dado su id
     * @param id id de; medico          
     * @return medico con el id dado
     * @throws MedicoException  si no existe un medico con el id dado
     */
    public MedicoDTO getMedID(Long id) throws MedicoException {
        MedicoDTO med = null;
        if (medicos == null) {
            throw new MedicoException("La lista de medicos esta vacia");
        } else {
            for (MedicoDTO medic : medicos) {
                if (medic.getId().equals(id)) {
                    med = medic;
                }
            }
            if (med == null) {
                throw new MedicoException("No hay medicos con el id dado");
            }
        }
        return med;
    }

    /**
     * Borra un medico de la lista
     * @param id id del medico
     * @throws MedicoException si el medico no existe  
     */
    public void deleteMedico(Long id) throws MedicoException {
        if (medicos == null) {
            throw new MedicoException("La lista de medicos esta vacia");
        } else {
            boolean ya = false;
            for (MedicoDTO medic : medicos) {
                if (medic.getId().equals(id)) {
                    medicos.remove(medic);
                    ya = true;
                }
            }
            if (!ya) {
                throw new MedicoException("El medico que desea eliminar no existe");
            }
        }
    }

    /**
     * Actualiza un medico con los datos dados por parametro
     * @param medicoN medico con los datos nuevos
     * @return medico actualizado
     * @throws MedicoException si no existe un medico con el id dado para actualizar
     */
    public MedicoDTO updateMedico(MedicoDTO medicoN) throws MedicoException {
        if (medicos == null) {
            throw new MedicoException("La lista de medicso esta vacia");
        } else {
            boolean ya = false;
            for (MedicoDTO medic : medicos) {
                if (Objects.equals(medic.getId(), medicoN.getId())) {
                    medic.setEspecialidad(medicoN.getEspecializacion());
                    medic.setNombre(medicoN.getNombre());
                    ya = true;

                }
            }
            if (!ya) {
                throw new MedicoException("No existe el medico con el id dado para actualizar");
            }
        }
        return medicoN;
    }
    
    
    
    /**
     * Calcula el promedio de duracion de citas dado un medico
     * @param id del medico
     * @return 
     */
      public double calcularPromedioCitaMedico(Long id)
    {
        double promedio=0;
        for(int i=0;i<medicos.size();i++)
        {
            if(medicos.get(i).getId().equals(id))
            {
               
               
            }
        }
        return promedio;
    
    }
 
   /**
    * retorna la lista de medicos de una especializacion
    */
   
   public List listaPorSpec(String spec)      
   {
       ArrayList temp = new ArrayList();
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getEspecializacion().getNombre().equals(spec))
           {
               temp.add(medicos.get(i));
           }
       }
       return temp;
   }
     
   public List <CitaDTO> getListaEsperaMedico (Long id) throws MedicoException {
       List<CitaDTO> listaEspera = null;
       for (MedicoDTO medico : medicos) {
           if (medico.getId().equals(id)) {
               listaEspera = medico.getListaEspera();
           }
       }
       if (listaEspera == null) throw new MedicoException ("No existe un médico con ese ID");
       return listaEspera;
   }
   
   public CitaDTO agregarCitaListaEspera (Long idMedico, CitaDTO cita) throws MedicoException {
       if (cita.getId() != 0) {
           for (CitaDTO cit : getListaEsperaMedico(idMedico)) {
               if (Objects.equals(cit.getId(), cita.getId())) {
                   throw new MedicoException("Ya existe una cita con ese id en la lista de espera");
               }
           }
       }
       else {
           long newId = 1L;
           for (CitaDTO cit : getListaEsperaMedico(idMedico)) {
               if (newId <= cit.getId()) newId = cit.getId() + 1;
           }
           cita.setId(newId);
       }
       getMedID(idMedico).agregarCitaListaEspera(cita);
       return cita;
   }
   
   public void deleteCitaListaEspera (Long idMedico, long idCita) throws MedicoException {
       getMedID(idMedico).deleteCitaListaEspera(idCita);
   }
}
