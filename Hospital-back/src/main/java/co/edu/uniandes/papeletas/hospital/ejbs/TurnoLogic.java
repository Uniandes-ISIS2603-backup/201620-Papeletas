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
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ac.cabezas716
 */
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
        return medico.turnos();
    }

    @Override
    public TurnoEntity getTurno(Long pId) {
        try{
        return persistence.find(pId);
          } catch (NoResultException e) {
            throw new IllegalArgumentException("El turno no existe");
        }
    }

    @Override
    public TurnoEntity getTurnoByName(String pName) {
        return persistence.findByName(pName);
    }

    @Override
    public TurnoEntity createTurno(Long pMedicoId, TurnoEntity pEntityTurno) throws HospitalLogicException {
        TurnoEntity alreadyExist = getTurnoByName(pEntityTurno.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(pEntityTurno.getFecha());
        //fecha actual
        Calendar c2 = Calendar.getInstance();
        if (alreadyExist != null) 
        {
            throw new HospitalLogicException("Ya existe un turno con el mismo nombre en el hospital");
        } 
        if(c1.before(c2))
        {
            throw new HospitalLogicException("El turno no puede tener una fecha pasada");
        }
        if(pEntityTurno.getDuracion()<0)
        {
            throw new HospitalLogicException("Ya duracion del turno no puede ser menor a 0 horas.");
        }
       
            MedicoEntity  medico = medicoLogic.getMedico(pMedicoId);
            pEntityTurno.setMedico(medico);

            pEntityTurno = persistence.create(pEntityTurno);
        
        return pEntityTurno;
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
