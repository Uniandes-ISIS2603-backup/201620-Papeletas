package co.edu.uniandes.rest.hospital.resources;

import co.edu.uniandes.papeletas.hospital.api.IEspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.ejbs.EspecializacionLogic;
import co.edu.uniandes.papeletas.hospital.entities.EspecializacionEntity;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

//import co.edu.uniandes.rest.hospital.mocks.EspecializacionMock;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDetailDTO;
import co.edu.uniandes.rest.hospital.exceptions.EspecializacionException;
import javax.inject.Inject;
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

    @Inject
    private IEspecializacionLogic especialidad;
    
    @GET
    @Path("{id: \\d+}")
    public EspecializacionDetailDTO getEspecializacionID(@PathParam("id")int id) throws EspecializacionException
    {
        EspecializacionEntity ent = especialidad.getEspecializacion((long)id);
        return new EspecializacionDetailDTO(ent);
    }
	
    @POST
    public EspecializacionDetailDTO createEspecialidad(EspecializacionDetailDTO spec)throws EspecializacionException
    {
     return new EspecializacionDetailDTO(spec.toEntity());
    }
    
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteEspecializacion (@PathParam("id") int id) throws EspecializacionException
    {
        especialidad.deleteEspecializacion((long)id);
    }
    @PUT
    @Path ("{id: \\d+}")
    public EspecializacionDetailDTO updateEspecializacion (@PathParam("id") int id, EspecializacionDetailDTO spec) throws EspecializacionException
    {
        EspecializacionEntity entity = spec.toEntity();
        entity.setId((long)id);
        EspecializacionEntity oldEntity = especialidad.getEspecializacion((long)id);
        return new EspecializacionDetailDTO(entity);
    }
}
