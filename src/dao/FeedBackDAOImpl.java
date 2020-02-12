package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;

public class FeedBackDAOImpl implements FeedbackDAO {

	private Connection conn;

	public FeedBackDAOImpl() throws ConnessioneException {
		conn = SingletonConnection.getInstance();
	}

	/*
	 * inserimento di un singolo feedbak relativo ad una edizione di un corso da
	 * aprte di un utente se un utente ha già inserito un feedback per una certa
	 * edizione si solleva una eccezione
	 */
	@Override
	public void insert(Feedback feedback) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO feedback (id_feedback, id_edizione, id_utente, descrizione, voto)");

		ps.setString(1, feedback.getIdUtente());
		ps.setInt(2, feedback.getIdFeedback());
		ps.setInt(3, feedback.getIdEdizione());
		ps.setString(4, feedback.getDescrizione());
		ps.setInt(6, feedback.getVoto());
		ps.executeUpdate();
	}

	/*
	 * modifica di tutti i dati di un singolo feedback un feedback viene individuato
	 * attraverso l'idFeedback se il feedback non esiste si solleva una eccezione
	 */
	@Override
	public void update(Feedback feedback) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE feedback SET id_feedback =?, id_edizione= ?, id_utente=?, descrizione=?, voto=?");

		ps.setInt(1, feedback.getIdFeedback());
		ps.setInt(2, feedback.getIdEdizione());
		ps.setString(3, feedback.getIdUtente());
		ps.setString(4, feedback.getDescrizione());
		ps.setInt(6, feedback.getVoto());
		int n = ps.executeUpdate();
		if (n == 0)
			throw new SQLException("feedback: " + feedback.getIdUtente() + " non presente");
	}

	/*
	 * cancellazione di un feedback se il feedback non esiste si solleva una
	 * eccezione
	 */
	@Override
	public void delete(int idFeedback) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM feedback WHERE(id_feedback =?, id_edizione= ?, id_utente=?, descrizione=?, voto=?)");
		ps.setInt(1, idFeedback);
		int n = ps.executeUpdate();
		if (n == 0)
			throw new SQLException("feedback: " + idFeedback + " non presente");
	}

	/*
	 * lettura di un singolo feedback scritto da un utente per una certa edizione se
	 * il feedback non esiste si solleva una eccezione
	 */
	@Override 
	public Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback WHERE id_utente = ? AND id_edizione = ?");
		ps.setString(1, idUtente);
		ps.setInt(2, idEdizione);
		ResultSet rs = ps.executeQuery();
		Feedback feed = null;
		if (rs.next()) {
			idUtente = rs.getString("id_utente");
			idEdizione = rs.getInt("id_edizione");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			feed = new Feedback(idEdizione, idUtente, descrizione, voto);
			return feed;

		} else {
			throw new SQLException("feedback di " + idUtente + "di " + idEdizione + " non presente");

		}
	}

	/*
	 * lettura di tutti i feedback di una certa edizione se non ci sono feedback o
	 * l'edizione non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException {

		ArrayList<Feedback> feedback = new ArrayList<Feedback>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback WHERE id_edizione = ?");

		ResultSet rs = ps.executeQuery();
		if (rs == null || idEdizione == 0) {
			return feedback;
		}
		while (rs.next()) {
			String idUtente = rs.getString("id_utente");
			idEdizione = rs.getInt("id_edizione");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			Feedback feed = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedback.add(feed);
		}
		return feedback;
	}

	/*
	 * lettura di tutti i feedback scritti da un certo utente se non ci sono
	 * feedback o l'utente non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException {
		ArrayList<Feedback> feedback = new ArrayList<Feedback>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback WHERE id_utente = ?");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			idUtente = rs.getString("id_utente");
			int idEdizione = rs.getInt("id_edizione");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			Feedback feed = new Feedback(idEdizione, idUtente, descrizione, voto);
			feedback.add(feed);
		}
		return feedback;
	}

	/*
	 * lettura di tutti i feedback scritti per un certo corso (nota: non edizione ma
	 * corso) se non ci sono feedback o il corso non esiste si torna una lista vuota
	 */
	@Override
	public ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException {

		ArrayList<Feedback> feedback = new ArrayList<Feedback>();

		PreparedStatement ps = conn.prepareStatement(
				"SELECT id_corso, titolo, f.descrizione, voto FROM CATALOGO JOIN calendario using ( id_corso) join feedback f using (id_edizione)");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			idCorso = rs.getInt("id_corso");
			String titolo = rs.getString("titolo");
			String descrizione = rs.getString("descrizione");
			int voto = rs.getInt("voto");

			Feedback feed = new Feedback(idCorso, titolo, descrizione, voto);
			feedback.add(feed);
		}
		return feedback;
	}

	

}
