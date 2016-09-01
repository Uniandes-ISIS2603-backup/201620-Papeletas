/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.Date;

/**
 *Objeto de transferencia de datos de Citas.
 * @author jc.useche10
 */
public class CitaDTO 
{
    //identificador de la cita
    private long idCita;
    
    //fecha para la cual esta programada la cita
    private Date fecha;
    
    //tiempo de la cita
    private long duracion;
    
    //indica si la cita ya ha sido completada o no
    private boolean completada;
    
    //indica si existe una cita
    private boolean hayCita;
    
    //medico asiganado a la cita
    private MedicoDTO medico;
    
    //consultorio asignado a la cita
    private ConsultorioDTO consultorio;
   
    private PacienteDTO paciente;
     /**
     * Constructor. Crea una nueva cita.
     */
    public CitaDTO(long idCita, Date fecha,long duracion, MedicoDTO medico, ConsultorioDTO consultorio, PacienteDTO paciente) 
    {
        this.idCita = idCita;
        this.fecha = fecha;
        this.duracion = duracion;
        this.completada = false;
        this.hayCita = true;
        this.medico = medico;
        this.consultorio = consultorio;
        this.paciente = paciente;
        
    }
    
    public CitaDTO()
    {
        
    }
    /**
    * obtiene la fecha para la cual esta programada la cita
    * @returnfecha
    */
    public Date getFecha()
    {
        return fecha;
    }
    
    /**
     * obtiene la duracion que tuvo la cita
     * @return duracionMins
     */
    public long getDuracionMins()
    {
        return duracion;
    }
    
    /**
     * informa si una cita ya fue completada o no
     * @return true si ya fue completada, false de lo contrario
     */
    public boolean getFueCompletada()
    {
        return completada;
    }
    
    /**
     * informa si hay una cita
     * @return true si hay una cita, false de lo contrario
     */
    public boolean getHayCita()
    {
        return hayCita;
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
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracionMins(long duracion)
    {
        this.duracion= duracion;
    }
    
    /**
     * establece si la cita fue completada
     * @param pDuracion 
     */
    public void setFueCompletada(boolean completada)
    {
        this.completada = completada;
    }
    
    /**
     * establece si hay una cita
     * @param pDuracion 
     */
    public void setHayCita(boolean hayCita)
    {
        this.hayCita =hayCita;
    }

    public long getId() 
    {  
        return idCita;
    }

    public void setId(long idCita)
    {
       this.idCita= idCita;
    }
}
