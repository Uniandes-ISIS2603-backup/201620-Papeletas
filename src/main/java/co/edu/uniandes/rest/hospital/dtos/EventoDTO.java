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
public class EventoDTO {
    
    private Date fechaIni;
    private Date fechaFin;
    
    public EventoDTO(Date pFechaIni, Date pFechaFin){
        fechaIni = pFechaIni;
        fechaFin = pFechaFin;
    }

    /**
     * @return the fechaIni
     */
    public Date getFechaIni() {
        return fechaIni;
    }

    /**
     * @param fechaIni the fechaIni to set
     */
    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public String toString(){
        return "Evento con fecha inicial " + fechaIni.toString() + " y fecha final " + fechaFin.toString();
    }
    
    
}
