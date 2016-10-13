/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lara
 */

@Stateless
public class EspecializacionPersistence
{
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public EspecializacionEntity find(Long id)
    {
        return em.find(EspecializacionEntity.class, id);
    }
}
