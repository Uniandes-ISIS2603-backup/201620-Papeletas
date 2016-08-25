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
    private long id;
    
    //fecha para la cual esta programada la cita
    private Date fecha;
    
    //tiempo de la cita
    private double duracionMins;
    
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
    public CitaDTO(long pId, Date pFecha, MedicoDTO pMedico, ConsultorioDTO pConsultorio, PacienteDTO pPaciente) 
    {
        id = pId;
        fecha = pFecha;
        duracionMins = 0.0;
        completada = false;
        hayCita = true;
        medico = pMedico;
        consultorio = pConsultorio;
        paciente = pPaciente;
        
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
    public double getDuracionMins()
    {
        return duracionMins;
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
    public void setFecha(Date pNuevaFecha)
    {
        fecha = pNuevaFecha;
    }
    
    /**
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracionMins(double pDuracion)
    {
        duracionMins = pDuracion;
    }
    
    /**
     * establece si la cita fue completada
     * @param pDuracion 
     */
    public void setFueCompletada(boolean pCompletada)
    {
        completada = pCompletada;
    }
    
    /**
     * establece si hay una cita
     * @param pDuracion 
     */
    public void setHayCita(boolean pHayCita)
    {
        hayCita = pHayCita;
    }

    public long getId() 
    {  
        return id;
    }

    public void setId(long newId)
    {
       id = newId;
    }
}
