package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import entity.Utente;

class AmministratoreDAOImplTest {

	@Test
	void insert() {
		AmministratoreDAO dao = null;

		try {
			dao = new AmministratoreDAOImpl();

			Utente u = new Utente("mario92", "password", "Mario", "Rossi", new java.util.Date(),
					"mario.rossi@gmail.com", "338 1234567", true);

//                 dao.insert(u);
//			
//			// u dovrebbe essere nel db!
//			
             Utente result = dao.select("mario92");
//			
//			assertEquals(u.getIdUtente(), result.getIdUtente());
//			assertEquals(u.getPassword(), result.getPassword());
//			assertEquals(u.getNome(), result.getNome());
//			assertEquals(u.getCognome(), result.getCognome());
//			assertEquals(u.getDataNascita(), result.getDataNascita());
//			assertEquals(u.getEmail(), result.getEmail());
//			assertEquals(u.getTelefono(), result.getTelefono());
//			
//			u.setCognome("Bianchi");
//			dao.update(u);
//			
//			result = dao.select("mario92");
//			assertEquals(u.getCognome(), result.getCognome());
			
			
//		
//			
//			dao.delete("mario92");
//			
		} catch (Exception ex) {
			fail("UEx" + ex.getMessage());
		}
		
//	try {
//		dao.select("mario92");
//			fail("Select should have failed here!");
//		} catch (SQLException e) {
//			//as expected
//		}		
		
	}
}
