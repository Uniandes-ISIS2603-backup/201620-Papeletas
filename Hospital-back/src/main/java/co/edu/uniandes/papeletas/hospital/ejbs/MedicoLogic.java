/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import co.edu.uniandes.papeletas.hospital.persistence.MedicoPersistence;
import co.edu.uniandes.papeletas.hospital.persistence.TurnoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas
 */
@Stateless
public class MedicoLogic implements IMedicoLogic
{

    @Inject 
    private MedicoPersistence persistence;
    
    @Inject
    private CitaPersistence persistenceCita;
    
    @Inject
    private TurnoPersistence persistenceTurno;
    
    @Override
    public List<MedicoEntity> getMedicos()
    {
        return persistence.findAll(); 
    }

    @Override
    public MedicoEntity getMedico(Long id)
    {
        return persistence.find(id);
    }

    @Override
    public MedicoEntity getMedicoByName(String name) 
    {
        return persistence.findByName(name);
    }

    @Override
    public MedicoEntity createMedico(MedicoEntity entity) throws HospitalLogicException
    {
        if(getNumberOfMedicos()>=MedicoEntity.MAX_MED)
        {
            throw new HospitalLogicException("Ya se llego al maximo de medicos posibles ");
        }
         return persistence.create(entity);
    }

    @Override
    public MedicoEntity updateMedico(MedicoEntity entity) 
    {
      return persistence.update(entity);
    }

    @Override
    public void deleteMedico(Long id) 
    {
        persistence.delete(id);
    }

    @Override
    public Integer getNumberOfMedicos()
    {
        return persistence.findAll().size();
    }

    @Override
    public List<CitaEntity> getCitas() 
    {
      return persistenceCita.findAll();
        }

    @Override
    public List<TurnoEntity> getTurnos() {
        return persistenceTurno.findAll(); 
    }

    @Override
    public CitaEntity createCita(CitaEntity entity) 
    {
      return persistenceCita.create(entity);
    }

    @Override
    public void deleteCita(Long id)
    {
        persistenceCita.delete(id);
    }
    
}
