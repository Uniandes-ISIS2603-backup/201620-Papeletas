/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un horario
 * @author ac.cabezas716
 */
public class HorarioDTO {

    
    //------------------------------------------------
    // Enum
    //------------------------------------------------
    public enum TipoUsuario {MEDICO, CONSULTORIO, DESCONOCIDO};
    
    public enum DiaSemana {LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO};
    //------------------------------------------------
    // Atributos
    //------------------------------------------------
    
    private TipoUsuario tipo;
    
    private String nombre;
    
    private List<JornadaDTO> jornadas;
    
    private List<EventoDTO> eventos;
   

    
    //------------------------------------------------
    // Constructores
    //------------------------------------------------
    /**
     * Constructor de la clase, incializa el arreglo de días y los días dependiendo el mes en que el horario es creado
     */
    public HorarioDTO(TipoUsuario pTipo, String pNombre){
        tipo = pTipo;
        nombre = pNombre;
        jornadas = new ArrayList();
        eventos = new ArrayList();
    }
    
    public HorarioDTO(){
        tipo = TipoUsuario.DESCONOCIDO;
        nombre = "desconocido";
        jornadas = new ArrayList();
        eventos = new ArrayList();
    }    
    //------------------------------------------------
    // Metodos
    //------------------------------------------------
   /**
     * @return the tipo
     */
    public TipoUsuario getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the jornadas
     */
    public List<JornadaDTO> getJornadas() {
        return jornadas;
    }

    /**
     * @param jornadas the jornadas to set
     */
    public void setJornadas(ArrayList<JornadaDTO> jornadas) {
        this.jornadas = jornadas;
    }

    /**
     * @return the eventos
     */
    public List<EventoDTO> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(ArrayList<EventoDTO> eventos) {
        this.eventos = eventos;
    }
   
    public void addEvento(Date pFechaI, Date pFechaF){
        getEventos().add(new EventoDTO(pFechaI, pFechaF));
    }
    
    public void addJornada(DiaSemana pDia, int pHoraI, int pHoraF){
        getJornadas().add(new JornadaDTO(pDia, pHoraI, pHoraF));
    }
    
    public String toString() {
        return "Tipo: " + getTipo().toString() + "  Jornadas agregadas: " + getJornadas().size() + "  Eventos programados: " + getEventos().size();
    }
}