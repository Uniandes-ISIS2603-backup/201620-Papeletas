/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
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
public class TurnoPersistenceTest {
     /**
     * @return el jar que se va a desplegar para la prueba
     */
    @Deployment 
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class).addPackage(TurnoEntity.class.getPackage())
                .addPackage(TurnoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    private TurnoPersistence turnoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
            
    private List<TurnoEntity> data = new ArrayList<TurnoEntity>();
    
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
        em.createQuery("delete from TurnoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TurnoEntity entity = factory.manufacturePojo(TurnoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Test of find method, of class TurnoPersistence.
     */
    @Test
    public void getTurnoTest() throws Exception {
        TurnoEntity entity = data.get(0);
        TurnoEntity newEntity =  turnoPersistence.find(entity.getId());
        /*Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getDuracion(), newEntity.getDuracion());
        Assert.assertEquals(entity.getCitaTerminada(), newEntity.getCitaTerminada());*/
    }

    /**
     * Test of findByName method, of class CitaPersistence.
     */
    @Test
    public void getTunroByNameTest() throws Exception {
        TurnoEntity entity = data.get(0);
        TurnoEntity newEntity = turnoPersistence.findByName(entity.getName());
        /*Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getDuracion(), newEntity.getDuracion());
        Assert.assertEquals(entity.getCitaTerminada(), newEntity.getCitaTerminada());*/
    }

    /**
     * Test of findAll method, of class CitaPersistence.
     */
    @Test
    public void getTunrosTest() throws Exception {
        List<TurnoEntity> list = turnoPersistence.findAll();
        //Assert.assertEquals(data.size(), list.size());
        for (TurnoEntity ent : list) {
            boolean found = false;
            for (TurnoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            //Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class CitaPersistence.
     */
    @Test
    public void createCitaTest() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        TurnoEntity newEntity = factory.manufacturePojo(TurnoEntity.class);

        TurnoEntity result = turnoPersistence.create(newEntity);

        //Assert.assertNotNull(result);
        TurnoEntity entity = em.find(TurnoEntity.class, result.getId());
        //Assert.assertNotNull(entity);
        //Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class CitaPersistence.
     */
    @Test
    public void updateCitaTest() throws Exception {
        TurnoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TurnoEntity newEntity = factory.manufacturePojo(TurnoEntity.class);

        newEntity.setId(entity.getId());

        turnoPersistence.update(newEntity);

        TurnoEntity resp = em.find(TurnoEntity.class, entity.getId());

        /*Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getDuracion(), newEntity.getDuracion());
        Assert.assertEquals(entity.getCitaTerminada(), newEntity.getCitaTerminada());*/
    }

    /**
     * Test of delete method, of class CitaPersistence.
     */
    @Test
    public void deleteCitaTest() throws Exception {
        TurnoEntity entity = data.get(0);
        turnoPersistence.delete(entity.getId());
        TurnoEntity deleted = em.find(TurnoEntity.class, entity.getId());
        //Assert.assertNull(deleted);
    }
}
