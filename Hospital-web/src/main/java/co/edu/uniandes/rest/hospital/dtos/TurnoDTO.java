/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.TurnoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author ac.cabezas716
 */
@XmlRootElement
public class TurnoDTO {
    /**
     * Id del turno
     */
    private Long id;
    
    /**
     * Nombre
     */
    private String name;
    
    /**
     * Fecha del turno
     */
    private Date fecha;
    
    /**
     * Duracion en minutos del turno
     */
    private int duracion;

    /**
     * Duración asignada para cada cita en el turno en minutos
     */
    private int duracionCita;
    /**
     * Constructor de la clase turno:
     * @param pTurnoEntity entidad base para contruir el DTO
     */
    
    public TurnoDTO(TurnoEntity pTurnoEntity) {
        id = pTurnoEntity.getId();
        name = pTurnoEntity.getName();
        fecha = pTurnoEntity.getFecha();
        duracion = pTurnoEntity.getDuracion();
        duracionCita = pTurnoEntity.getDuracionCita();
    }
    /**
     * Constructor por defecto
     */
    public TurnoDTO(){
        id = 0L;
        fecha = new Date();
        duracion = 0;
        duracionCita = 30;
    }
    
    /**
     * Convierte un objeto CitaDTO a CitaEntity.
     *
     * @return Nuevo objeto TurnoEntity.
     * 
     */
    public TurnoEntity toEntity() {
        TurnoEntity entity = new TurnoEntity();
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setDuracion(this.getDuracion());
        entity.setDuracionCita(this.getDuracionCita());
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @return the duracionCitas
     */
    public int getDuracionCita() {
        return duracionCita;
    }

    /**
     * @param duracionCita the duracionCita to set
     */
    public void setDuracionCita(int duracionCita) {
        this.duracionCita = duracionCita;
    }
}
