/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.castro12
 */
@XmlRootElement
public class PacienteDetailDTO extends PacienteDTO {
    
    private List<CitaDTO> citas = new ArrayList<>();
    
     @PodamExclude
    private PacienteDTO paciente;
    
    public PacienteDetailDTO(){
        super();
    }
    
    /**
     * Crea un objeto PacienteDetailDTO a partir de un objeto PacienteEntity
     * incluyendo los atributos de PacienteDTO.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public PacienteDetailDTO(PacienteEntity entity) {
        super(entity);
        if(entity!=null){
            this.paciente = new PacienteDTO();
        }
        List<CitaEntity> citasList = entity.getCitas();
        for (CitaEntity cita : citasList) {
            this.citas.add(new CitaDTO(cita));
        }
    }

    /**
     * Convierte un objeto PacienteDetailDTO a PacienteEntity incluyendo los
     * atributos de PacienteDTO.
     *
     * @return objeto PacienteEntity.
     *
     */
    @Override
    public PacienteEntity toEntity() {
        PacienteEntity entity = super.toEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setLastName(this.getLastName());
        entity.setIdentificacionCivil(this.getIdentificacionCivil());
         List<CitaDTO> citas = this.getCitas();
        for (CitaDTO cita : this.citas) {         
            entity.getCitas().add(cita.toEntity());
        }
        return entity;
    }
    public PacienteDTO getPaciente(){
        return paciente;
    }
    public void setPaciente(PacienteDTO pPaciente){
        this.paciente = pPaciente;
    }
    
    public List<CitaDTO> getCitas(){
        return this.citas;
    }
    
    public void setCitas(List<CitaDTO> citas){
        this.citas = citas;
    }
}
