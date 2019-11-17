package controller.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import model.Filme;
import persistence.FilmeDao;

@Path("/filmes")
public class FilmesResource implements IFilmesResources {

	private String listaFilmesToJson(List<Filme> filmes) {
		Gson gson = new Gson();
		String listaJson = gson.toJson(filmes);
		return listaJson;
	}

	private boolean validaToken(long token) {
		if (token == 1234) {
			return true;
		} else {
			return false;
		}
	}

}