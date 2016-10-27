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
    
    public PacienteEntity getPaciente(Long id)throws HospitalLogicException;
    
    public PacienteEntity getPacienteByName(String name) throws HospitalLogicException;
    
    public PacienteEntity createPaciente (PacienteEntity paciente) throws HospitalLogicException;
    
    public PacienteEntity updatePaciente (PacienteEntity paciente)throws HospitalLogicException;
    
    public void deletePaciente (Long id) throws HospitalLogicException;
    
    public void addCita(Long id,CitaEntity cita) throws HospitalLogicException;
    
}
