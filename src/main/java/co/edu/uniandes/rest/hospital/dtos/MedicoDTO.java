/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.ArrayList;
import java.util.Date;

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
     * Lista de espera del médico
     */
    private ArrayList<CitaDTO> listaEspera;
    
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
        listaEspera = new ArrayList <>();
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
    
    /**
     * Obtiene la lista de espera del médico.
     * @return la lista de espera del médico
     */
    public ArrayList getListaEspera () {
        return listaEspera;
    }
    
    /**
     * Modifica la lista de espera del médico
     * @param listaEspera nueva lista de espera del médico.
     */
    public void setListaEspera (ArrayList listaEspera) {
        this.listaEspera = listaEspera;
    }
    
    /**
     * Agrega una cita nueva a la lista de espera
     * @param cita nueva cita 
     */
    public void agregarCitaListaEspera (CitaDTO cita) {
        listaEspera.add(cita);
    }
    
    /**
     * Remueve un paciente de la lista de espera
     * @param fecha fecha de la cita que se quiere remover
     * @return cita de la lista de espera con la fecha que se encuentra por parámetro
     */
    public CitaDTO removerCitaListaEspera (Date fecha) {
        boolean encontro = false;
        CitaDTO cita = null;
        for (int i = 0; i < listaEspera.size() && !encontro; i++){
            if(listaEspera.get(i).getFecha().equals(fecha)){
                cita = listaEspera.get(i);
                encontro = true;
            }
        }
        return cita;
    }

}
