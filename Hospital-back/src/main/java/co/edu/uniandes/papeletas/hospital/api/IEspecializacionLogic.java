/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.api;

import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/**
 *
 * @author jc.lara10
 */
public interface IEspecializacionLogic
{
    public List<EspecializacionEntity> getEspecializaciones();
    public EspecializacionEntity getEspecializacion(Long id);
    public EspecializacionEntity getEspecializacionByName(String name);
    public EspecializacionEntity createEspecializacion(EspecializacionEntity entity) throws HospitalLogicException; 
    public EspecializacionEntity updateEspecializacion(EspecializacionEntity entity);
    public void deleteEspecializacion(Long id);
}
