/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.api;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/**
 *
 * @author jf.mendez11
 */
public interface IConsultorioLogic {
    
    public List <ConsultorioEntity> getConsultorios() throws HospitalLogicException;
    
    public ConsultorioEntity getConsultorio (Long id);
    
    public ConsultorioEntity getConsultorioByNumber (Integer number);
    
    public ConsultorioEntity createConsultorio (ConsultorioEntity entity) throws HospitalLogicException;
    
    public ConsultorioEntity updateConsultorio (ConsultorioEntity entity);
    
    public void deleteConsultorio (Long id);
    
}
