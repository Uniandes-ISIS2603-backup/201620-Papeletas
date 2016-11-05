/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
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
 * @author ac.cabezas716
 */
@Stateless
public class TurnoPersistence {
    private static final Logger LOGGER = Logger.getLogger(TurnoPersistence.class.getName());
    
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public TurnoEntity find (Long id){
        LOGGER.log(Level.INFO, "Consultando turno con id={0}", id);
        return em.find(TurnoEntity.class, id);
    }
    
    public TurnoEntity findByName(Long medicoId, String name){
        LOGGER.log(Level.INFO, "Consultando  turno con name= {0} ", name);
        TypedQuery<TurnoEntity> q = em.createQuery("select u from TurnoEntity u where u.medico.id = :medicoId and u.name = :name", TurnoEntity.class);
        q = q.setParameter("medicoId", medicoId);
        q = q.setParameter("name", name);
        List<TurnoEntity> turnosSimilarName = q.getResultList();
        if (turnosSimilarName.isEmpty() ) {
            return null; 
        } else {
            return turnosSimilarName.get(0);
        }
    }
    
    public List<TurnoEntity> findAll() {
        LOGGER.info("Consultando todos los turnos");
        Query q = em.createQuery("select u from TurnoEntity u");
        return q.getResultList();
    }
    
    public List<TurnoEntity> findAllInMedico(Long medicoId) {
    LOGGER.log(Level.INFO, "Consultando todos los turnos del medico id={0}", medicoId);
    TypedQuery q = em.createQuery("select d from TurnoEntity d  where d.medico.id = :medicoId", TurnoEntity.class);
    q = q.setParameter("medicoId", medicoId);
    return q.getResultList();
    }
    
    public TurnoEntity create(TurnoEntity entity) {
        System.out.println("Creando un turno nuevo");
        LOGGER.info("Creando un turno nuevo");
        em.persist(entity);
        System.out.println("Turno creado");
        LOGGER.info("Turno creado");
        return entity;
    }

    public TurnoEntity update(TurnoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando turno con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando turno con id={0}", id);
        TurnoEntity entity = em.find(TurnoEntity.class, id);
        em.remove(entity);
    }
    
}
