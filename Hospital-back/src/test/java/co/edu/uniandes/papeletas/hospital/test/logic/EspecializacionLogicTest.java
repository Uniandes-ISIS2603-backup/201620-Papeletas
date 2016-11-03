/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.api.IEspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.EspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.EspecializacionPersistence;
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
 * @author jc.lara10
 */
@RunWith(Arquillian.class)
public class EspecializacionLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IEspecializacionLogic especializacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<EspecializacionEntity> data = new ArrayList<EspecializacionEntity>();
    
    @Deployment
    public static JavaArchive createDeplyment ()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EspecializacionEntity.class.getPackage())
                .addPackage(EspecializacionLogic.class.getPackage())
                .addPackage(IEspecializacionLogic.class.getPackage())
                .addPackage(EspecializacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void setUp()
    {
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
    
    private void clearData()
    {
        em.createQuery("delete from CitaEntity").executeUpdate();
        em.createQuery("delete from PacienteEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for (int i = 0; i < 3; i++)
        {
            EspecializacionEntity entity = factory.manufacturePojo(EspecializacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createEspecializacion() throws HospitalLogicException
    {
        EspecializacionEntity newEntity = factory.manufacturePojo(EspecializacionEntity.class);
        EspecializacionEntity result;
        result = especializacionLogic.createEspecializacion(newEntity);
        Assert.assertNotNull(result);
        EspecializacionEntity entity = em.find(EspecializacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }
    
    @Test
    public void deleteEspecializacionTest()
    {
        EspecializacionEntity entity = data.get(1);
        especializacionLogic.deleteEspecializacion(entity.getId());
        EspecializacionEntity deleted = em.find(EspecializacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void getEspecializacionTest()
    {
        EspecializacionEntity entity = data.get(0);
        EspecializacionEntity resultEntity = especializacionLogic.getEspecializacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getEspecializacionesTest() {
        List<EspecializacionEntity> list = especializacionLogic.getEspecializaciones();
        Assert.assertEquals(data.size(), list.size());
        for (EspecializacionEntity entity : list) {
            boolean found = false;
            for (EspecializacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
}
