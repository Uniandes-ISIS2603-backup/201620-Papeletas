/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.especializacionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lara
 */

@Stateless
public class especializacionPersistence
{
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public especializacionEntity find(Long id)
    {
        return em.find(especializacionEntity.class, id);
    }
}
