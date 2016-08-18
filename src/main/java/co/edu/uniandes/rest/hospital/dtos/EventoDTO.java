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
    
    public int getHoraIni(){
        return horaIni;
    }
    
    public int getHoraFin(){
        return horaFin;
    }
    
    
}
