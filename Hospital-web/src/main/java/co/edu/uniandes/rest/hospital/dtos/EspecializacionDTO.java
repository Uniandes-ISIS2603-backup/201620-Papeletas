package co.edu.uniandes.rest.hospital.dtos;

import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Juan Camilo Lara
 *
 */
@XmlRootElement
public class EspecializacionDTO
{
	
	/*
	 * identificador de la especializacion
	 */
	private Long id;
	
	/*
	 * nombre de la especializacion
	 */
	private String nombre;
        
        
        private double promedio;
	
        
        public EspecializacionDTO(){}
	
	public EspecializacionDTO(EspecializacionEntity entity)
	{
            if (entity != null)
            {
                this.nombre = entity.getName();
                this.id = entity.getId();
                promedio = 0.0;
            }
	}
        
        public EspecializacionEntity toEntity ()
        {
            EspecializacionEntity entity = new EspecializacionEntity();
            entity.setId((long)this.getId());
            entity.setName(this.getNombre());
            return entity;
    }

	/*
	 * @return id
	 */
	public int getId()
	{
		return id.intValue();
	}
	
	/*
	 * @return nombre
	 */
	public String getNombre()
	{
		return nombre;
	}
	
	/*
	 * @return descripcion
	 */

	public void setId(int id)
	{
		this.id = (long)id;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
        
        
        public double getPromedio()
        {
            return promedio;
        }
        
        public void setPromedio(double p)
        {
            promedio+=p;
        }
        @Override
        public String toString()
        {
    	return "{ id : " + getId() + ", name : \"" + getNombre() + "\" }" ;  
        }
		
}
