package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CatalogoDAO;
import dao.CatalogoDAOImpl;
import dao.FeedBackDAOImpl;
import dao.FeedbackDAO;
import dao.RegistrazioneUtenteDAO;
import dao.RegistrazioneUtenteDAOImpl;
import entity.Feedback;
import entity.Utente;
import exceptions.ConnessioneException;
import exceptions.DAOException;

public class UtenteServiceImpl implements UtenteService {

	// dichiarare qui tutti i dao di cui si ha bisogno
	private RegistrazioneUtenteDAO daoU;
	private FeedbackDAO daoF;
	// ... dichiarazione di altri eventuali DAO

	// costruire qui tutti i dao di cui si ha bisogno
	public UtenteServiceImpl() throws ConnessioneException {
		daoU = new RegistrazioneUtenteDAOImpl();
		daoF = new FeedBackDAOImpl();
		// ... costruzione dei altri eventuali dao
	}

	/*
	 * registrazione nel sistema di un nuovo utente Se l'utente � gi� presente si
	 * solleva una eccezione
	 */
	@Override
	public void registrazioneUtente(Utente u) throws DAOException {
		try {
			daoU.insert(u);
		} catch (SQLException e) {
			throw new DAOException("impossibile inserire l'utente", e);
		}

	}

	/**
	 * controllo della presenza di un utente in base a idUtente e password Se
	 * l'utente � presente viene recuperato e ritornato Se l'utente non � presente
	 * (o la password � errata) si solleva una eccezione
	 */
	@Override
	public Utente checkCredenziali(String idUtente, String psw) throws DAOException {
		try {
			Utente u = daoU.select(idUtente);
			if (!u.getPassword().equals(psw)) {
				return u;
			}
			throw new DAOException("Password errata");
		} catch (SQLException e) {
			throw new DAOException("Utente non registrato", e);
		}
	}

	/*
	 * cancellazione di un utente dal sistema l'utente � cancellabile solo se non vi
	 * sono dati correlati. (ad esempio, non � (o � stato) iscritto a nessuna
	 * edizione) se l'utente non � cancellabile si solleva una eccezione
	 * 
	 */
	@Override
	public void cancellaRegistrazioneUtente(String idUtente) throws DAOException {
		// TODO Auto-generated method stub
		try {
			daoU.delete(idUtente);
		} catch (SQLException e) {
			throw new DAOException("Impossibile cancellare utente: utente non registrato", e);
		}
	}

	/*
	 * modifica tutti i dati di un utente l'utente viene individuato in base a
	 * idUtente se l'utente non � presente si solleva una eccezione
	 */
	@Override
	public void modificaDatiUtente(Utente u) throws DAOException {
		// TODO Auto-generated method stub
		try {
			daoU.update(u);
		} catch (SQLException e) {
			throw new DAOException("Impossibile modificare utente: utente non registrato", e);
		}

	}

	/*
	 * legge tutti gli utenti registrati sul sistema se non vi sono utenti il metodo
	 * ritorna una lista vuota
	 */
	@Override
	public ArrayList<Utente> visualizzaUtentiRegistrati() throws DAOException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Utente> result = daoU.select();
			return result;
		} catch (SQLException e) {
			throw new DAOException("Utenti non presenti", e);
		}

	}

	/*
	 * inserisce un feedback per una certa edizione Un utente pu� inserire un
	 * feedback solo per i corsi gi� frequentati (e terminati) e solo se non lo ha
	 * gi� fatto in precedenza (un solo feedback ad utente per edizione) se l'utente
	 * non pu� inserire un feedback si solleva una eccezione
	 */
	@Override
	public void inserisciFeedback(Feedback f) throws DAOException {
		// TODO Auto-generated method stub
		try {
			daoF.insert(f);
		} catch (SQLException e) {
			throw new DAOException("Feedback gi� inserito", e);
		}

	}

	/*
	 * modifica della descrizione e/o del voto di un feedback il feedback �
	 * modificabile solo da parte dell'utente che lo ha inserito e solo entro un
	 * mese dal termine della edizione del corso se l'utente non pu� modificare un
	 * feedback si solleva una eccezione
	 */
	/** @Override
	public void modificaFeedback(Feedback feedback) throws DAOException {
		// TODO Auto-generated method stub
		try {
			if (... && daoF.selectEndDate(rs.getIdCorso()) {
		
				daoF.update(feedback.getVoto());
		  		daoF.update(feedback.getDescrizione());
			} else {
				throw new DAOException("Impossibile modificare");
			
		} catch (SQLException e) {
			throw new DAOException("", e);

		}

	} **/

	/*
	 * eliminazione di un feedback il feedback � cancellabile solo da parte
	 * dell'utente che lo ha inserito e solo entro un mese dal termine della
	 * edizione del corso se l'utente non pu� cancellare un feedback si solleva una
	 * eccezione
	 */
	@Override
	public void cancellaFeedback(int idFeedback, int IdCorso) throws DAOException {
		// TODO Auto-generated method stub
		try {
			if(daoF.selectEndDate(IdCorso)) {
				daoF.delete(idFeedback);
			} else {
				throw new DAOException("Fuori tempo massimo");
			}
		} catch (SQLException e) {
			throw new DAOException("", e);
		}

	}

	@Override
	public void modificaFeedback(Feedback feedback) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancellaFeedback(int idFeedback) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
