/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

/**
 *
 * @author Nicolas
 */
public class ValorDTO {

    double promedio;

    public ValorDTO() {
    }
    
    public ValorDTO(double valor) {
        this.promedio = valor;
    }

    public double getValor() {
        return promedio;
    }

    public void setValor(double valor) {
        this.promedio = valor;
    }
    
    
    
   
    
}
