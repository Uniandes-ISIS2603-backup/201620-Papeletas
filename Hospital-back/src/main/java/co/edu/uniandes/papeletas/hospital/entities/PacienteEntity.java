/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

/**
 *
 * @author df.castro12
 */
@Entity
public class PacienteEntity extends BaseEntity implements Serializable {
    
    private String lastName;
    
    @OneToMany(mappedBy = "paciente" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaEntity> citas = new ArrayList<>();
    

    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public List<CitaEntity> getCitas(){
        return citas;
    }
    
    public void setCitas(List<CitaEntity> citas){
        this.citas = citas;
    }
}
