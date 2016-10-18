/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lara
 */

@Stateless
public class EspecializacionPersistence
{
    private static final Logger LOGGER = Logger.getLogger(EspecializacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public EspecializacionEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando especializacion con id={0}", id);
        return em.find(EspecializacionEntity.class, id);
    }
    
    public EspecializacionEntity findByName(String name)
    {
        LOGGER.log(Level.INFO, "Consultando especializacion con nombre= ", name);
        TypedQuery <EspecializacionEntity> q = em.createQuery("select u from EspecializacionsEntity u where u.name = :name", EspecializacionEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }
    
    public List <EspecializacionEntity> findAll ()
    {
        LOGGER.info("Consultando todos las Especializaciones");
        Query q = em.createQuery("select u from EspecializacionEntity u");
        return q.getResultList();
    }
     
    public EspecializacionEntity create (EspecializacionEntity entity)
    {
        LOGGER.info("Creando una especializacion nuevo");
        em.persist(entity);
        LOGGER.info("Especializacion creado");
        return entity;
    }
    
    public EspecializacionEntity update (EspecializacionEntity entity)
    {
        LOGGER.log(Level.INFO, "Actualizando Especializacion con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando especializacion con id={0}", id);
        EspecializacionEntity entity = em.find(EspecializacionEntity.class, id);
        em.remove(entity);
    }
}
 