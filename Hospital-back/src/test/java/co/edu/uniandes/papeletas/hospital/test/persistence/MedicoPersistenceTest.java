/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.persistence;

import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.persistence.MedicoPersistence;
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

import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nicolas
 */
@RunWith(Arquillian.class)
public class MedicoPersistenceTest {

    
    @Deployment
    public static JavaArchive createDeployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoPersistence.class.getPackage())
                .addPackage(MedicoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
        @Inject
    MedicoPersistence medicoPersistence;
    
    @PersistenceContext(unitName = "PapeletasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List <MedicoEntity> data = new ArrayList<>();
    
    public MedicoPersistenceTest() {
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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }
    
    private void insertData () {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
        /**
     * Prueba para crear un Medico.
     */
    @Test
    public void createMedicoTest()throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        MedicoEntity result = medicoPersistence.create(newEntity);
        Assert.assertNotNull(result);
        MedicoEntity entity = em.find(MedicoEntity.class, result.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar la lista de Medicos.
     *
     *
     */
    @Test
    public void getMedicosTest()throws Exception {
        List<MedicoEntity> list = medicoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedicoEntity ent : list) {
            boolean found = false;
            for (MedicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un medico.
     */
    @Test
    public void getMedicoTest() throws Exception {
        MedicoEntity entity = data.get(0);
        MedicoEntity newEntity = medicoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    

    /**
     * Prueba para eliminar un Company.
     */
    @Test
    public void deleteMedicoTest() throws Exception{
        MedicoEntity entity = data.get(0);
        medicoPersistence.delete(entity.getId());
        MedicoEntity deleted = em.find(MedicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Medico.
     */
    @Test
    public void updateMedicoTest()throws Exception {
        MedicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);

        newEntity.setId(entity.getId());

        medicoPersistence.update(newEntity);

        MedicoEntity resp = em.find(MedicoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
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
     * Test of find method, of class MedicoPersistence.
     */
    @Test
    public void testFind() throws Exception {
    }
    
}
