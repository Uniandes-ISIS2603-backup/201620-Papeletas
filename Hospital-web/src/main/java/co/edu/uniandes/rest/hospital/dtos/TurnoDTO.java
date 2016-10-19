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
    private MedicoDTO medico;
    
    /**
     * Consultorio asignado para el turno
     */
    private ConsultorioDTO consultorio;

    /**
     * Duración asignada para cada cita en el turno en minutos
     */
    private int duracionCitas;
    /**
     * Constructor de la clase turno:
     * @param pId id del turno
     * @param pMedico Medico dueño del turno creado
     * @param pFecha Fecha que representa el día del turno
     * @param pDuracion Duración del turno en minutos
     * @param pDuracionCitas Duración inicial de cada cita en el turno
     */
    public TurnoDTO(Long pId, MedicoDTO pMedico, Date pFecha, int pDuracion, int pDuracionCitas ) {
        id = pId;
        fecha = pFecha;
        duracion = pDuracion;
        medico = pMedico;
        consultorio = new ConsultorioDTO();
        duracionCitas = pDuracionCitas;
    }
    /**
     * Constructor por defecto
     */
    public TurnoDTO(){
        id = 0L;
        fecha = new Date();
        duracion = 0;
        medico = new MedicoDTO();
        consultorio = new ConsultorioDTO();
        duracionCitas = 0;
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
     * @return the medico
     */
    public MedicoDTO getMedicoId() {
        return medico;
    }

    /**
     * @param medicoId the medico to set
     */
    public void setMedicoId(MedicoDTO medicoId) {
        this.medico = medicoId;
    }

    /**
     * @return the consultorio
     */
    public ConsultorioDTO getConsultorioId() {
        return consultorio;
    }

    /**
     * @param consultorioId the consultorio to set
     */
    public void setConsultorioId(ConsultorioDTO consultorioId) {
        this.consultorio = consultorioId;
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
