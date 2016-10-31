/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.api.IMedicoLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.MedicoLogic;
import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nicolas
 */
@RunWith(Arquillian.class)
public class MedicoLogicTest
{
     private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IMedicoLogic medicoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<MedicoEntity> data = new ArrayList<MedicoEntity>();
    
    @Deployment
    public static JavaArchive createDeplyment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedicoEntity.class.getPackage())
                .addPackage(MedicoLogic.class.getPackage())
                .addPackage(IMedicoLogic.class.getPackage())
                .addPackage(MedicoPersistence.class.getPackage())
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
        em.createQuery("delete from MedicoEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
    *Crea un medico sin errores
    */
    @Test
    public void createMedicoTest1 () throws HospitalLogicException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        MedicoEntity result = medicoLogic.createMedico(newEntity);
        Assert.assertNotNull(result);     
        MedicoEntity entity = em.find(MedicoEntity.class, result.getId());     
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
   
    /**
     * Intenta crear un medic0o que supera la cantidad maxima de medicos permitidos
     * @throws Exception 
     */
     @Test(expected = HospitalLogicException.class)
     public void createMedicoTest2 () throws Exception {
         MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
         for (int i = data.size(); i < MedicoEntity.MAX_MED; i++) {
             MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
             medicoLogic.createMedico(entity);
         }
         
         MedicoEntity result = medicoLogic.createMedico(newEntity);
     }
 
  
     /**
      * Lista de todos los medicos
      */
    @Test
    public void getMedicoTest() {
        List<MedicoEntity> list = medicoLogic.getMedicos();
        Assert.assertEquals(data.size(), list.size());
        for (MedicoEntity entity : list) {
            boolean found = false;
            for (MedicoEntity ent : data) {
                if (entity.getId().equals(ent.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Test para retornar un medico con un id dado
     */
    @Test
    public void getMedicotest() {
        MedicoEntity entity = data.get(0);
        MedicoEntity result = medicoLogic.getMedico(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());

    }
    
    /**
     * Test que prueba borrar un medico
     */
    @Test
    public void deleteMedicotest() {
        MedicoEntity entity = data.get(1);
        medicoLogic.deleteMedico(entity.getId());
        MedicoEntity deleted = em.find(MedicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Test que prueba actualizar un medico
     */
    @Test
    public void updateMedicoTest() {
        MedicoEntity entity = data.get(0);
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setId(entity.getId());
        medicoLogic.updateMedico(newEntity);
        MedicoEntity resp = em.find(MedicoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());

    }
    
}
