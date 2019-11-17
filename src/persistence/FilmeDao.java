package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Filme;
import model.Genero;

public class FilmeDao {
	
	private Connection c;

	public FilmeDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void insereFilme(Filme f) throws SQLException{
		String sql = "INSERT INTO filme VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getIdFilme());
		ps.setString(2, f.getNomeFilme());
		ps.setInt(3, f.getGeneroFilme().getCodGenero());
		ps.execute();
		ps.close();
	}

	public void deleteFilme(Filme f) throws SQLException{
		String sql = "DELETE filme WHERE idFilme = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getIdFilme());
		ps.execute();
		ps.close();
	}

	public void atualizaFilme(Filme f) throws SQLException{
		String sql = "UPDATE filme SET nomeFilme = ?, genero = ? WHERE idFilme = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, f.getNomeFilme());
		ps.setInt(2, f.getGeneroFilme().getCodGenero());
		ps.setInt(3, f.getIdFilme());

		ps.execute();
		ps.close();
	}

	public List<Filme> consultaFilmes() throws SQLException{
		List<Filme> listaFilme = new ArrayList<Filme>();
		
		String sql = "SELECT f.idFilme, f.nomeFilme, g.codGenero, g.tipoGenero FROM filme f INNER JOIN genero g ON f.genero = g.codGenero";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Filme filme = new Filme();
			filme.setIdFilme(rs.getInt("idFilme"));
			filme.setNomeFilme(rs.getString("nomeFilme"));
			
			Genero genero = new Genero();
			genero.setCodGenero(rs.getInt("codGenero"));
			genero.setTipoGenero(rs.getString("tipoGenero"));
			
			filme.setGeneroFilme(genero);
			
			listaFilme.add(filme);
		}
		rs.close();
		ps.close();
		return listaFilme;
	} 

	
	public List<Filme> consultaFilmesPorId(Filme f) throws SQLException{
		List<Filme> listaFilme = new ArrayList<Filme>();
		
		String sql = "SELECT f.idFilme, f.nomeFilme, g.codGenero, g.tipoGenero FROM filme f INNER JOIN genero g ON f.genero = g.codGenero WHERE f.idFilme = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getIdFilme());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Filme filme = new Filme();
			filme.setIdFilme(rs.getInt("idFilme"));
			filme.setNomeFilme(rs.getString("nomeFilme"));
			
			Genero genero = new Genero();
			genero.setCodGenero(rs.getInt("codGenero"));
			genero.setTipoGenero(rs.getString("tipoGenero"));
			
			filme.setGeneroFilme(genero);
			
			listaFilme.add(filme);
		}
		rs.close();
		ps.close();
		return listaFilme;
	} 
	
	public Filme consultaFilmesPorIdGenero(Filme f) throws SQLException{
		Filme filme = new Filme();
		
		String sql = "SELECT f.idFilme, f.nomeFilme, g.codGenero, g.tipoGenero FROM filme f INNER JOIN genero g ON f.genero = g.codGenero WHERE f.idFilme = ? AND g.codGenero = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, f.getIdFilme());
		ps.setInt(2, f.getGeneroFilme().getCodGenero());
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			filme.setIdFilme(rs.getInt("idFilme"));
			filme.setNomeFilme(rs.getString("nomeFilme"));
			
			Genero genero = new Genero();
			genero.setCodGenero(rs.getInt("codGenero"));
			genero.setTipoGenero(rs.getString("tipoGenero"));
			
			filme.setGeneroFilme(genero);
			
		}
		rs.close();
		ps.close();
		return filme;
	} 

}
