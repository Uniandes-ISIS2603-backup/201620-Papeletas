/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.useche10
 */
@XmlRootElement
public class CitaDetailDTO extends CitaDTO{

    //medico asiganado a la cita
    @PodamExclude
    private MedicoDTO medico;
    
    //consultorio asignado a la cita
    @PodamExclude
    private ConsultorioDTO consultorio;
   
    @PodamExclude
    private PacienteDTO paciente;
    
    public CitaDetailDTO() {
        super();
    }
    
     /**
     * Crea un objeto CitaDetailDTO a partir de un objeto CitaEntity
     * incluyendo los atributos de CitaDTO.
     *
     * @param entity Entidad CitaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CitaDetailDTO(CitaEntity entity) {
        super(entity);
        if (entity.getMedico()!= null) 
        {
            this.medico = new MedicoDTO(entity.getMedico());
        }
        if(entity.getConsultorio() != null)
        {
            this.consultorio = new ConsultorioDTO(entity.getConsultorio());
        }
        if(entity.getPaciente() != null)
        {
            this.paciente = new PacienteDTO(entity.getPaciente());
        }
    }
    
    /**
     * Convierte un objeto CitaDetailDTO a CitaEntity incluyendo los
     * atributos de CitaDTO.
     *
     * @return  objeto CitaEntity.
     *
     */
    @Override
    public CitaEntity toEntity() {
        CitaEntity entity = super.toEntity();
        if (this.getMedico() != null) {
            entity.setMedico(this.getMedico().toEntity());
        }
        if (this.getConsultorio() != null) {
            entity.setConsultorio(this.getConsultorio().toEntity());
        }
        if (this.getMedico() != null) {
            entity.setPaciente(this.getPaciente().toEntity());
        }
        return entity;
    }
    
     public MedicoDTO getMedico()
    {
       return medico;
    }
    
    public void setMedico(MedicoDTO pMedico)
    {
        medico = pMedico;
    }    
    public ConsultorioDTO getConsultorio()
    {
       return consultorio;
    }
 
    public void setConsultorio(ConsultorioDTO pConsultorio)
    {
        consultorio = pConsultorio;
    }
    
    public PacienteDTO getPaciente()
    {
        return paciente;
    }
    
    public void setPaciente(PacienteDTO pPaciente)
    {
        paciente = pPaciente;
    }
}
