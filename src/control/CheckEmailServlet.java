package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.*;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet("/CheckEmail")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");*/
		
		UserDao dao = new UserDao();
		String email = request.getParameter("em");
		
		ArrayList<UserBean> users;
		try {
			users = dao.doRetrieveAll(null);
			for(UserBean user: users) {
				if(user.getEmail().equals(email)){
					//String json = new Gson().toJson("not valid");
					//response.getWriter().write(json);
					response.getWriter().write("0");
					return;
				}
		}
			//String json = new Gson().toJson("valid");
			response.getWriter().write("1");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
