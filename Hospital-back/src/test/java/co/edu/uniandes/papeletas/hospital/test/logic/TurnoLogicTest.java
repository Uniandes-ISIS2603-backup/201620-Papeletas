/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.api.ITurnoLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.TurnoLogic;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.TurnoPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ac.cabezas716
 */
@RunWith(Arquillian.class)
public class TurnoLogicTest {
    
    MedicoEntity fatherEntity;
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private ITurnoLogic turnoLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

    /**
     * 
     */
    private List<TurnoEntity> data = new ArrayList<TurnoEntity>();

    /**
    * 
    */
    private List<MedicoEntity> medicoData = new ArrayList<>();
    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TurnoEntity.class.getPackage())
                .addPackage(TurnoLogic.class.getPackage())
                .addPackage(ITurnoLogic.class.getPackage())
                .addPackage(TurnoPersistence.class.getPackage())
                .addPackage(MedicoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * 
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * 
     */
    private void clearData() {
        em.createQuery("delete from TurnoEntity").executeUpdate();
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        fatherEntity = factory.manufacturePojo(MedicoEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            TurnoEntity entity = factory.manufacturePojo(TurnoEntity.class);
            entity.setMedico(fatherEntity);
            Date hoy = new Date();
            Date mañana = new Date(hoy.getTime()+ 86400000 + i);
            entity.setFecha(mañana);
            em.persist(entity);
            data.add(entity);
        }
    }
   
    /**
     * Prueba para consultar la lista de Turnos
     *
     * 
     */
    @Test
    public void getTurnosTest() {
        List<TurnoEntity> list = turnoLogic.getTurnos(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (TurnoEntity entity : list) {
            boolean found = false;
            for (TurnoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Turno pot Id
     *
     * 
     */
    @Test
    public void getTurnoTest() {
        TurnoEntity entity = data.get(0);
        TurnoEntity resultEntity = turnoLogic.getTurno(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Turno
     *
     * 
     */
    @Test
    public void deleteTurnoTest() {
        TurnoEntity entity = data.get(1);
        turnoLogic.deleteTurno(entity.getId());
        TurnoEntity deleted = em.find(TurnoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Turno
     *
     * 
     */
    @Test
    public void updateTurnoTest() throws  HospitalLogicException{
        TurnoEntity entity = data.get(0);
        TurnoEntity newEntity = factory.manufacturePojo(TurnoEntity.class);
        Date hoy = new Date();
        Date mañana = new Date(hoy.getTime()+ 86400000);
        newEntity.setFecha(mañana);
        newEntity.setId(entity.getId());
        turnoLogic.updateTurno(fatherEntity.getId(),newEntity);
     
        TurnoEntity resp = em.find(TurnoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
