package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserBean;
import model.UserDao;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        try {
            String username = request.getParameter("un");
            String password = request.getParameter("pw");

            // Verifica password senza crittografia
            UserBean userNonCrittografato = userDao.doRetrieveByUsernameAndPasswordNonCrittografiata(username, password);

            if (userNonCrittografato.isValid()) {
                // Se la password corrisponde senza crittografia
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userNonCrittografato);
                redirectToHomeOrCheckout(request, response);
                return; // Importante per uscire dal metodo dopo il redirect
            }

            // Se la password non corrisponde senza crittografia, verifica con crittografia
            String hashedPassword = UserDao.hashPassword(password);
            UserBean userCrittografato = userDao.doRetrieve(username, hashedPassword);

            if (userCrittografato.isValid()) {
                // Se la password corrisponde con crittografia
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userCrittografato);
                redirectToHomeOrCheckout(request, response);
            } else {
                // Nessun utente trovato con username/password corrispondenti
                response.sendRedirect(request.getContextPath() + "/Login.jsp?action=error");
            }

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private void redirectToHomeOrCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkout = request.getParameter("checkout");
        if (checkout != null) {
            response.sendRedirect(request.getContextPath() + "/account?page=Checkout.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        }
    }
}
