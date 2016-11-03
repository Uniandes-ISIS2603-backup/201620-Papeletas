/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.api;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public interface IMedicoLogic 
{
    public List <MedicoEntity> getMedicos();
    
    public List <CitaEntity> getCitas();
    
    public List <TurnoEntity> getTurnos();
    
    public MedicoEntity getMedico (Long id);
    
    public MedicoEntity getMedicoByName(String name);
        
    public MedicoEntity createMedico (MedicoEntity entity) throws HospitalLogicException;
    
    public MedicoEntity updateMedico (MedicoEntity entity);
    
    public void deleteMedico (Long id);
    
    public Integer getNumberOfMedicos ();
    
}
