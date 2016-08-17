/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.dtos;

import java.util.ArrayList;

/**
 * Clase que representa un horario
 * @author ac.cabezas716
 */
public class HorarioDTO {
    
    //------------------------------------------------
    // Enum
    //------------------------------------------------
    /**
     * Enumeración que representa los meses del año
     */
    public enum meses{ENERO, FEBRERO, FEBRERO_B, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE};
    
    //------------------------------------------------
    // Atributos
    //------------------------------------------------
    
    /**
     * Arreglo de días del horario
     */
    private ArrayList<DiaDTO> dias;
    
    /**
     * Mes del horario
     */
    private meses mes;
    
    /**
     * Año del horario
     */
    private int año;
    
    //------------------------------------------------
    // Constructores
    //------------------------------------------------
    /**
     * Constructor de la clase, incializa el arreglo de días y los días dependiendo el mes en que el horario es creado
     */
    public HorarioDTO(){
        dias = new ArrayList(30); 
    }
    
    /**
     * Constructor de la clase, incializa el arreglo de días y los días dependiendo el mes
     * @param pMes El mes del horario
     * @param pAño El año del horario
     */
    public HorarioDTO(meses pMes, int pAño){ 
        mes = pMes;
        año = pAño;
        int d = 31;
        if(pMes == meses.FEBRERO)
            d = 28;
        else if(pMes == meses.FEBRERO_B)
            d = 29;
        else if(pMes == meses.ABRIL || pMes == meses.JUNIO || pMes == meses.SEPTIEMBRE || pMes == meses.NOVIEMBRE)
            d = 30;
        dias = new ArrayList(d);   
    }
    
    //------------------------------------------------
    // Metodos
    //------------------------------------------------
    /**
     * @return the dias
     */
    public ArrayList<DiaDTO> getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(ArrayList<DiaDTO> dias) {
        this.dias = dias;
    }

    /**
     * @return the mes
     */
    public meses getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(meses mes) {
        this.mes = mes;
    }
    
    
    
}
