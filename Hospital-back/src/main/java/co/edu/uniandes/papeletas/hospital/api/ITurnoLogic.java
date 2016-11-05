/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.api;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/**
 *
 * @author ac.cabezas716
 */
public interface ITurnoLogic {
    
    public List<TurnoEntity> getTurnos(Long pMedicoId);
    
    public TurnoEntity getTurno(Long pId);
    
    public TurnoEntity getTurnoByName(Long pMedicoId, String pName);
    
    public TurnoEntity createTurno(Long pMedicoId, TurnoEntity pEntityTurno) throws HospitalLogicException; 
    
    public TurnoEntity updateTurno(Long pMedicoId, TurnoEntity pEntityTurno);
    
    public void deleteTurno(Long pId);
    
    public ConsultorioEntity getConsultorioTurno(Long pId);
}
