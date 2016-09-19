/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.Calendar;
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
    
    private MedicoDTO medico;
    
    private ConsultorioDTO consultorio;
    
    private CitaDTO[] citas;

    public TurnoDTO(MedicoDTO pMedico, Date pFecha, int pDuracion) {
        id = new Long((int)(Math.random()* Long.MAX_VALUE));
        fecha = pFecha;
        duracion = pDuracion;
        citas = new CitaDTO[duracion/15];
        medico = pMedico;
        consultorio = null;
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        for(int i = 0; i < citas.length ; i++){
            Date f = new Date(c.getTimeInMillis() + (i * 900000L));
            citas[i] = new CitaDTO( f, 15L, pMedico);
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
        this.setCitas(citas);
    }

    /**
     * @return the medico
     */
    public MedicoDTO getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    /**
     * @return the consultorio
     */
    public ConsultorioDTO getConsultorio() {
        return consultorio;
    }

    /**
     * Asigna consultorio a un turno y a todas las citas de ese turno
     * @param pConsultorio Consultorio a asignar
     */
    public void setConsultorio(ConsultorioDTO pConsultorio) {
        this.consultorio = pConsultorio;
        for(int i = 0; i < citas.length; i++){
            //Asignar consultorio a las citas
            citas[i].setConsultorio(pConsultorio);
        }
    }
    
    
}