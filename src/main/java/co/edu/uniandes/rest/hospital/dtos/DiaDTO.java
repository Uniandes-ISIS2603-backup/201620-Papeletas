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
        horaFin = 7;
        eventos = new ArrayList();
        for (int i = 0; i < eventos.size(); i++) {
            eventos.add(new EventoDTO());
        }
    }
    
    public ArrayList<EventoDTO> darEventos(){
        return eventos;
    }
}