package co.edu.uniandes.rest.hospital.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.edu.uniandes.rest.hospital.mocks.EspecializacionMock;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import co.edu.uniandes.rest.hospital.exceptions.EspecializacionException;
import javax.ws.rs.QueryParam;



/**
 * 
 * @author Juan Camilo Lara
 *
 */

@Path("especializacion")
@Produces("application/json")
public class EspecializacionResource
{
    /*
    * Especializacion mock
    */
    private EspecializacionMock Especialidad = new EspecializacionMock();   
    @GET
	public List<EspecializacionDTO> getEspecializaciones() throws EspecializacionException
	{
		return Especialidad.getSpecialties();
	}
	
    @GET
    @Path("{id: \\d+}")
    public EspecializacionDTO getEspecializacionID(@PathParam("id")int id) throws EspecializacionException
    {
        return Especialidad.getSpecID(id);
    }
	
    @POST
    public EspecializacionDTO createEspecialidad(EspecializacionDTO spec)throws EspecializacionException
    {
     return Especialidad.createSpecialty(spec);
    }
    
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteEspecializacion (@PathParam("id") int id) throws EspecializacionException
    {
        Especialidad.deleteEspecializacion(id);
    }
    @PUT
    @Path ("{id: \\d+}")
    public EspecializacionDTO updateEspecializacion (@PathParam("id") int id, EspecializacionDTO spec) throws EspecializacionException
    {
        return Especialidad.updateEspecializacion(id, spec);
    }
}
