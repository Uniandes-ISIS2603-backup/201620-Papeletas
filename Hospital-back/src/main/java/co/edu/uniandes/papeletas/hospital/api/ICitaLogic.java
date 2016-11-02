package co.edu.uniandes.papeletas.hospital.api;
import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
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
    public List<CitaEntity> getCitas(Long medicoId);
    public CitaEntity getCita(Long id);
    public CitaEntity getCitaByName(Long medicoid, String name);
    public CitaEntity createCita(Long medicoid, CitaEntity entity) throws HospitalLogicException; 
    public CitaEntity updateCita(Long medicoid, CitaEntity entity);
    public void deleteCita(Long id);
    public ConsultorioEntity getConsultorio(Long citaI);
    public PacienteEntity getPaciente(Long citaId);
}

