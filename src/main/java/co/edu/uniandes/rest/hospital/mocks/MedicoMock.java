/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;

import java.util.ArrayList;
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


    public MedicoMock() {
        if (medicos == null) {
            medicos = new ArrayList<>();
<<<<<<< HEAD
            medicos.add(new MedicoDTO("Nicolas Simmonds", 1L    , "02/04-15/04", new EspecializacionDTO(1, "Cardiologia")));
            medicos.add(new MedicoDTO("Juan Mendez", 2L, "15/04-25/04", new EspecializacionDTO(1,"Cardiologia")));
            medicos.add(new MedicoDTO("Diego Castro", 3L, "01/04-30/04", new EspecializacionDTO(2, "Endocrinologia")));
            medicos.add(new MedicoDTO("Juan Useche", 4L, "08/04-11/04", new EspecializacionDTO(3, "Neumologia")));
            medicos.add(new MedicoDTO("Juan Lara", 5L, "20/04-29/04", new EspecializacionDTO(4, "Neurologia")));
=======
            medicos.add(new MedicoDTO("Nicolas Simmonds", 1L    , "Ortopedista"));
            medicos.add(new MedicoDTO("Juan Mendez", 2L, "Cardiologo"));
            medicos.add(new MedicoDTO("Diego Castro", 3L,  "Ginecologo"));
            medicos.add(new MedicoDTO("Juan Useche", 4L,"Otorrino"));
            medicos.add(new MedicoDTO("Juan Lara", 5L,  "Oftalmologo"));
>>>>>>> origin/master
            
      
            for(int i=0;i<medicos.size();i++)
            {
                medicos.get(i).setDuracionConsulta(i+13);
                medicos.get(i).setCantidadCitas(i+1);
            }
        }
        logger.setLevel(Level.INFO);
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
               promedio=medicos.get(i).calcularPromedioCitaMedico();
               break;
            }
        }
        return promedio;
    
    }
    
    
      /**
       * Calcula el promedio de citas dada una especialidad;
       * @param pEspecialidad
       * @return 
       */
   public double calcularPromedioEspecialidad(String pEspecialidad)
   {
       double promedio=0;
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getEspecializacion().equals(pEspecialidad)) 
            {
                promedio+=medicos.get(i).calcularPromedioCitaMedico();
            }          
       }
       return promedio;
       
   }
  
   
   /**
    * Registra el fin de una consulta medica de un medico con el id dado y el tuirno dado
    * @param idMedico id del medico     
    * @param idTurno id del turno
    * @param idCita id de la cita   
    * @param pDuracion duracion de la consulta
    */
   public void registrarFinCita(Long idMedico, Long idTurno, Long idCita, int pDuracion)
   {
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getId()==idMedico)
           {
               medicos.get(i).registrarFinCita(pDuracion, idCita, idTurno);
               break;
           }
       }
   }
   
   /**
    * Crea un turno en el médco con cierta id
    * @param pIdMedico Id del médico
    * @param pFecha Fecha del medico
    * @param pDuracion Duracion del turno en minutos
    *//*
   public void crearTurno(Long pIdMedico, Date pFecha, int pDuracion) throws MedicoException{
       for(MedicoDTO actual: medicos){
           if(actual.getId().equals(pIdMedico)){
               actual.agregarTurno(pFecha, pDuracion);
               return;
           }
       }
       throw new MedicoException("No existe un médico con id " + pIdMedico);
   }
   
   /**
    * Asigna un consultorio a un turno con cierta id de un medico con cierta id
    * @param pIdMedico Id del médico
    * @param pIdTurno Id del turno
    * @param pConsultorio Consultorio a agregar
    * @throws TurnoLogicException
    * @throws MedicoException 
    */
   public void asignarConsultorio(Long pIdMedico, Long pIdTurno, ConsultorioDTO pConsultorio) throws TurnoLogicException, MedicoException{
       for(MedicoDTO actual: medicos){
           if(actual.getId().equals(pIdMedico)){
               actual.asignarConsultorioTurno(pIdTurno, pConsultorio);
               return;
           }
       }
       throw new MedicoException("No existe un médico con id " + pIdMedico);
   }
   
   public List listaPorSpec(Long IdSpec)      
   {
       ArrayList temp = new ArrayList();
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getEspecializacion().getId()==IdSpec)
           {
               temp.add(medicos.get(i));
           }
       }
       return temp;
   }
   
   
  
   
   
}
