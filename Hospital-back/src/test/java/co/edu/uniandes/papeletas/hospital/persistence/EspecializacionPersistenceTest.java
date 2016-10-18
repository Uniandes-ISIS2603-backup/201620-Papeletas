/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;
 
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
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
 * @author Lara
 */

@RunWith(Arquillian.class)
public class EspecializacionPersistenceTest
{
    
    @Deployment 
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EspecializacionEntity.class.getPackage())
                .addPackage(EspecializacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject 
    private EspecializacionPersistence especializacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<EspecializacionEntity> data = new ArrayList();
    
    public EspecializacionPersistenceTest()
    {
        
    }
    
    @BeforeClass
    public static void setupClass()
    {
        
    }
    @AfterClass
    public static void tearDownClass()
    {
        
    }
    
    private void clearData ()
    {
        em.createQuery("delete from EspecializacionEntity").executeUpdate();
    }
    
    private void insertData ()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EspecializacionEntity entity = factory.manufacturePojo(EspecializacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Before
    public void setUp ()
    {
        try
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    @Test
    public void createEspecializacionTest() throws Exception
    {
        PodamFactory factory = new PodamFactoryImpl();
        EspecializacionEntity newEntity = factory.manufacturePojo(EspecializacionEntity.class);

        EspecializacionEntity result = especializacionPersistence.create(newEntity);

        Assert.assertNotNull(result);
        EspecializacionEntity entity = em.find(EspecializacionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getEspecializacionTest() throws Exception
    {
        List<EspecializacionEntity> list = especializacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(EspecializacionEntity ent : list)
        {
            boolean found = false;
            for (EspecializacionEntity entity : data)
            {
                if (ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @After
    public void tearDown()
    {
        
    }
    @Test
    public void testFind() throws Exception
    {
        
    }
}
