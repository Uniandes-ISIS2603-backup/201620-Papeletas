/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.test.logic;

import co.edu.uniandes.papeletas.hospital.api.ICitaLogic;
import co.edu.uniandes.papeletas.hospital.api.IPacienteLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.CitaLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.PacienteLogic;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import co.edu.uniandes.papeletas.hospital.persistence.PacientePersistence;
import co.edu.uniandes.papeletas.hospital.persistence.CitaPersistence;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author df.castro12
 */
@RunWith(Arquillian.class)
public class PacienteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IPacienteLogic pacienteLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PacienteEntity> data = new ArrayList<PacienteEntity>();
    
    private List<CitaEntity> citaData = new ArrayList<>();
    
    private ICitaLogic citaLogic = new CitaLogic();
    
    @Deployment
    public static JavaArchive createDeplyment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PacienteEntity.class.getPackage())
                .addPackage(PacienteLogic.class.getPackage())
                .addPackage(IPacienteLogic.class.getPackage())
                .addPackage(PacientePersistence.class.getPackage())
                .addPackage(CitaEntity.class.getPackage())
                .addPackage(ICitaLogic.class.getPackage())
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
        em.createQuery("delete from CitaEntity").executeUpdate();
        em.createQuery("delete from PacienteEntity").executeUpdate();
          }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
             for (CitaEntity d : entity.getCitas()) {
                d.setPaciente(entity);
            }
            em.persist(entity);
            data.add(entity);
        }
        for (int i=0; i<3;i++){
            CitaEntity citas = factory.manufacturePojo(CitaEntity.class);
            em.persist(citas);
            citaData.add(citas);
        }
    }
    
    @Test
    public void createPaciente1() throws HospitalLogicException{
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
        for (CitaEntity d : newEntity.getCitas()) {
            d.setPaciente(newEntity);
        }

        PacienteEntity result = pacienteLogic.createPaciente(newEntity);
        Assert.assertNotNull(result);

        PacienteEntity entity = em.find(PacienteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getCitas());
        Assert.assertNotNull(result.getCitas());
        Assert.assertEquals(result.getCitas().size(), entity.getCitas().size());

        for (CitaEntity d : result.getCitas()) {
            boolean found = false;
            for (CitaEntity oracle : entity.getCitas()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }

    }
    
    @Test(expected=HospitalLogicException.class)
    public void createPaciente2() throws Exception{
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
        newEntity.setIdentificacionCivil(data.get(0).getIndentificacionCivil());
        PacienteEntity result = pacienteLogic.createPaciente(newEntity);
    }
    
    @Test
    public void deletePacienteTest() {
        PacienteEntity entity = data.get(1);
        pacienteLogic.deletePaciente(entity.getId());
        PacienteEntity deleted = em.find(PacienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void getPacienteTest() {
        PacienteEntity entity = data.get(0);
        PacienteEntity resultEntity = pacienteLogic.getPaciente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void getPacientesTest() {
        List<PacienteEntity> list = pacienteLogic.getPacientes();
        Assert.assertEquals(data.size(), list.size());
        for (PacienteEntity entity : list) {
            boolean found = false;
            for (PacienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updatePacienteTest() {
        PacienteEntity entity = data.get(0);
        PacienteEntity pojoEntity = factory.manufacturePojo(PacienteEntity.class);

        pojoEntity.setId(entity.getId());

        pacienteLogic.updatePaciente(pojoEntity);

        PacienteEntity resp = em.find(PacienteEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getIndentificacionCivil(),resp.getIndentificacionCivil());
    }
    
    @Test
    public void addCitas1()throws HospitalLogicException{
        PacienteEntity entity = data.get(0);
        CitaEntity citaEntity = citaData.get(1);
        Date actual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actual);
        calendar.add(Calendar.DAY_OF_YEAR,30);
        citaEntity.setFecha(calendar.getTime());
        CitaEntity response = pacienteLogic.addCita(entity.getId(), citaEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(citaEntity.getId(), response.getId());
    }
    /**
     * Test de error para cuando una cita no existe
     * @throws Exception 
     */
    @Test(expected=HospitalLogicException.class)
    public void addCitas2()throws Exception{
        pacienteLogic.addCita(data.get(0).getId(),null);
    }
    
    /**
     * Test de error cuando se reserva una cita que ya pasó
     * @throws Exception 
     */
    @Test(expected=HospitalLogicException.class)
    public void addCitas3()throws Exception{
        PacienteEntity entity = data.get(0);
        CitaEntity citaEntity = citaData.get(0);
        Date actual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actual);
        calendar.add(Calendar.DAY_OF_YEAR,-30);
        citaEntity.setFecha(calendar.getTime());
        pacienteLogic.addCita(entity.getId(), citaEntity);
    }
    
    @Test
    public void removeCitaTest() {
        pacienteLogic.removeCita(data.get(0).getId(),citaData.get(0).getId());
        CitaEntity response = pacienteLogic.getCita(data.get(0).getId(), citaData.get(0).getId());
        Assert.assertNull(response);
    }
    
    @Test
    public void getCitaTest() throws HospitalLogicException {
        PacienteEntity entity = data.get(0);
        CitaEntity citaEntity = citaData.get(0);
        Date actual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actual);
        calendar.add(Calendar.DAY_OF_YEAR,30);
        citaEntity.setFecha(calendar.getTime());
        pacienteLogic.addCita(entity.getId(), citaEntity);
        CitaEntity response = pacienteLogic.getCita(entity.getId(), citaEntity.getId());
        Assert.assertEquals(citaEntity.getId(), response.getId());
    }
    
    @Test
    public void listCitasTest() {
        List<CitaEntity> list = pacienteLogic.getCitas(data.get(0).getId());
        Assert.assertEquals(5, list.size());
    }
}
