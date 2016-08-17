/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.dtos;

/**
 *
 * @author df.castro12
 */
public class PacienteDTO {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private int satisfaction;
    
    

    /**
     * Constructor por defecto
     */
    public PacienteDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del paciente
     * @param name nombre del paciente
     * @param lastName apellido del paciente
     * @param age edad del paciente
     * @param satisfaction nivel de satisfacción
     */
    public PacienteDTO(Long id, String name,String lastName, int age, int satisfaction) {
		super();
		this.id = id;
		this.name = name;
                this.lastName = lastName;
                this.age = age;
                this.satisfaction = satisfaction;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
     /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
     /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the name to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * @return the satisfaction
     */
    public int getSatisfaction() {
        return satisfaction;
    }

    /**
     * @param satisfaction the name to set
     */
    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\" , last name: \""+getLastName()+"\" , age :"+getAge()+", satisfaction : "+ getSatisfaction()+"}" ;  
    }
}
