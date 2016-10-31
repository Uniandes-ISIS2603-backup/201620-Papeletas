package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.Calendar;
import javax.persistence.NoResultException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jc.useche10
 */
@Stateless
public class CitaLogic implements ICitaLogic {
    
    @Inject
    private CitaPersistence persistence;
    
    @Inject
    private IMedicoLogic medicoLogic;

    /**
     * Obtiene la lista de los registros de Cita que pertenecen a un medico.
     *
     * @return Colección de objetos de CitaEntity.
     * 
     */
    @Override
    public List<CitaEntity> getCitas(Long medicoid) {
        MedicoEntity medico = medicoLogic.getMedico(medicoid);
        return medico.getCitas();
    }


    /**
     * Obtiene los datos de una instancia de Cita a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CitaEntity con los datos del Cita consultado.
     * 
     */
    @Override
    public CitaEntity getCita(Long id) {
        try{
        return persistence.find(id);
          } catch (NoResultException e) {
            throw new IllegalArgumentException("El Department no existe");
        }
    }

    /**
     * Obtiene los datos de una instancia de Cita a partir de su name.
     *
     * @param name nombre del empleado de la instancia a consultar
     * @return el primer empleado con ese nombre .
     * 
     */
    @Override
    public CitaEntity getCitaByName(Long medicoid,String name) {
        return persistence.findByName(medicoid,name);
    }
    /**
     * Se encarga de crear un Cita en la base de datos.
     *
     * @param entity Objeto de CitaEntity con los datos nuevos
     * @return Objeto de CitaEntity con los datos nuevos y su ID.
     * 
     */
    @Override
    public CitaEntity createCita(Long medicoid, CitaEntity entity) throws HospitalLogicException
    {
        MedicoEntity medico = medicoLogic.getMedico(medicoid);
        entity.setMedico(medico);
        return persistence.update(entity);
    }

    @Override
    public CitaEntity updateCita(Long medicoid, CitaEntity entity) {
        MedicoEntity medico = medicoLogic.getMedico(medicoid);
        entity.setMedico(medico);
        return persistence.update(entity);
    }
    /**
     * Elimina una instancia de Cita de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * 
     */
    @Override
    public void deleteCita(Long id){
        CitaEntity old = getCita(id);
        persistence.delete(old.getId());
    }
}
