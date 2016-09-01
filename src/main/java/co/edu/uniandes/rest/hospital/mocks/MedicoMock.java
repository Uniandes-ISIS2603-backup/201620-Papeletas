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
    private final static int NUM_MED = 103;

    /**
     * Arraylist con los medicos
     */
    private static ArrayList<MedicoDTO> medicos;


    public MedicoMock() {
        if (medicos == null) {
            medicos = new ArrayList<>();
            medicos.add(new MedicoDTO("Nicolas Simmonds", 1L    , "02/04-15/04", "Ortopedista"));
            medicos.add(new MedicoDTO("Juan Mendez", 2L, "15/04-25/04", "Cardiologo"));
            medicos.add(new MedicoDTO("Diego Castro", 3L, "01/04-30/04", "Ginecologo"));
            medicos.add(new MedicoDTO("Juan Useche", 4L, "08/04-11/04", "Otorrino"));
            medicos.add(new MedicoDTO("Juan Lara", 5L, "20/04-29/04", "Oftalmologo"));
        }
        logger.setLevel(Level.INFO);
        logger.info("Inicializa la lista de ciudades");
        logger.info("ciudades" + medicos);
    }

    /**
     * Lista de los medicos
     *
     * @return lista de consultorios
     * @throws ConsultorioException Si la lista no existe
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
    public MedicoDTO getMedID(long id) throws MedicoException {
        MedicoDTO med = null;
        if (medicos == null) {
            throw new MedicoException("La lista de medicos esta vacia");
        } else {
            for (MedicoDTO medic : medicos) {
                if (Objects.equals(medic.getId(), id)) {
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
                    medic.setDisponibilidad(medicoN.getDisponibilidad());
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
}
