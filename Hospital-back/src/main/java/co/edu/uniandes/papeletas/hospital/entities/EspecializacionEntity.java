/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import javax.persistence.Entity;
 
/**
 *
 * @author Lara
 */
@Entity
public class EspecializacionEntity extends BaseEntity
{
    private String nombre;
    
    public String getName()
    {
        return nombre;
    }
    
    public void setName(String pNombre)
    {
        nombre=pNombre;
    }
}
 