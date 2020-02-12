package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entity.Edizione;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;

public class IscrizioneUtenteDAOImpl implements IscrizioneUtenteDAO {

	private Connection conn;

	public IscrizioneUtenteDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/*
	 * iscrizione di un certo utente ad una certa edizione di un corso. sia l'utente
	 * che l'edizione devono già essere stati registrati in precedenza se l'utente
	 * e/o l'edizione non esistono o l'utente è già iscritto a quella edizione si
	 * solleva una eccezione
	 */
	@Override
	public void iscriviUtente(int idEdizione, String idUtente) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("INSERT INTO iscritti (id_edizione,id_utente) VALUES (?,?)");
		ps.setInt(1, idEdizione);
		ps.setString(2, idUtente);
		ps.executeUpdate();

	}

	/*
	 * cancellazione di una iscrizione ad una edizione nota: quando si cancella
	 * l'iscrizione, sia l'utente che l'edizione non devono essere cancellati se
	 * l'utente e/o l'edizione non esistono si solleva una eccezione
	 */
	@Override
	public void cancellaIscrizioneUtente(int idEdizione, String idUtente) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM iscritti WHERE id_utente=? AND id_edizione=?");
		ps.setInt(1, idEdizione);
		ps.setString(2, idUtente);
		
		ps.executeUpdate();
		if(idEdizione==0 && idUtente==null) {
			throw new SQLException("iscritto " + idUtente + " e edizione " + idEdizione + " non presenti");
		} else if (idEdizione ==0) {
			throw new SQLException("Edizione " + idEdizione + " non presente");
		} else if (idUtente==null)  {
			throw new SQLException("iscritto " + idUtente + " non presente");
		}

	}

	/*
	 * lettura di tutte le edizioni a cui è iscritto un utente se l'utente non
	 * esiste o non è iscritto a nessuna edizione si torna una lista vuota
	 */
	@Override
	public ArrayList<Edizione> selectIscrizioniUtente(String idUtente) throws SQLException {
		ArrayList<Edizione> edizioni = new ArrayList<Edizione>();

		PreparedStatement ps = conn.prepareStatement("SELECT id_edizione, id_corso, dataInizio, durata, aula, docente FROM calendario JOIN iscritti USING (id_edizione) WHERE id_utente = ?");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int idEdizione = rs.getInt("id_edizione");
			int idCorso = rs.getInt ("id_corso");
			Date dataInizio = rs.getDate("dataInizio");
			int durata = rs.getInt("durata");
			String aula = rs.getString("aula");
			String docente = rs.getString("docente");
			
			Edizione edizione = new Edizione(idEdizione, idCorso, dataInizio, durata, aula, docente);
			edizioni.add(edizione);
		}
		return edizioni;
	}

	/*
	 * lettura di tutti gli utenti iscritti ad una certa edizione se l'edizione non
	 * esiste o non vi sono utenti iscritti si torna una lista vuota
	 */
	@Override
	public ArrayList<Utente> selectUtentiPerEdizione(int idEdizione) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ritorna il numero di utenti iscritti ad una certa edizione
	 */
	@Override
	public int getNumeroIscritti(int idEdizione) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
