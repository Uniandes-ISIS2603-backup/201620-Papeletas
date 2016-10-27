/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
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
 * @author jf.mendez11
 */
@Stateless
public class ConsultorioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ConsultorioPersistence.class.getName());
    
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public ConsultorioEntity find (Long id) {
        LOGGER.log(Level.INFO, "Consultando consultorio con id={0}", id);
        return em.find(ConsultorioEntity.class, id);
    }
    
    public ConsultorioEntity findByNumber (Integer numero) {
        LOGGER.log(Level.INFO, "Consultando consultorio con numero= ", numero);
        TypedQuery <ConsultorioEntity> q = em.createQuery("select u from ConsultorioEntity u where u.numero = :numero", ConsultorioEntity.class);
        q = q.setParameter("numero", numero);
        List<ConsultorioEntity> consultorios = q.getResultList();
        
        if (consultorios.isEmpty()) {
            return null;
        }
        else {
            return consultorios.get(0);
        }
    }
    
    public List <ConsultorioEntity> findAll () {
        LOGGER.info("Consultando todos los  consultorios");
        Query q = em.createQuery("select u from ConsultorioEntity u");
        return q.getResultList();
    }
    
    public ConsultorioEntity create (ConsultorioEntity entity) {
        LOGGER.info("Creando un consultorio nuevo");
        em.persist(entity);
        LOGGER.info("Consultorio creado");
        return entity;
    }
    
    public ConsultorioEntity update (ConsultorioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando consultorio con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando  cita con id={0}", id);
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, id);
        em.remove(entity);
    }
}
