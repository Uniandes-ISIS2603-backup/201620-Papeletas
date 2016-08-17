/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.dtos;

/**
 *
 * @author jf.mendez11
 */
public class ConsultorioDTO {
    
    /**
     * Número de cuartos de hora en un día
     */
    public static final int NUM_CUARTOS_DE_HORA_DIA = 96;
    
    /**
     * Id del consultorio
     */
    private Long id;
    /**
     * Indica si el consultorio está libre
     */
    private boolean libre;
    /**
     * Disponibilidad del consultorio
     */
    private boolean [] disponibilidad;
    
    /**
     * Constructor de la clase
     */
    public ConsultorioDTO () {
    }
    
    /**
     * Constructor de la clase
     * @param id id del consultorio
     * @param libre si el consultorio está libre o no.
     */
    public ConsultorioDTO (Long id, boolean libre)
    {
        super();
        this.id = id;
        this.libre = libre;
        this.disponibilidad = new boolean [NUM_CUARTOS_DE_HORA_DIA];
        //Por defecto, el consultorio siempre está disponible
        for (int i = 0; i < NUM_CUARTOS_DE_HORA_DIA; i++)
            disponibilidad[i] = false;
    }
    
    /**
     * Retorna el id del consultorio
     * @return id del consultorio
     */
    public Long getId ()
    {
        return id;
    }
    
    /**
     * Modifica el id del consultorio
     * @param id id nuevo
     */
    public void setId (Long id)
    {
        this.id = id;
    }
    
    /**
     * Indica si el consultorio está libre
     * @return true si el consultorio está libre, false de lo contrario
     */
    public boolean isLibre ()
    {
        return libre;
    }
    
    /**
     * Cambia el estado de libre del consultorio
     * @param libre nuevo estado del consultorio
     */
    public void setLibre (boolean libre)
    {
        this.libre = libre;
    }
    
    /**
     * Retorna un arreglo con la disponibilidad del consultorio
     * @return el arreglo de la disponibilidad del consultorio
     */
    public boolean [] getDisponibilidad ()
    {
        return disponibilidad;
    }
    
    public void setDisponibilidad (boolean [] disponibilidad)
    {
        this.disponibilidad = disponibilidad;
    }
    
    /**
     * Modifica la disponibilidad del consultorio en el indice de la hora especificada
     * @param i inidce de la hora especificada.
     */
    public void changeDisponibilidad (int i)
    {
        if (disponibilidad [i])
            disponibilidad [i] = false;
        else
            disponibilidad [i] = true;
    }
}
