/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author ac.cabezas716
 */
@Entity
public class TurnoEntity extends BaseEntity{
    private Date fecha;
    private int duracion;
    private int duracionCita;
    
    @ManyToOne
    private MedicoEntity medico;
    
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
     * @param duracionCita the duracionCita to set
     */
    public void setDuracionCita(int duracionCita) {
        this.duracionCita = duracionCita;
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
