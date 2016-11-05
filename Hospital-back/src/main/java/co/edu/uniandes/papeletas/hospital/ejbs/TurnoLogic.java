/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.ejbs;

import co.edu.uniandes.papeletas.hospital.api.IConsultorioLogic;
import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.api.ITurnoLogic;
import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.TurnoPersistence;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ac.cabezas716
 */
@Stateless
public class TurnoLogic implements ITurnoLogic {
    
    @Inject
    private TurnoPersistence persistence;
    
    @Inject
    private IMedicoLogic medicoLogic;

    @Inject
    private IConsultorioLogic consultorioLogic;

    @Override
    public List<TurnoEntity> getTurnos(Long pMedicoId) {
        MedicoEntity medico = medicoLogic.getMedico(pMedicoId);
        return medico.getTurnos();
    }

    @Override
    public TurnoEntity getTurno(Long pId) {
        try {
            return persistence.find(pId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El turno no existe");
        }
    }

    @Override
    public TurnoEntity getTurnoByName(Long pMedicoId, String pName) {
        return persistence.findByName(pMedicoId, pName);
    }

    @Override
    public TurnoEntity createTurno(Long medicoid, TurnoEntity entity) throws HospitalLogicException
    {
        System.out.println("Verificando si existe otro turno");
        TurnoEntity alreadyExist = getTurnoByName(medicoid, entity.getName());
        System.out.println("Verificado si existe otro turno con el mismo nombre ");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(entity.getFecha());
        //fecha actual
        Calendar c2 = Calendar.getInstance();
        if (alreadyExist != null) 
        {
            throw new HospitalLogicException("Ya existe un turno con ese nombre en la misma hospital");
        } 
        if(c1.before(c2))
        {
            throw new HospitalLogicException("El turno no puede ser en el pasado");
        }
        if(entity.getDuracion()<0)
        {
            throw new HospitalLogicException("La duracion no puede ser menor a 0 ");
        }
       
            MedicoEntity  medico = medicoLogic.getMedico(medicoid);
            entity.setMedico(medico);

            entity = persistence.create(entity);
        
        return entity;
    }

    @Override
    public TurnoEntity updateTurno(Long pMedicoId, TurnoEntity pEntityTurno) {
        MedicoEntity medico = medicoLogic.getMedico(pMedicoId);
        pEntityTurno.setMedico(medico);
        return persistence.update(pEntityTurno);
    }

    @Override
    public void deleteTurno(Long pId) {
        TurnoEntity old = getTurno(pId);
        persistence.delete(old.getId());
    }

    @Override
    public ConsultorioEntity getConsultorioTurno(Long pId) {
        return persistence.find(pId).getConsultorio();  
    }
    
}
