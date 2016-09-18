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
    }
    
    public TurnoDTO(){
    }
    
}