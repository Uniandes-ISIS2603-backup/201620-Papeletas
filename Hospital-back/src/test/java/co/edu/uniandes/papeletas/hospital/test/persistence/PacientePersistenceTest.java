/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.persistence;

import javax.inject.Inject;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import co.edu.uniandes.papeletas.hospital.persistence.PacientePersistence;
import java.util.ArrayList;
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
 * @author df.castro12
 */
@RunWith(Arquillian.class)
public class PacientePersistenceTest {
    /**
     * @return el jar que se va a desplegar para la prueba
     */
    @Deployment 
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PacienteEntity.class.getPackage())
                .addPackage(PacientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private PacientePersistence pacientePersistence;
    
    @PersistenceContext(unitName = "PapeletasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
            
    private List<PacienteEntity> data = new ArrayList<PacienteEntity>();
    
     /**
     * Configuración inicial de la prueba.
     */
    public PacientePersistenceTest() {
    }
    
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
        em.createQuery("delete from PacienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
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
     * Test of find method, of class PacientePersistence.
     */
    @Test
    public void getPacienteTest() throws Exception {
        PacienteEntity entity = data.get(0);
        PacienteEntity newEntity =  pacientePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getLastName(), newEntity.getLastName());
        
    }

    /**
     * Test of findByName method, of class PacientePersistence.
     */
    @Test
    public void getPacienteByNameTest() throws Exception {
        PacienteEntity entity = data.get(0);
        PacienteEntity newEntity = pacientePersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getLastName(), newEntity.getLastName());
    }
    
    @Test
    public void getPacienteByIdentificacionCivilTest() throws Exception {
        PacienteEntity entity = data.get(0);
        PacienteEntity newEntity = pacientePersistence.findByIdentificacionCivil(entity.getIndentificacionCivil());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getLastName(), newEntity.getLastName());
    }

    /**
     * Test of findAll method, of class PacientePersistence.
     */
    @Test
    public void getPacientesTest() throws Exception {
        List<PacienteEntity> list = pacientePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PacienteEntity ent : list) {
            boolean found = false;
            for (PacienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class PacientePersistence.
     */
    @Test
    public void createPacienteTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);

        PacienteEntity result = pacientePersistence.create(newEntity);

        Assert.assertNotNull(result);
        PacienteEntity entity = em.find(PacienteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }

    /**
     * Test of update method, of class PacientePersistence.
     */
    @Test
    public void updatePacienteTest() throws Exception {
        PacienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);

        newEntity.setId(entity.getId());

        pacientePersistence.update(newEntity);

        PacienteEntity resp = em.find(PacienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(resp.getId(), newEntity.getId());
        Assert.assertEquals(resp.getLastName(), newEntity.getLastName());
        
    }

    /**
     * Test of delete method, of class PacientePersistence.
     */
    @Test
    public void deletePacienteTest() throws Exception {
        PacienteEntity entity = data.get(0);
        pacientePersistence.delete(entity.getId());
        PacienteEntity deleted = em.find(PacienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
