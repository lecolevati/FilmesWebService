package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Genero;

public class GeneroDao {

	private Connection c;

	public GeneroDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public Genero consultaGeneroPorTipo(Genero g) throws SQLException {
		String sql = "SELECT codGenero, tipoGenero FROM genero WHERE tipoGenero = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, g.getTipoGenero());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			g.setCodGenero(rs.getInt("codGenero"));
			g.setTipoGenero(rs.getString("TipoGenero"));
		}
		rs.close();
		ps.close();
		return g;
	}

}
