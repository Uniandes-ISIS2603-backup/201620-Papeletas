/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

/**
 *
 * @author jf.mendez11
 */
public class ConsultorioDTO {
    
    /**
     * Id del consultorio
     */
    private Long id;
    
    /**
     * Número del consultorio
     */
    private Long numero;
    
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
    public ConsultorioDTO (Long id, Long numero)
    {
        super();
        this.id = id;
        this.numero = numero;
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
     * Indica el número del consultorio
     * @return el número del consultorio
     */
    public Long getNumero ()
    {
        return numero;
    }
    
    /**
     * Cambia el número del consultorio
     * @param numero nuevo estado del consultorio
     */
    public void setNumero (Long numero)
    {
        this.numero = numero;
    }
}
