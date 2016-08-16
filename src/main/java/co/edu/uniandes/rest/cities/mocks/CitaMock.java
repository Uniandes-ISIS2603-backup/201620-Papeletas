/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.mocks;

import java.util.Date;

/**
 *
 * @author jc.useche10
 */
public class CitaMock {
    
    //fecha para la cual esta programada la cita
    private Date fecha;
    
    //tiempo de la cita
    private double duracionMins;
    
    //indica si la cita ya ha sido completada o no
    private boolean completada;
    
    //indica si existe una cita
    private boolean hayCita;
    
     /**
     * Constructor. Crea una nueva cita.
     */
    public CitaMock(Date pFecha) 
    {
        fecha = pFecha;
        duracionMins = 0.0;
        completada = false;
        hayCita = true;
    }
    /**
    * obtiene la fecha para la cual esta programada la cita
    * @returnfecha
    */
    public Date getFecha()
    {
        return fecha;
    }
    
    /**
     * obtiene la duracion que tuvo la cita
     * @return duracionMins
     */
    public double getDuracionMins()
    {
        return duracionMins;
    }
    
    /**
     * informa si una cita ya fue completada o no
     * @return true si ya fue completada, false de lo contrario
     */
    public boolean getFueCompletada()
    {
        return completada;
    }
    
    /**
     * informa si hay una cita
     * @return true si hay una cita, false de lo contrario
     */
    public boolean getHayCita()
    {
        return hayCita;
    }
    /**
     * cambia la fecha de una cita
     * @param pNuevaFecha  nueva fecha para la cita
     */
    public void setFecha(Date pNuevaFecha)
    {
        fecha = pNuevaFecha;
    }
    
    /**
     * establece la duracion de una cita
     * @param pDuracion 
     */
    public void setDuracionMins(double pDuracion)
    {
        duracionMins = pDuracion;
    }
    
    public void setFueCompletada(boolean pCompletada)
    {
        completada = pCompletada;
    }
    
    public void setHayCita(boolean pHayCita)
    {
        hayCita = pHayCita;
    }
}
