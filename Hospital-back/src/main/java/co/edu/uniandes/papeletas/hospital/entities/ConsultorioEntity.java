/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jf.mendez11
 */
@Entity
public class ConsultorioEntity extends BaseEntity implements Serializable {
    
    public static int MAX_NUM_CONSULTORIOS = 23;
    
    private Integer numero;
    
    public Integer getNumero () {
        return numero;
    }
    
    public void setNumero (Integer numero) {
        this.numero = numero;
    }
}
