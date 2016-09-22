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
        
        
        private double promedio;
	
        
        public EspecializacionDTO(){}
	
	public EspecializacionDTO(int id, String nombre)
	{
		super();
		this.id = id;
		this.nombre = nombre;
                promedio=0.0;
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
