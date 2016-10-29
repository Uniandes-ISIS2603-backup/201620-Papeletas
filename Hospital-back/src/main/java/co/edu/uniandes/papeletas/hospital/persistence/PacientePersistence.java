/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
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
 * @author df.castro12
 */
@Stateless
public class PacientePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PacientePersistence.class.getName());
    
    @PersistenceContext(unitName = "PapeletasPU")
    protected EntityManager em;
    
    public PacienteEntity find (Long id){
        LOGGER.log(Level.INFO, "Consultando paciente con id={0}", id);
        return em.find(PacienteEntity.class, id);
    }
    
    public PacienteEntity findByName(String name){
         LOGGER.log(Level.INFO, "Consultando paciente con name = {0}", name);
        TypedQuery<PacienteEntity> q
                = em.createQuery("select u from PacienteEntity u where u.name = :name", PacienteEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }
    
    public PacienteEntity findByIdentificacionCivil(Long idcl){
        LOGGER.log(Level.INFO, "Consultando paciente con idcl = {0}", idcl);
        TypedQuery<PacienteEntity> q
                = em.createQuery("select u from PacienteEntity u where u.identificacionCivil = :identificacionCivil", PacienteEntity.class);
        q = q.setParameter("identificacionCivil", idcl); 
        
        List<PacienteEntity> pacientesSimilar = q.getResultList();
        if (pacientesSimilar.isEmpty() ) {
            return null; 
        } else {
            return pacientesSimilar.get(0);
        }
    }
    
    public List<PacienteEntity> findAll() {
        LOGGER.info("Consultando todos los pacientes");
        Query q = em.createQuery("select u from PacienteEntity u");
        return q.getResultList();
    }

    public PacienteEntity create(PacienteEntity entity) {
        LOGGER.info("Creando un paciente nuevo");
        em.persist(entity);
        LOGGER.info("Paciente creado");
        return entity;
    }

    public PacienteEntity update(PacienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando paciente con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando paciente con id={0}", id);
        PacienteEntity entity = em.find(PacienteEntity.class, id);
        em.remove(entity);
    }
        
}
