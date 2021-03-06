/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ac.cabezas716
 */
@Entity
public class TurnoEntity extends BaseEntity implements Serializable{
  
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Integer duracion;
    private Integer duracionCita;
    
    
    @ManyToOne
    @PodamExclude
    private MedicoEntity medico;
    
    @PodamExclude
    @ManyToOne
    private ConsultorioEntity consultorio;
    
    
  
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the duracionCita
     */
    public int getDuracionCita() {
        return duracionCita;
    }
    
    /**
     * @param duracionCita the duracionCita to set
     */
    public void setDuracionCita(int duracionCita) {
        this.duracionCita = duracionCita;
    }
    /**
     * @return the medico
     */
    public MedicoEntity getMedico() {
        return medico;
    }

    /**
     * @param pMedico the medico to set
     */
    public void setMedico(MedicoEntity pMedico) {
        medico = pMedico;
    }

    

    /**
     * @return the consultorio
     */
    public ConsultorioEntity getConsultorio() {
        return consultorio;
    }

    /**
     * @param consultorio the consultorio to set
     */
    public void setConsultorio(ConsultorioEntity consultorio) {
        this.consultorio = consultorio;
    }
    
}
