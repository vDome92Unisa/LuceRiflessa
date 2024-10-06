package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdottoDao dao = new ProdottoDao();
		
		ArrayList<ArrayList<ProdottoBean>> categorie = new ArrayList<>();
		String redirectedPage = request.getParameter("page");
		
		try {
			ArrayList<ProdottoBean> Collane = dao.doRetrieveByCategoria("Collane");
			ArrayList<ProdottoBean> Bracciali = dao.doRetrieveByCategoria("Bracciali");
			ArrayList<ProdottoBean> Anelli = dao.doRetrieveByCategoria("Anelli");
			ArrayList<ProdottoBean> Pendenti = dao.doRetrieveByCategoria("Pendenti");
			ArrayList<ProdottoBean> Orecchini = dao.doRetrieveByCategoria("Orecchini");
			
			categorie.add(Collane);
			categorie.add(Bracciali);
			categorie.add(Anelli);
			categorie.add(Pendenti);
			categorie.add(Orecchini);

			request.getSession().setAttribute("categorie", categorie);
			

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + redirectedPage);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
