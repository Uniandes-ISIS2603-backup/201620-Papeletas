package co.edu.uniandes.rest.hospital.dtos;

/**
 * 
 * @author Juan Camilo Lara
 *
 */

public class EspecializacionDTO
{
	
	/*
	 * identificador de la especializacion
	 */
	private int id;
	
	/*
	 * nombre de la especializacion
	 */
	private String nombre;
	
        
        public EspecializacionDTO(){}
	
	public EspecializacionDTO(int id, String nombre)
	{
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/*
	 * @return id
	 */
	public int getId()
	{
		return id;
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
		this.id = id;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
        @Override
        public String toString()
        {
    	return "{ id : " + getId() + ", name : \"" + getNombre() + "\" }" ;  
        }
		
}
