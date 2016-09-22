/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;
import co.edu.uniandes.rest.hospital.mocks.CitaMock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Nicolas
 */
public class MedicoDTO {

    /**
     * Nombre del medico
     */
    private String nombre;

    /**
     * id del medico
     */
    private Long id;

    /**
     * Especialidad del medico
     */
    
    private EspecializacionDTO especialidad;
    
    /**
     * Lista de espera del médico
     */
    private List<CitaDTO> listaEspera;

    /**
     * Turnos del medico
     */
    private List<TurnoDTO> turnos;
    
    /**
     * Duracion de la consulta del medico
     */
    private int duracionConsultas;
    
    /**
     * numero de citas finalizadas;|
     */
    private int cantidadCitas;
    
    /**
     * Relacion con la clase ciita
     */
    private CitaMock cita;
    
    
    /**
     * Constructor vacio
     */
    public MedicoDTO()
    {
        listaEspera = new ArrayList <>();
        turnos = new ArrayList<>();
        cita=new CitaMock();
    }
    /**
     * 
     * @param nombre
     * @param id
     * @param disponibilidad
     * @param espe 
     */

    public MedicoDTO(String nombre, Long id, EspecializacionDTO espe) {
        super();
        this.nombre = nombre;
        this.id = id;
        this.especialidad = espe;
        this.cantidadCitas=0;
        listaEspera = new ArrayList <>();
        turnos = new ArrayList<>();
        cita=new CitaMock();
    }

    /**
     * retorna el Nombre del medico
     *
     * @return nombre del medico
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del medicpo
     *
     * @param nombre nombre del medico
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el id del medico
     *
     * @return id del medico
     */
    public Long getId() {
        return id;
    }

    /**
     * modifica el id del medico
     *
     * @param id id del medico
     */
    public void setId(Long id) {
        this.id = id;

    }

    /**
     * retorna la disponibilidad del medico
     *
     * @return disponibilidad del medico
     */
 

    /**
     * Modifica la disponibilidad del medico
     *
     * @param disponibilidad disponibilidad del medico
     */
  

    public EspecializacionDTO getEspecializacion()

    {
        return especialidad;
    }
    
    public void setEspecialidad(EspecializacionDTO e)
    {
        this.especialidad=e;
    }
    
    /**
     * Obtiene la lista de espera del médico.
     * @return la lista de espera del médico
     */
    public List<CitaDTO> getListaEspera () {
        return listaEspera;
    }
    
    /**
     * Modifica la lista de espera del médico
     * @param listaEspera nueva lista de espera del médico.
     */
    public void setListaEspera (ArrayList listaEspera) {
        this.listaEspera = listaEspera;
    }
    
    /**
     * Agrega una cita nueva a la lista de espera
     * @param cita nueva cita 
     */
    public void agregarCitaListaEspera (CitaDTO cita) {
        listaEspera.add(cita);
    }
    
    /**
     * Remueve un paciente de la lista de espera
     * @param fecha fecha de la cita que se quiere remover
     * @return cita de la lista de espera con la fecha que se encuentra por parámetro
     */
    public CitaDTO removerCitaListaEspera (Date fecha) {
        boolean encontro = false;
        CitaDTO cita = null;
        for (int i = 0; i < listaEspera.size() && !encontro; i++){
            if(listaEspera.get(i).getFecha().equals(fecha)){
                cita = listaEspera.get(i);
                encontro = true;
            }
        }
        return cita;
    }
    
    public List<TurnoDTO> darTurno()
    {
        return turnos;
    }
    private void setTurno(List<TurnoDTO> turnos)
    {
        this.turnos=turnos;
    }

    /**
     * @return the duracionConsulta
     */
    public int getDuracionConsulta() {
        return duracionConsultas;
    }

    /**
     * @param duracionConsulta the duracionConsulta to set
     */
    public void setDuracionConsulta(int duracionConsulta)
    {
         duracionConsultas+=duracionConsulta;
        
    }
    
    public int darCantidadCitas()
    {
        return cantidadCitas;
    }
    
    public void agregarCita()
    {
        cantidadCitas++;
    } 
    
    public void setCantidadCitas(int cantidad)
    {
        cantidadCitas+=cantidad;
    }
    public double calcularPromedioCitaMedico()
    {
        return getDuracionConsulta()/darCantidadCitas();
    }
    
    public void registrarFinCita(int pDuracion, Long idCita, Long idTurno) 
    {
        try
        {
           List<CitaDTO> citas= cita.getCitas(idTurno);
           for(int i=0;i<citas.size();i++)
           {
              if(citas.get(i).getMedico().getId()==getId())
              {
                  setDuracionConsulta(pDuracion);
                  agregarCita();
              }
           }
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        
    }
    
    /**
     * Crea un turno nuevo y lo agrega a la lista de turnos del médico cada cita del turno tiene una duración inicil de 15 minutos
     * @param pFecha Fecha del nuevo turno
     * @param pDuracion Duracion en minutos del turno
     */
    /*public void agregarTurno(Date pFecha, int pDuracion){
        turnos.add(new TurnoDTO(this, pFecha, pDuracion, 15));
    }*/
    
    /**
     * Asigna el consultorio al turno identificado con el id 
     * @param pIdTurno id del turno
     * @param pConsultorio consultorio a asignar
     */
    
}
