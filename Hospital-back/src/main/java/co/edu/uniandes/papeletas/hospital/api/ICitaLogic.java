package co.edu.uniandes.papeletas.hospital.api;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jc.useche10
 */
public interface ICitaLogic {
    public List<CitaEntity> getCitas();
    public CitaEntity getCita(Long id);
    public CitaEntity getCitaByName(String name);
    public CitaEntity createCita(CitaEntity entity) throws HospitalLogicException; 
    public CitaEntity updateCita(CitaEntity entity) throws HospitalLogicException;
    public void deleteCita(Long id);
}

