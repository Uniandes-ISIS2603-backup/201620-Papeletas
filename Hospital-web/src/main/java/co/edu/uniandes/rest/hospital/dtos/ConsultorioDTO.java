/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jf.mendez11
 */
@XmlRootElement
public class ConsultorioDTO {
    
    /**
     * Nombre del consolturio (no aplica)
     */
    private String name;
    
    /**
     * Id del consultorio
     */
    private Long id;
    
    /**
     * Número del consultorio
     */
    private Integer numero;
    
    /**
     * Constructor de la clase
     */
    public ConsultorioDTO () {
    }
    
    /**
     * Constructor de la clase
     */
    public ConsultorioDTO (ConsultorioEntity entity)
    {
        if (entity != null) {
            this.name = entity.getName();
            this.id = entity.getId();
            this.numero = entity.getNumero();
        }
    }
    
    public ConsultorioEntity toEntity () {
        ConsultorioEntity entity = new ConsultorioEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setNumero(this.getNumero());
        return entity;
    }
    
    /**
     * Retorna el id del consultorio
     * @return id del consultorio
     */
    public Long getId ()
    {
        return id;
    }
    
    /**
     * Modifica el id del consultorio
     * @param id id nuevo
     */
    public void setId (Long id)
    {
        this.id = id;
    }
    
    /**
     * Indica el número del consultorio
     * @return el número del consultorio
     */
    public Integer getNumero ()
    {
        return numero;
    }
    
    /**
     * Cambia el número del consultorio
     * @param numero nuevo estado del consultorio
     */
    public void setNumero (Integer numero)
    {
        this.numero = numero;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
}
