package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import model.Filme;
import model.Genero;

public class InitBD {

	public InitBD() {
		super();
	}

	public static void main(String[] args) {
		try {
			insereDados();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insereDados() throws IOException, ClassNotFoundException, SQLException {
		File f = new File("F:\\movies.txt");
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String linha = br.readLine();

		GeneroDao genDao = new GeneroDao();
		
		while (linha != null) {
			String[] colunas = linha.split(";");
			String[] generos = colunas[2].split(",");
			if (generos.length > 1) {
				for (String s : generos) {
					Genero g = new Genero();
					g.setTipoGenero(s.trim());
					g = genDao.consultaGeneroPorTipo(g);
					if (g.getCodGenero() > 0) {
						Filme filme = new Filme();
						filme.setIdFilme(Integer.parseInt(colunas[0]));
						filme.setNomeFilme(colunas[1]);
						filme.setGeneroFilme(g);
						
						FilmeDao fDao = new FilmeDao();
						fDao.insereFilme(filme);
					}
				}
			} else {
				if (generos.length == 1) {
					Genero g = new Genero();
					g.setTipoGenero(colunas[2]);
					g = genDao.consultaGeneroPorTipo(g);
					if (g.getCodGenero() > 0) {
						Filme filme = new Filme();
						filme.setIdFilme(Integer.parseInt(colunas[0]));
						filme.setNomeFilme(colunas[1]);
						filme.setGeneroFilme(g);
						
						FilmeDao fDao = new FilmeDao();
						fDao.insereFilme(filme);
					}					
				}
			}
			linha = br.readLine();
		}
		br.close();
		isr.close();
		fis.close();
	}

}
