/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.api;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import co.edu.uniandes.papeletas.hospital.exceptions.HospitalLogicException;
import java.util.List;

/**
 *
 * @author df.castro12
 */
public interface IPacienteLogic {
     public List<PacienteEntity> getPacientes();
    
    public PacienteEntity getPaciente(Long id);
    
    public PacienteEntity getPacienteByName(String name);
    
    public PacienteEntity createPaciente (PacienteEntity paciente) throws HospitalLogicException;
    
    public PacienteEntity updatePaciente (PacienteEntity paciente);
    
    public void deletePaciente (Long id);
    
    public List<CitaEntity> getCitas(Long id);
    
    public CitaEntity getCita(Long id,Long idCita);
    
    public CitaEntity addCita(Long id,CitaEntity cita) throws HospitalLogicException;
    
    public void removeCita(Long idPaciente, Long idCita);
}
