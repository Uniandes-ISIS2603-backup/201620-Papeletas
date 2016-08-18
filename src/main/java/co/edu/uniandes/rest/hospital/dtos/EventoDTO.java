/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

/**
 *
 * @author ac.cabezas716
 */
public class EventoDTO {
    
    private int horaIni;
    private int horaFin;
    
    public EventoDTO(){
        horaIni = 10;
        horaFin = 11;
    }

    /**
     * @return the horaIni
     */
    public int getHoraIni() {
        return horaIni;
    }

    /**
     * @param horaIni the horaIni to set
     */
    public void setHoraIni(int horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * @return the horaFin
     */
    public int getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }
    
    
}
