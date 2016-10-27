/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.ejbs;

import co.edu.uniandes.papeletas.hospital.api.IEspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import java.util.List;

/**
 *
 * @author jc.lara10
 */
public class EspecializacionLogic implements IEspecializacionLogic
{

    @Override
    public List<EspecializacionEntity> getEspecializaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EspecializacionEntity getEspecializacion(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EspecializacionEntity getEspecializacionByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EspecializacionEntity createEspecializacion(EspecializacionEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EspecializacionEntity updateEspecializacion(EspecializacionEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEEspecializacion(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}