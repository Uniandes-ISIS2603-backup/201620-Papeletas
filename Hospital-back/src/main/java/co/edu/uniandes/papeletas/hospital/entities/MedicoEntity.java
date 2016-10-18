/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Nicolas
 */
@Entity
public class MedicoEntity extends BaseEntity implements Serializable
{
    
        private List medicos = new ArrayList<>();
    
    private String nombre;
    
    private Long id;
    
    public String getNombre()
    {
        return nombre;
    }
    public Long getID()
    {
        return id;
    }
    
    public void setNombre(String pNombre)
    {
        this.nombre=pNombre;
    }
    
    public void setID(Long id)
    {
        this.id=id;
    }   
}