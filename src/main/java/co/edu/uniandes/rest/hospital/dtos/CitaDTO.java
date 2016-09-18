/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;





/**
 *Objeto de transferencia de datos de Citas.
 * @author jc.useche10
 */
public class CitaDTO 
{
    //identificador de la cita
    private long idCita;
    
    //fecha para la cual esta programada la cita
    private String fecha;
    
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
    public CitaDTO(long idCita, String fecha,long duracion, MedicoDTO medico, ConsultorioDTO consultorio, PacienteDTO paciente) 
    {
        this.idCita = idCita;
        this.fecha = fecha;
        this.duracion = duracion;
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
    public String getFecha()
    {
        return fecha;
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
     * cambia la fecha de una cita
     * @param pNuevaFecha  nueva fecha para la cita
     */
    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
    
    /**
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracion(long duracion)
    {
        this.duracion= duracion;
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
