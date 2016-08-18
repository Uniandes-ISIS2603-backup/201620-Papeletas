/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

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
     * Enumeración que representa los meses del year
     */
    public enum meses{ENERO, FEBRERO, FEBRERO_B, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE};
    
    //------------------------------------------------
    // Atributos
    //------------------------------------------------
    /**
     * Año del horario
     */
    private int year;
    
    /**
     * Mes del horario
     */
    private meses month;
    
    /**
     * Arreglo de días del horario
     */
    private DiaDTO[] dias;
    
    //------------------------------------------------
    // Constructores
    //------------------------------------------------
    /**
     * Constructor de la clase, incializa el arreglo de días y los días dependiendo el mes en que el horario es creado
     */
    public HorarioDTO(){
        year = 1997;
        month = meses.ENERO;
        dias = new DiaDTO[31];
        for (int i = 0; i < dias.length; i++) {
            dias[i]= new DiaDTO();
        }
    }
    /**
     * Constructor de la clase, incializa el arreglo de días y los días dependiendo el month
     * @param pMes El month del horario
     * @param pAño El year del horario
     */
    public HorarioDTO(meses pMes, int pAño){ 
        year = pAño;
        month = pMes;
        int d = 31;
        if(pMes == meses.FEBRERO)
            d = 28;
        else if(pMes == meses.FEBRERO_B)
            d = 29;
        else if(pMes == meses.ABRIL || pMes == meses.JUNIO || pMes == meses.SEPTIEMBRE || pMes == meses.NOVIEMBRE)
            d = 30;
        dias = new DiaDTO[d];  
        
        for (int i = 0; i < dias.length; i++) {
            dias[i]= new DiaDTO();
        }
    }
    
    //------------------------------------------------
    // Metodos
    //------------------------------------------------
    /**
     * @return the dias
     */
    public DiaDTO[] getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(DiaDTO[] dias) {
        this.dias = dias;
    }

    /**
     * @return the month
     */
    public meses getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(meses month) {
        this.month = month;
    }
    
    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    
    public String toString() {
        return "Año: " + year + "Mes: " + month + "Dias: " + dias.length;
    }
    
    
}
