/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.api.IEspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.EspecializacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.lara10
 */
@Stateless
public class EspecializacionLogic implements IEspecializacionLogic
{

    @Inject
    private EspecializacionPersistence persistence;
    
    @Override
    public List<EspecializacionEntity> getEspecializaciones()
    {
        return persistence.findAll();
    }

    @Override
    public EspecializacionEntity getEspecializacion(Long id)
    {
        return persistence.find(id);
    }

    @Override
    public EspecializacionEntity getEspecializacionByName(String name)
    {
        return persistence.findByName(name);
    }

    /**
     *
     * @param entity
     * @return
     * @throws HospitalLogicException
     */
    @Override
    public EspecializacionEntity createEspecializacion(EspecializacionEntity entity) throws HospitalLogicException
    { 
        EspecializacionEntity exists = persistence.find(entity.getId());
        if(exists != null)
        {
            throw new HospitalLogicException("Ya Existe una especializacion con ese nombre");
        }
        else
        {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public EspecializacionEntity updateEspecializacion(EspecializacionEntity entity)
    {
        persistence.update(entity);
        return entity;
    }
    
    @Override
    public void deleteEspecializacion(Long id)
    {
        persistence.delete(id);
    }
    
}