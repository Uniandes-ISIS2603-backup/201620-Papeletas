/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

import javax.persistence.ManyToOne;
 
/**
 *
 * @author Lara
 */
@Entity
public class EspecializacionEntity extends BaseEntity
{
    private String nombre;
    
    @OneToMany(mappedBy = "especializacion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoEntity> medicos = new ArrayList<>();


   
    
    public String getName()
    {
        return nombre;
    }
    
    public void setName(String pNombre)
    {
        nombre=pNombre;
    }
    public List<MedicoEntity> medicos()
    {
        return medicos;
    }

}
 
