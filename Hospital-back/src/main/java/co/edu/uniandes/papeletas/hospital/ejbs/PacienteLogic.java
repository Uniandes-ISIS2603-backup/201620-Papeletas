/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.ejbs;


import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IPacienteLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import co.edu.uniandes.papeletas.hospital.persistence.PacientePersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.castro12
 */
@Stateless
public class PacienteLogic implements IPacienteLogic {
    
    @Inject
    private PacientePersistence persistence;
    
    @Inject
    private ICitaLogic citaLogic;
    
    /**
     * Obtiene la lista de los registros de Paciente.
     *
     * @return Colección de objetos de PacienteEntity.
     *
     */
    @Override
    public List<PacienteEntity> getPacientes() {
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Paciente a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PacienteEntity con los datos del Paciente consultado.
     * @throws co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException
     *
     */
    @Override
    public PacienteEntity getPaciente(Long id){
       PacienteEntity entity;
       entity = persistence.find(id);
       return entity;
    }
    
    /**
     * Obtiene los datos de una instancia de Paciente a partir de su Nombre
     * @param name Nombre de la instancia a consultar
     * @return Instancia de PacienteEntity con los datos del primer paciente con ese nombre.
     * @throws HospitalLogicException 
     */
    @Override
    public PacienteEntity getPacienteByName(String name)  {
        PacienteEntity entity;
        entity = persistence.findByName(name);
        return entity;
    }
    
    /**
     * Se encarga de crear un Paciente en la base de datos.
     *
     * @param paciente Objeto de PacienteEntity con los datos nuevos
     * @return Objeto de PacienteEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException
     *
     */
    @Override
    public PacienteEntity createPaciente(PacienteEntity paciente) throws HospitalLogicException {
        PacienteEntity alreadyExist = persistence.findByIdentificacionCivil(paciente.getIndentificacionCivil());
        if(alreadyExist != null){
            throw new HospitalLogicException("El paciente con identificación civil: "+paciente.getIndentificacionCivil()+" ya existe en el sistema");
        }
        else{
            persistence.create(paciente);
        }
        return paciente;
    }
    
    /**
     * Se encarga de actualizar un paciente en la base de datos
     * @param paciente Objeto de PacienteEntity con datos actualizados
     * @return Objeto de PacienteEntity con los datos nuevos y su ID
     */
    @Override
    public PacienteEntity updatePaciente(PacienteEntity paciente) {
       persistence.update(paciente);
        return paciente;
    }
    
    /**
     * Se encarga de eliminar un paciente con el ID dado
     * @param id el ID del objeto PacienteEntity a eliminar
     */
    @Override
    public void deletePaciente(Long id)  {
 
            persistence.delete(id);
       
    }
    
    /**
     * Se encarga de agregar una cita al paciente con ID dado
     * @param id ID del PacienteEntity al agregar la cita
     * @param idcita La cita para agregar a la lista de citas del Paciente
     * @throws HospitalLogicException 
     */
    @Override
    public CitaEntity addCita(Long id,CitaEntity cita) throws HospitalLogicException {
        PacienteEntity paciente = persistence.find(id);
        Date actual = new Date();
        if(cita==null){
            throw new HospitalLogicException("La cita no existe");
        }  
        else if(cita.getFecha().before(actual)){
            throw new HospitalLogicException("La cita que está tratando de agregar tiene una fecha anterior a su reserva");
        }
        else{
            cita.setPaciente(paciente);
            List<CitaEntity> citas = paciente.getCitas();
            citas.add(cita);
            paciente.setCitas(citas);
        }
        return cita;
    }
    /**
     * Se remuieve una cita
     * @param idPaciente
     * @param idCita 
     */
    @Override
    public void removeCita(Long idPaciente, Long idCita) {
        CitaEntity cita = citaLogic.getCita(idCita);
        cita.setPaciente(null);
    }

    @Override
    public List<CitaEntity> getCitas(Long id) {
        PacienteEntity paciente = persistence.find(id);
        return paciente.getCitas();
    }

    @Override
    public CitaEntity getCita(Long id, Long idCita) {
        List<CitaEntity> list = persistence.find(id).getCitas();
        CitaEntity citaEntity = new CitaEntity();
        citaEntity.setId(idCita);
        int index = list.indexOf(citaEntity);
        System.out.println(index);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
}
