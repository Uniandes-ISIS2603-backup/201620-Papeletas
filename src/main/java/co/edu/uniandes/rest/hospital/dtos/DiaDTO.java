/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.ArrayList;

/**
 * Clase que representa un día 
 * @author ac.cabezas716
 */
public class DiaDTO {
    
    private ArrayList<EventoDTO> eventos;
    
    private int horaInicio;
    
    private int horaFin;
    
    public DiaDTO(){
        horaInicio = 7;
        horaFin = 19;
        eventos = new ArrayList();
        for (int i = 0; i < eventos.size(); i++) {
            eventos.add(new EventoDTO());
        }
    }
    public DiaDTO(int pHoraInicio, int pHoraFin){
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        eventos = new ArrayList();
        for (int i = 0; i < eventos.size(); i++) {
            eventos.add(new EventoDTO());
        }
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(ArrayList<EventoDTO> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the horaInicio
     */
    public int getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
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