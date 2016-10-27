/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.api.IConsultorioLogic;
import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.ConsultorioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jf.mendez11
 */
@Stateless
public class ConsultorioLogic implements IConsultorioLogic {
    
    @Inject
    private ConsultorioPersistence persistence; 
    
    @Override
    public List<ConsultorioEntity> getConsultorios() {
        return persistence.findAll();
    }

    @Override
    public ConsultorioEntity getConsultorio(Long id) {
        return persistence.find(id);
    }

    @Override
    public ConsultorioEntity getConsultorioByNumber(Integer number) {
        return persistence.findByNumber(number);
    }

    @Override
    public ConsultorioEntity createConsultorio(ConsultorioEntity entity) throws HospitalLogicException {
        ConsultorioEntity exists = persistence.findByNumber(entity.getNumero());
        if (exists != null) {
            throw new HospitalLogicException("Ya existe un consultorio con ese número");
        }
        else if (getNumberOfConsultorios() >= ConsultorioEntity.MAX_NUM_CONSULTORIOS) {
            throw new HospitalLogicException("Ya existe el máximo número de consultorios");
        }
        else {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public ConsultorioEntity updateConsultorio(ConsultorioEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteConsultorio(Long id) {
        persistence.delete(id);
    }

    @Override
    public Integer getNumberOfConsultorios() {
        return persistence.findAll().size();
    }
    
}
