package controller.resources;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

public interface IFilmesResources {

	public Response buscarFilmes(@HeaderParam("token") long token);
	public Response cadastraFilme(String filmeJson, @HeaderParam("token") long token);

}
