package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SingletonConnection;
import entity.Corso;
import exceptions.ConnessioneException;

@WebServlet("/corsi")
public class CorsiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Corso> corsi = new ArrayList<>();
		try {
			Connection conn = SingletonConnection.getInstance();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select titolo, Durata, numeroMaxPartecipanti, costo, descrizione from catalogo join calendario using(id_corso)");

			while (rs.next()) {
				corsi.add(new Corso(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5)));

			}
			request.setAttribute("courses", corsi);
		} catch (SQLException | ConnessioneException e) {
			// ...
		}
		 request.getRequestDispatcher("WEB-INF/jsp/corsi2.jsp").forward(request, response);
	}

	public CorsiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
