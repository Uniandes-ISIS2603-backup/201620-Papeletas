/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.ejbs.CitaLogic;
import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
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
 * @author jc.useche10
 */
@RunWith(Arquillian.class)
public class CitaLogicTest {
    
    MedicoEntity fatherEntity;
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private ICitaLogic citaLogic;

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
    private List<CitaEntity> data = new ArrayList<CitaEntity>();

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
                .addPackage(CitaEntity.class.getPackage())
                .addPackage(CitaLogic.class.getPackage())
                .addPackage(ICitaLogic.class.getPackage())
                .addPackage(CitaPersistence.class.getPackage())
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
        em.createQuery("delete from CitaEntity").executeUpdate();
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
            CitaEntity entity = factory.manufacturePojo(CitaEntity.class);
            entity.setMedico(fatherEntity);
            Date hoy = new Date();
            Date mañana = new Date(hoy.getTime()+ 86400000 + i);
            entity.setFecha(mañana);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Cita
     *
     * 
     */
    @Test
    public void createCitaTest1() throws HospitalLogicException {
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
         Date hoy = new Date();
         Date mañana = new Date(hoy.getTime()+ 86400000);
         newEntity.setFecha(mañana);
        newEntity.setDuracion(5);
        CitaEntity result = citaLogic.createCita(fatherEntity.getId(),newEntity);
        Assert.assertNotNull(result);
        CitaEntity entity = em.find(CitaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

     /**
     * Prueba para crear un Cita con un nombre que ya existe
     *
     * 
     */
    @Test(expected = HospitalLogicException.class)
    public void createCitaTest2() throws HospitalLogicException {
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
        newEntity.setName(data.get(0).getName());
        newEntity.setDuracion(5);
        CitaEntity result = citaLogic.createCita(fatherEntity.getId(),newEntity);
    }
    
     /**
     * Prueba para crear un Cita con unaduracion menor a 0
     *
     * 
     */
    @Test(expected = HospitalLogicException.class)
    public void createCitaTest3() throws HospitalLogicException {
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
        newEntity.setDuracion(-5);
        CitaEntity result = citaLogic.createCita(fatherEntity.getId(),newEntity);
    }
    
     /**
     * Prueba para crear una Fecha en el pasado
     *
     * 
     */
    @Test(expected = HospitalLogicException.class)
    public void createCitaTest4() throws HospitalLogicException {
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
        Date hoy = new Date();
        Date ayer = new Date( hoy.getTime()-86400000);
        newEntity.setFecha(ayer);
        newEntity.setDuracion(5);
        CitaEntity result = citaLogic.createCita(fatherEntity.getId(),newEntity);
    }
    /**
     * Prueba para consultar la lista de Citas
     *
     * 
     */
    @Test
    public void getCitasTest() {
        List<CitaEntity> list = citaLogic.getCitas(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (CitaEntity entity : list) {
            boolean found = false;
            for (CitaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Cita
     *
     * 
     */
    @Test
    public void getCitaTest() {
        CitaEntity entity = data.get(0);
        CitaEntity resultEntity = citaLogic.getCita(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Cita
     *
     * 
     */
    @Test
    public void deleteCitaTest() {
        CitaEntity entity = data.get(1);
        citaLogic.deleteCita(entity.getId());
        CitaEntity deleted = em.find(CitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Cita
     *
     * 
     */
    @Test
    public void updateCitaTest() throws  HospitalLogicException{
        CitaEntity entity = data.get(0);
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
         Date hoy = new Date();
         Date mañana = new Date(hoy.getTime()+ 86400000);
         newEntity.setFecha(mañana);
        newEntity.setId(entity.getId());
        citaLogic.updateCita(fatherEntity.getId(),newEntity);
     
        CitaEntity resp = em.find(CitaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
