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
	
	/*
	 * descripcion de la especializacion
	 */
	private String descripcion;
        
        public EspecializacionDTO(){}
	
	public EspecializacionDTO(int id, String nombre, String descripcion)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	public String getDescripcion()
	{
		return descripcion;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
		
}
