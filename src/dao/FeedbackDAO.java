package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Feedback;


public interface FeedbackDAO {
	
	void insert(Feedback feedback) throws SQLException;
	void update(Feedback feedback) throws SQLException;
	void delete(int idFeedback) throws SQLException;
	Feedback selectSingoloFeedback(String idUtente, int idEdizione) throws SQLException;
	ArrayList<Feedback> selectPerEdizione(int idEdizione) throws SQLException;
	ArrayList<Feedback> selectPerUtente(String idUtente) throws SQLException;
	ArrayList<Feedback> selectFeedbackPerCorso(int idCorso) throws SQLException;
	void deleteSingoloFeedback(int idFeedback, int idEdizione) throws SQLException;
	void updateSingoloFeedback(String descrizione, int voto, int idFeedback, int idEdizione) throws SQLException;
	

}