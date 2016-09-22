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
    private Long medicoId;
    
    /**
     * Consultorio asignado para el turno
     */
    private Long consultorioId;

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
    public TurnoDTO(Long pId, Long pMedico, Date pFecha, int pDuracion, int pDuracionCitas ) {
        id = pId;
        fecha = pFecha;
        duracion = pDuracion;
        medicoId = pMedico;
        consultorioId = null;
        duracionCitas = pDuracionCitas;
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
     * @return the medicoId
     */
    public Long getMedicoId() {
        return medicoId;
    }

    /**
     * @param medicoId the medicoId to set
     */
    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    /**
     * @return the consultorioId
     */
    public Long getConsultorioId() {
        return consultorioId;
    }

    /**
     * @param consultorioId the consultorioId to set
     */
    public void setConsultorioId(Long consultorioId) {
        this.consultorioId = consultorioId;
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
