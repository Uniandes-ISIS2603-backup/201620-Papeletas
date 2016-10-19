/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Nicolas
 */
@Entity
public class MedicoEntity extends BaseEntity implements Serializable
{
       
    @OneToMany(mappedBy = "medico")
    private List<CitaEntity> citas;
    
    @OneToMany(mappedBy = "medico")
    private List<TurnoEntity> turnos;
  
    
    public List<CitaEntity> citas()
    {
        return citas;
    }
     public List<TurnoEntity> turnos()
    {
        return turnos;
    } 
}
