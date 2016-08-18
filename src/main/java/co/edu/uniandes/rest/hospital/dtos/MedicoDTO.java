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
    
   

}
