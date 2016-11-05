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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ac.cabezas716
 */
@Entity
public class TurnoEntity extends BaseEntity implements Serializable{
    private Date fecha;
    private int duracion;
    private int duracionCita;
    
    @PodamExclude
    @ManyToOne
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
     * @param medico the medico to set
     */
    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
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
