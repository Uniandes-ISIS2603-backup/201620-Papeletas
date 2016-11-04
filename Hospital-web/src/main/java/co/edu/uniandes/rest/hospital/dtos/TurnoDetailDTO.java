/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ac.cabezas716
 */
@XmlRootElement
public class TurnoDetailDTO extends TurnoDTO{
    /**
     * Medico dueño del turno
     */
    @PodamExclude
    private MedicoDTO medico;
    
    /**
     * Consultorio asignado para el turno
     */
    @PodamExclude
    private ConsultorioDTO consultorio;
    
    public TurnoDetailDTO () {
        super();
    }
    
     /**
     * Crea un objeto TurnoDetailDTO a partir de un objeto TurnoEntity
     * incluyendo los atributos de TurnoDTO.
     *
     * @param entity Entidad TurnoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public TurnoDetailDTO(TurnoEntity entity) {
        super(entity);
        if (entity.getMedico()!= null) 
        {
            this.medico = new MedicoDTO(entity.getMedico());
        }
        if(entity.getConsultorio() != null)
        {
            this.consultorio = new ConsultorioDTO(entity.getConsultorio());
        }
    }
    
    /**
     * Convierte un objeto TurnoDetailDTO a TurnoEntity incluyendo los
     * atributos de TurnoDTO.
     *
     * @return  objeto TurnoEntity.
     *
     */
    @Override
    public TurnoEntity toEntity() {
        TurnoEntity entity = super.toEntity();
        if (this.getMedico() != null) {
            entity.setMedico(this.getMedico().toEntity());
        }
        if (this.getConsultorio() != null) {
            entity.setConsultorio(this.getConsultorio().toEntity());
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
}
