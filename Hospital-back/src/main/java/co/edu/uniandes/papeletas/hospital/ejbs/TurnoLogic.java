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
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.TurnoPersistence;
import java.util.List;
import javax.inject.Inject;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TurnoEntity getTurno(Long pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TurnoEntity getTurnoByName(Long pMedicoId, String pName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TurnoEntity createTurno(Long pMedicoId, TurnoEntity pEntityTurno) throws HospitalLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TurnoEntity updateTurno(Long pMedicoId, TurnoEntity pEntityTurno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTurno(Long pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultorioEntity getConsultorioTurno(Long pId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
