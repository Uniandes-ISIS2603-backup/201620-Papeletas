/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.ConsultorioEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jf.mendez11
 */
@XmlRootElement
public class ConsultorioDetailDTO extends ConsultorioDTO {
    
    public ConsultorioDetailDTO () {
        super ();
    }
    
    public ConsultorioDetailDTO (ConsultorioEntity entity) {
        super(entity);
    }
    
    @Override
    public ConsultorioEntity toEntity () {
        ConsultorioEntity entity = super.toEntity();
       return entity; 
    }
}
