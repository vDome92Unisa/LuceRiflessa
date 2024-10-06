package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserDao;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String dataNascita = request.getParameter("nascita");
        String username = request.getParameter("us");
        String pwd = request.getParameter("pw");

        String[] parti = dataNascita.split("-");
        dataNascita = parti[2] + "-" + parti[1] + "-" + parti[0];

        try {
            // Crittografa la password
            String hashedPwd = hashPassword(pwd);

            UserBean user = new UserBean();
            user.setNome(nome);
            user.setCognome(cognome);
            user.setEmail(email);
            user.setDataDiNascita(Date.valueOf(dataNascita));
            user.setUsername(username);
            user.setPassword(hashedPwd); // Usa la password crittografata
            user.setAmministratore(false);
            user.setCap(null);
            user.setIndirizzo(null);
            user.setCartaDiCredito(null);
            dao.doSave(user);

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/Home.jsp");
    }


	
	public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

