/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.api.IConsultorioLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.ConsultorioLogic;
import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.ConsultorioPersistence;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jf.mendez11
 */
@RunWith(Arquillian.class)
public class ConsultorioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IConsultorioLogic consultorioLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ConsultorioEntity> data = new ArrayList<ConsultorioEntity>();
    
    @Deployment
    public static JavaArchive createDeplyment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConsultorioEntity.class.getPackage())
                .addPackage(ConsultorioLogic.class.getPackage())
                .addPackage(IConsultorioLogic.class.getPackage())
                .addPackage(ConsultorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
    
    private void clearData() {
        em.createQuery("delete from ConsultorioEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
    *Crea un consultorio donde el número no existe y se respeta la capacidad máxima.
    */
    @Test
    public void createConsultorioTest1 () throws HospitalLogicException {
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);
        
        ConsultorioEntity result = consultorioLogic.createConsultorio(newEntity);
        Assert.assertNotNull(result);
        
        ConsultorioEntity entity = em.find(ConsultorioEntity.class, result.getId());
        
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());
    }
    
    /**
     * Prueba crear un consultorio con número que ya existe, respetando la capacidad 
     */
    @Test(expected = HospitalLogicException.class)
     public void createConsultorioTest2 () throws Exception {
         ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);
         newEntity.setNumero(data.get(0).getNumero());
         ConsultorioEntity result = consultorioLogic.createConsultorio(newEntity);
     }
     
     /**
      * Prueba crear un consultorio cuando se excede de la capacidad máxima
      */
     @Test(expected = HospitalLogicException.class)
     public void createConsultorioTest3 () throws Exception {
         ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);
         for (int i = data.size(); i < ConsultorioEntity.MAX_NUM_CONSULTORIOS; i++) {
             ConsultorioEntity entity = factory.manufacturePojo(ConsultorioEntity.class);
             consultorioLogic.createConsultorio(entity);
         }
         
         ConsultorioEntity result = consultorioLogic.createConsultorio(newEntity);
     }
     
    @Test
    public void getConsultoriosTest() {
        List<ConsultorioEntity> list = consultorioLogic.getConsultorios();
        Assert.assertEquals(data.size(), list.size());
        for (ConsultorioEntity entity : list) {
            boolean found = false;
            for (ConsultorioEntity ent : data) {
                if (entity.getId().equals(ent.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getConsultorioTest() {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity result = consultorioLogic.getConsultorio(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getNumero(), result.getNumero());
        Assert.assertEquals(entity.getId(), result.getId());
    }
    
    @Test
    public void deleteCompanyTest() {
        ConsultorioEntity entity = data.get(1);
        consultorioLogic.deleteConsultorio(entity.getId());
        ConsultorioEntity deleted = em.find(ConsultorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateCompanyTest() {
        ConsultorioEntity entity = data.get(0);
        ConsultorioEntity newEntity = factory.manufacturePojo(ConsultorioEntity.class);

        newEntity.setId(entity.getId());

        consultorioLogic.updateConsultorio(newEntity);

        ConsultorioEntity resp = em.find(ConsultorioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
 }
