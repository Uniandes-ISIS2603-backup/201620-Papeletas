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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ac.cabezas716
 */
public class TurnoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PacientePersistence.class.getName());
    
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public TurnoEntity find (Long id){
        LOGGER.log(Level.INFO, "Consultando el turno con id={0}", id);
        return em.find(TurnoEntity.class, id);
    }
    
    public TurnoEntity findByName(String name){
         LOGGER.log(Level.INFO, "Consultando paciente con name = {0}", name);
        TypedQuery<TurnoEntity> q
                = em.createQuery("select u from PacienteEntity u where u.name = :name", TurnoEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }
    
    public List<TurnoEntity> findAll() {
        LOGGER.info("Consultando todos los turnos");
        Query q = em.createQuery("select u from TurnoEntity u");
        return q.getResultList();
    }

    public TurnoEntity create(TurnoEntity entity) {
        LOGGER.info("Creando un turno nuevo");
        em.persist(entity);
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
