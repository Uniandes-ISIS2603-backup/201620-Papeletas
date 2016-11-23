/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.PacienteEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author df.castro12
 */
@XmlRootElement
public class PacienteDTO {
    private Long id;
    private String name;
    private String lastName;
    private Long identificacionCivil;
    
    
    

    /**
     * Constructor por defecto
     */
    public PacienteDTO() {
	}

    /**
     * Crea un objeto PacienteDTO a partir de un objeto PacienteEntity.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el
     * nuevo objeto.
     * 
     */
    public PacienteDTO(PacienteEntity entity){
        if(entity!=null){
            this.id = entity.getId();
            this.identificacionCivil = entity.getIndentificacionCivil();
            this.name = entity.getName();
            this.lastName = entity.getLastName();
        }
	}
    
    public PacienteDTO(String nombre,String apellido,Long id, Long civil){
        super();
        this.name = nombre;
        this.id = id;
        this.lastName = apellido;
        this.identificacionCivil = civil;
    }
    /**
     * Convierte un objeto PacienteDTO a PacienteEntity.
     *
     * @return Nueva objeto PacienteEntity.
     * 
     */
    public PacienteEntity toEntity() {
        PacienteEntity entity = new PacienteEntity();
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setIdentificacionCivil(this.getIdentificacionCivil());
        entity.setLastName(this.getLastName());
        return entity;
    }

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
     /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
     /**
     * @return the age
     */
    public Long getIdentificacionCivil() {
        return identificacionCivil;
    }

    /**
     * @param identificacionCivil  the name to set
     */
    public void setIdentificacionCivil(Long identificacionCivil) {
        this.identificacionCivil = identificacionCivil;
    }
     
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" , last name: \""+getLastName()+"\" , civil identification :"+getIdentificacionCivil()+ "}" ;  
    }
}
