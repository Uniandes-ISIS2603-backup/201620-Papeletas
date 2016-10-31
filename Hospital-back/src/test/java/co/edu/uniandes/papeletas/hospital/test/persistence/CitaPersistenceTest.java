/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.persistence;

import javax.inject.Inject;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.useche10
 */
@RunWith(Arquillian.class)
public class CitaPersistenceTest {
    
    /**
     * @return el jar que se va a desplegar para la prueba
     */
    @Deployment 
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(CitaEntity.class.getPackage())
                .addPackage(CitaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private CitaPersistence citaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
            
    private List<CitaEntity> data = new ArrayList<CitaEntity>();
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
    
    private void clearData() {
        em.createQuery("delete from CitaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CitaEntity entity = factory.manufacturePojo(CitaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Test of find method, of class CitaPersistence.
     */
    @Test
    public void getCitaTest() throws Exception {
        CitaEntity entity = data.get(0);
        CitaEntity newEntity =  citaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(entity.getFecha());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(newEntity.getFecha());
        Assert.assertEquals(c1.get(Calendar.MONTH),c2.get(Calendar.MONTH));
        Assert.assertEquals(c1.get(Calendar.DAY_OF_YEAR),c2.get(Calendar.DAY_OF_YEAR));
        Assert.assertEquals(entity.getDuracion(), newEntity.getDuracion());
    }

    /**
     * Test of findByName method, of class CitaPersistence.
     *
    @Test
    public void getCitaByNameTest() throws Exception {
        CitaEntity entity = data.get(0);
        CitaEntity newEntity = citaPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(entity.getFecha());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(newEntity.getFecha());
        Assert.assertEquals(c1.get(Calendar.MONTH),c2.get(Calendar.MONTH));
        Assert.assertEquals(c1.get(Calendar.DAY_OF_YEAR),c2.get(Calendar.DAY_OF_YEAR));
        Assert.assertEquals(entity.getDuracion(), newEntity.getDuracion());
    }/

    /**
     * Test of findAll method, of class CitaPersistence.
     */
    @Test
    public void getCitasTest() throws Exception {
        List<CitaEntity> list = citaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CitaEntity ent : list) {
            boolean found = false;
            for (CitaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class CitaPersistence.
     */
    @Test
    public void createCitaTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);

        CitaEntity result = citaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        CitaEntity entity = em.find(CitaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class CitaPersistence.
     */
    @Test
    public void updateCitaTest() throws Exception {
        CitaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);

        newEntity.setId(entity.getId());

        citaPersistence.update(newEntity);

        CitaEntity resp = em.find(CitaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Calendar c1 = Calendar.getInstance();
        c1.setTime(newEntity.getFecha());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(resp.getFecha());
        Assert.assertEquals(c1.get(Calendar.MONTH),c2.get(Calendar.MONTH));
        Assert.assertEquals(c1.get(Calendar.DAY_OF_YEAR),c2.get(Calendar.DAY_OF_YEAR));
        Assert.assertEquals(newEntity.getDuracion(), resp.getDuracion());
    }

    /**
     * Test of delete method, of class CitaPersistence.
     */
    @Test
    public void deleteCitaTest() throws Exception {
        CitaEntity entity = data.get(0);
        citaPersistence.delete(entity.getId());
        CitaEntity deleted = em.find(CitaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
