/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.MedicoEntity;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
     * Especialidad del medico
     */
    
    private List<EspecializacionDTO> especialidad;
    
    /**
     * Lista de espera del médico
     */
    private List<CitaDTO> listaEspera;

    /**
     * Turnos del medico
     */
    private List<TurnoDTO> turnos;

    
    /**
     * numero de citas finalizadas;|
     */
    private int cantidadCitas;
    
    /**
     * Relacion con la clase ciita
     */
    
   
    
    private Double promedio;
    
    
    /**
     * Constructor vacio
     */
    
    public MedicoDTO()
    {
        listaEspera = new ArrayList <>();
        turnos=new ArrayList<>();
        promedio=0.0;
        especialidad=new ArrayList<>();
    }
   
    
    
    public MedicoDTO(MedicoEntity entity)
    {
        listaEspera = new ArrayList <>();
        turnos = new ArrayList<>();
        especialidad=new ArrayList<>();
        promedio=0.0;
        this.nombre=entity.getName();
        this.id=entity.getId();
    }
    /**
     * 
     * @param nombre
     * @param id
     * @param disponibilidad
     * @param espe 
     */

    public MedicoDTO(String nombre, Long id, List espe) {
        super();
        this.nombre = nombre;
        this.id = id;
        this.especialidad = espe;
        this.cantidadCitas=0;
        listaEspera = new ArrayList <>();
        turnos = new ArrayList<>();
    }
    
     /**
     * Convierte un objeto CitaDTO a CitaEntity.
     *
     * @return Nueva objeto CitaEntity.
     * 
     */
    public MedicoEntity toEntity() {
        MedicoEntity entity = new MedicoEntity();
        entity.setName(this.getNombre());
        entity.setId(this.getId());
        return entity;
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
 * 
 * @return 
 */
    public List getEspecializacion()
    {
        return especialidad;
    }
    
    public void setEspecialidad(List e)
    {
        this.especialidad=e;
    }
    
    public Double getPromedio()
    {
        return (promedio);
    }
    
    public void setPromedio(Double promedio)
    {
        this.promedio=promedio;
    }
    /**
     * Obtiene la lista de espera del médico.
     * @return la lista de espera del médico
     */
    public List<CitaDTO> getListaEspera () {
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
    
    public void deleteCitaListaEspera (Long idEspera) throws MedicoException {
        boolean existe = false;
        for (CitaDTO cit : listaEspera) {
            if (cit.getId().equals(idEspera)) {
                listaEspera.remove(cit);
                existe = true;
            }
        }
        if (!existe) throw new MedicoException ("No existe una cita con el id identificado");
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
    
    public List<TurnoDTO> darTurno()
    {
        return turnos;
    }
    private void setTurno(List<TurnoDTO> turnos)
    {
        this.turnos=turnos;
    }

 
    
    public int darCantidadCitas()
    {
        return cantidadCitas;
    }
    
    public void agregarCita()
    {
        cantidadCitas++;
    } 
    
    public void setCantidadCitas(int cantidad)
    {
        cantidadCitas+=cantidad;
    }

    
    public List updateEspecialidad(List spec)
    {
        especialidad=spec;
        return especialidad;
    }
    
}
