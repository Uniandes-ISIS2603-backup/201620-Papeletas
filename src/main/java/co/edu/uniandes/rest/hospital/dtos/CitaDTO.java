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
    
    //medico asiganado a la cita
    private MedicoDTO medico;
    
    //consultorio asignado a la cita
    private ConsultorioDTO consultorio;
   
    private PacienteDTO paciente;
     /**
     * Constructor. Crea una nueva cita.
     */
    public CitaDTO(Date fecha,long duracion, MedicoDTO medico) 
    {
        this.idCita = (long)Math.random()*(100-1)+1;
        this.fecha = fecha;
        this.duracion = duracion;
        this.medico = medico;
        this.consultorio = null;
        this.paciente = null;
        
    }
    
    public CitaDTO()
    {
      
    }
    public long getId()
    {
        return idCita;
    }
    public void setId(Long pIdCita)
    {
        this.idCita = pIdCita;
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
    public long getDuracion()
    {
        return duracion;
    }
    /**
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracion(long pDuracion)
    {
        duracion = pDuracion;
    }
   
    public MedicoDTO getMedico()
    {
       return medico;
    }
    
    public void setMedico(MedicoDTO pMedico)
    {
        medico = pMedico;
    }
    
    public ConsultorioDTO getConsultorio()
    {
       return consultorio;
    }
 
    public void setConsultorio(ConsultorioDTO pConsultorio)
    {
        consultorio = pConsultorio;
    }
    
    public PacienteDTO getPaciente()
    {
        return paciente;
    }
    
    public void setPaciente(PacienteDTO pPaciente)
    {
        paciente = pPaciente;
    }
}
