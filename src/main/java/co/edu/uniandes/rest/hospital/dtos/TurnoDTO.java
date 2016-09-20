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
    /**
     * Id del turno
     */
    private Long id;
    
    /**
     * Fecha del turno
     */
    private Date fecha;
    
    /**
     * Duracion en minutos del turno
     */
    private int duracion;
    
    /**
     * Medico dueño del turno
     */
    private MedicoDTO medico;
    
    /**
     * Consultorio asignado para el turno
     */
    private ConsultorioDTO consultorio;
    
    /**
     * Arreglo de citas del turno
     */
    private CitaDTO[] citas;

    /**
     * Duración asignada para cada cita en el turno en minutos
     */
    private int duracionCitas;
    /**
     * Constructor de la clase turno:
     * -El arreglo de citas es inicializado sin paciente lo que indican que no han sido asignadas.
     * -Las fechas de cada cita ya han sido asignadas
     * @param pMedico Medico dueño del turno creado
     * @param pFecha Fecha que representa el día del turno
     * @param pDuracion Duración del turno en minutos
     * @param pDuracionCitas Duración inicial de cada cita en el turno
     * @param pId id del turno
     */
    public TurnoDTO(Long pId, MedicoDTO pMedico, Date pFecha, int pDuracion, int pDuracionCitas ) {
        id = pId;
        fecha = pFecha;
        duracion = pDuracion;
        citas = new CitaDTO[duracion/15];
        medico = pMedico;
        consultorio = null;
        duracionCitas = pDuracionCitas;
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

    /**
     * @return the duracionCitas
     */
    public int getDuracionCitas() {
        return duracionCitas;
    }

    /**
     * @param duracionCitas the duracionCitas to set
     */
    public void setDuracionCitas(int duracionCitas) {
        this.duracionCitas = duracionCitas;
    }
    
}