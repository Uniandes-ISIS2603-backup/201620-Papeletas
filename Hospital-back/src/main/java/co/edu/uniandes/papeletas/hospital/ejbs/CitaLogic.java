package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.Calendar;
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
    
    @Inject private CitaPersistence persistence;


    /**
     * Obtiene la lista de los registros de Cita.
     *
     * @return Colección de objetos de CitaEntity.
     * 
     */
    @Override
    public List<CitaEntity> getCitas() {
        return persistence.findAll();
    }


    /**
     * Obtiene los datos de una instancia de Cita a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CitaEntity con los datos del Cita consultado.
     * 
     */
    public CitaEntity getCita(Long id) {
        return persistence.find(id);
    }

    /**
     * Obtiene los datos de una instancia de Cita a partir de su name.
     *
     * @param name nombre del empleado de la instancia a consultar
     * @return el primer empleado con ese nombre .
     * 
     */
    public CitaEntity getCitaByName(String name) {
        return persistence.findByName(name);
    }
    /**
     * Se encarga de crear un Cita en la base de datos.
     *
     * @param entity Objeto de CitaEntity con los datos nuevos
     * @return Objeto de CitaEntity con los datos nuevos y su ID.
     * 
     */
    @Override
    public CitaEntity createCita(CitaEntity entity) throws HospitalLogicException
    {
        CitaEntity exists = persistence.findByName(entity.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(entity.getFecha());
        //fecha acctual
        Calendar c2 = Calendar.getInstance();
        if (exists != null) 
        {
            throw new HospitalLogicException("Ya existe una cita con ese nombre");
        }
        
        else if(c1.before(c2)) 
        {
            throw new HospitalLogicException("La cita no puede exisitir en el pasado");
        }   
        else if(entity.getDuracion()<0)
        {
            throw new HospitalLogicException("La cita no puede exisitir en el pasado");
        }
        else
        {
            persistence.create(entity);
        }
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Cita.
     *
     * @param entity Instancia de CitaEntity con los nuevos datos.
     * @return Instancia de CitaEntity con los datos actualizados.
     * 
     */
    @Override
    public CitaEntity updateCita(CitaEntity entity) throws HospitalLogicException{
        CitaEntity exists = persistence.findByName(entity.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(entity.getFecha());
        //fecha acctual
        Calendar c2 = Calendar.getInstance();
        if (exists != null) 
        {
            throw new HospitalLogicException("Ya existe una cita con ese número");
        }
        
        else if(c1.before(c2)) 
        {
            throw new HospitalLogicException("La cita no puede exisitir en el pasado");
        }   
        else if(entity.getDuracion()<0)
        {
            throw new HospitalLogicException("La cita no puede exisitir en el pasado");
        }
        else
        {
            return persistence.update(entity);
        } 
    }

    /**
     * Elimina una instancia de Cita de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * 
     */
    @Override
    public void deleteCita(Long id) {
        persistence.delete(id);
    }
}
