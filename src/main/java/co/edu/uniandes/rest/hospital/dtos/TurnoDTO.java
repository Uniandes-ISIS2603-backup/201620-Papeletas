/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.Date;


/**
 *
 * @author ac.cabezas716
 */
public class TurnoDTO {
    
    
    private Long id;
    
    /**
     * Dia de la semana del turno
     */
    private Date fecha;
    
    private int duracion;
    
    private CitaDTO[] citas;

    

    public TurnoDTO(Long pId, Date pFecha, int pDuracion) {
        id = pId;
        fecha = pFecha;
        duracion = pDuracion;
        citas = new CitaDTO[duracion/15];
        for(int i = 0; i < citas.length ; i++){
            citas[i] = new CitaDTO();
        }
    }
    
    public TurnoDTO(){
        
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
     * @return the citas
     */
    public CitaDTO[] getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(CitaDTO[] citas) {
        this.citas = citas;
    }
    
}