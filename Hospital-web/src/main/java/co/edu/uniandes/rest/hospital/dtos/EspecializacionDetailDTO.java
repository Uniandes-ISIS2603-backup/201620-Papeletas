/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jc.lara10
 */
@XmlRootElement
public class EspecializacionDetailDTO extends EspecializacionDTO
{
    public EspecializacionDetailDTO ()
    {
        super ();
    }
    
    public EspecializacionDetailDTO (EspecializacionEntity entity)
    {
        super(entity);
    }
    
    @Override
    public EspecializacionEntity toEntity ()
    {
       EspecializacionEntity entity = super.toEntity();
       return entity; 
    }
}
