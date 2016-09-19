/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

/**
 *
 * @author Nicolas
 */
public class MedicoDTO {

    /**
     * Nombre del medico
     */
    private String nombre;

    /**
     * id del medico
     */
    private Long id;

    /**
     * disponibilidad del medico
     */
    private String disponibilidad;

    /**
     * Especialidad del medico
     */
    
    private String especialidad;
    
    
    private TurnoDTO turno;
    
    /**
     * Determina si la consulta ya acabo o no;
     */
    private boolean consultaTerminada;
    
    
    private int duracionConsultas;
    
    private int cantidadCitas;
    
    
    /**
     * Constructor vacio
     */
    public MedicoDTO()
    {
        
    }
    /**
     * 
     * @param nombre
     * @param id
     * @param disponibilidad
     * @param espe 
     */
    public MedicoDTO(String nombre, Long id, String disponibilidad, String espe) {
        super();
        this.nombre = nombre;
        this.id = id;
        this.disponibilidad = disponibilidad;
        this.especialidad = espe;
    }

    /**
     * retorna el Nombre del medico
     *
     * @return nombre del medico
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del medicpo
     *
     * @param nombre nombre del medico
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el id del medico
     *
     * @return id del medico
     */
    public Long getId() {
        return id;
    }

    /**
     * modifica el id del medico
     *
     * @param id id del medico
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * retorna la disponibilidad del medico
     *
     * @return disponibilidad del medico
     */
        public String getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Modifica la disponibilidad del medico
     *
     * @param disponibilidad disponibilidad del medico
     */
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

  
    
    public String getEspecializacion()
    {
        return especialidad;
    }
    
    public void setEspecialidad(String e)
    {
        this.especialidad=e;
    }
    
    
    private TurnoDTO darTurno()
    {
        return turno;
    }
    private void setTurno(TurnoDTO turno)
    {
        this.turno=turno;
    }

    /**
     * @return the consultaTerminada
     */
    public boolean isConsultaTerminada() {
        return consultaTerminada;
    }

    /**
     * @param consultaTerminada the consultaTerminada to set
     */
    public void setConsultaTerminada(boolean consultaTerminada) {
        this.consultaTerminada = consultaTerminada;
    }

    /**
     * @return the duracionConsulta
     */
    public int getDuracionConsulta() {
        return duracionConsultas;
    }

    /**
     * @param duracionConsulta the duracionConsulta to set
     */
    public void setDuracionConsulta(int duracionConsulta)
    {
         duracionConsultas+=duracionConsulta;
        
    }
    
    public int darCantidadCitas()
    {
        return cantidadCitas;
    }
    
    public void agregarCita()
    {
        cantidadCitas++;
    } 
    public double calcularPromedioCitaMedico()
    {
        return getDuracionConsulta()/darCantidadCitas();
    }
    
   

}
