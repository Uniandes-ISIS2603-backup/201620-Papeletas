/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
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
 * @author jc.useche10
 */
@Stateless
public class CitaPersistence {
     private static final Logger LOGGER = Logger.getLogger(CitaPersistence.class.getName());

    @PersistenceContext(unitName = "PapeletasPU")
     protected EntityManager em;
    
        public CitaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando  cita con id={0}", id);
        return em.find(CitaEntity.class, id);
    }

    public CitaEntity findByName(Long medicoId, String name) {        
        LOGGER.log(Level.INFO, "Consultando  cita con name= {0} ", name);
        TypedQuery<CitaEntity> q = em.createQuery("select u from CitaEntity u where u.medico.id = :medicoId and u.name = :name", CitaEntity.class);
        q = q.setParameter("medicoId", medicoId);
        q = q.setParameter("name", name);
        List<CitaEntity> citasSimilarName = q.getResultList();
        if (citasSimilarName.isEmpty() ) {
            return null; 
        } else {
            return citasSimilarName.get(0);
        }
    }

    public List<CitaEntity> findAll() {
        LOGGER.info("Consultando todos los  citas");
        Query q = em.createQuery("select u from CitaEntity u");
        return q.getResultList();
    }

    public CitaEntity create(CitaEntity entity) {
        LOGGER.info("Creando un  cita nuevo");
        em.persist(entity);
        LOGGER.info("Cita creado");
        return entity;
    }

    public CitaEntity update(CitaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando  cita con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando  cita con id={0}", id);
        CitaEntity entity = em.find(CitaEntity.class, id);
        em.remove(entity);
    }
}
