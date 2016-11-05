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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Nicolas
 */
@Entity
public class MedicoEntity extends BaseEntity implements Serializable
{
    public final static int MAX_MED=103;
       
    @OneToMany(mappedBy = "medico",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaEntity> citas= new ArrayList<>();
    
    @OneToMany(mappedBy = "medico",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurnoEntity> turnos=new ArrayList<>();
    
    @ManyToOne
    private EspecializacionEntity especializacion;
    
    

    
    public List<CitaEntity> getCitas()
    {
        return citas;
    }
     public List<TurnoEntity> getTurnos()
    {
        return turnos;

    }
     public EspecializacionEntity especialidades()
    {
        return especializacion;
    }
 } 


