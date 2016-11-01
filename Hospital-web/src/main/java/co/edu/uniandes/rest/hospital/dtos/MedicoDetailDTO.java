/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicolas
 */
public class MedicoDetailDTO extends MedicoDTO
{
    
    @PodamExclude
    private MedicoDTO medico;

    
    
    public MedicoDetailDTO() {
        super();
    }
    
     /**
     * Crea un objeto MedicoDEtailDTO a partir de un objeto MedicoEntity
     * incluyendo los atributos de MedicoDTO.
     *
     * @param entity Entidad MedicoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public MedicoDetailDTO(MedicoEntity entity) {
        super(entity);
        if (entity!=null) 
         {
             this.medico = new MedicoDTO();
        
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
    public MedicoEntity toEntity() {
        MedicoEntity entity = super.toEntity();      
        entity.setId(this.getId());
        entity.setName(this.getNombre());
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
    
    
    
    
}
