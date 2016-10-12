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

/**
 *
 * @author jc.useche10
 */
@Entity
public class CitaEntity extends BaseEntity implements Serializable {
     //fecha para la cual esta programada la cita
    private Date fecha;
    
    //tiempo de la cita
    private int duracion;
    
    //medico asiganado a la cita
    // @ManyToOne
    // private MedicoEntity medico;
    
    //consultorio asignado a la cita
    private ConsultorioEntity consultorio;
   
    private PacienteEntity paciente;
    
    private Boolean citaTerminada;
    
    /**
    * obtiene la fecha para la cual esta programada la cita
    * @returnfecha
    */
    public Date getFecha()
    {
        return fecha;
    }
     /**
     * cambia la fecha de una cita
     * @param pNuevaFecha  nueva fecha para la cita
     */
    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
    
    /**
     * obtiene la duracion que tuvo la cita
     * @return duracion
     */
    public int getDuracion()
    {
        return duracion;
    }
    /**
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracion(int pDuracion)
    {
        duracion = pDuracion;
    }
   
/*
    public MedicoEntity getMedico()
    {
       return medico;
    }
    
    public void setMedico(MedicoEntity pMedico)
    {
        medico = pMedico;
    }
*/     
    public ConsultorioEntity getConsultorio()
    {
       return consultorio;
    }
 
    public void setConsultorio(ConsultorioEntity pConsultorio)
    {
        consultorio = pConsultorio;
    }
    
    public PacienteEntity getPaciente()
    {
        return paciente;
    }
    
    public void setPaciente(PacienteEntity pPaciente)
    {
        paciente = pPaciente;
    }
    
    public boolean getCitaTerminada()
    {
        return citaTerminada;
    }
    public void setCitaTerminada()
    {
        citaTerminada = !citaTerminada;
    }
    //private Long idTurno; ??
}
