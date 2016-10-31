/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.CitaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;





/**
 *Objeto de transferencia de datos de Citas.
 * @author jc.useche10
 */
@XmlRootElement
public class CitaDTO 
{
    //identificador de la cita
    private Long idCita;
    //fecha para la cual esta programada la cita
    private String name;
    //fecha para la cual esta programada la cita
    private Date fecha;
    //tiempo de la cita
    private int duracion;
    
     /**
     * Constructor. Crea una nueva cita.
     */
    public CitaDTO()
    {}
     /**
     * Crea un objeto CitaMinimumDTO a partir de un objeto CitaEntity.
     *
     * @param entity Entidad CitaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public CitaDTO(CitaEntity entity) {
        if (entity != null) {
            this.name = entity.getName();
            this.idCita = entity.getId();
            this.fecha = entity.getFecha();
            this.duracion = entity.getDuracion();
        }
    }
     /**
     * Convierte un objeto CitaDTO a CitaEntity.
     *
     * @return Nueva objeto CitaEntity.
     * 
     */
    public CitaEntity toEntity() {
        CitaEntity entity = new CitaEntity();
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setDuracion(this.getDuracion());
        return entity;
    }
    public Long getId()
    {
        return idCita;
    }
    public void setId(Long pIdCita)
    {
        this.idCita = pIdCita;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String pname)
    {
        this.name = pname;
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
}
