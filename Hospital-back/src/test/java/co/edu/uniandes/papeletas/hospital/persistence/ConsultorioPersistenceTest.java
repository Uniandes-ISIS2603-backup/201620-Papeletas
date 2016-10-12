/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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
 * @author jf.mendez11
 */
@RunWith(Arquillian.class)
public class ConsultorioPersistenceTest {
    
    @Inject
    ConsultorioPersistence consultorioPersistence;
    
    @Deployment
    public static JavaArchive createDeployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConsultorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List <ConsultorioEntity> data = new ArrayList<>();
    
    public ConsultorioPersistenceTest() {
    }
    
    @Before
    public void setUp () {
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
    
    private void clearData () {
        em.createQuery("delete from ConsultorioEntity").executeUpdate();
    }
    
    private void insertData () {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
        /**
     * Prueba para crear un Consultorio.
     */
    @Test
    public void createConsultorioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);

        ConsultorioEntity result = consultorioPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Consultorios.
     *
     *
     */
    @Test
    public void getConsultoriosTest() {
        List<ConsultorioEntity> list = consultorioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ConsultorioEntity ent : list) {
            boolean found = false;
            for (ConsultorioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un consultorio.
     */
    @Test
    public void getConsultorioTest() {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity newEntity = consultorioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para consultar un Consultorio.
     */
    @Test
    public void getConsultorioByNameTest() {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity newEntity = consultorioPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Company.
     */
    @Test
    public void deleteConsultorioTest() {
        ConsultorioEntity entity = data.get(0);
        consultorioPersistence.delete(entity.getId());
        ConsultorioEntity deleted = em.find(ConsultorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    
    

    /**
     * Prueba para actualizar un Consultorio.
     */
    @Test
    public void updateConsultorioTest() {
        ConsultorioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);

        newEntity.setId(entity.getId());

        consultorioPersistence.update(newEntity);

        ConsultorioEntity resp = em.find(ConsultorioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ConsultorioPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }
    
}
