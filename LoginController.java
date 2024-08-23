package jsp_employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		EmployeeCRUD crud = new EmployeeCRUD();

		try {

			 String passwordDB = crud.getPassword(email);

			if (passwordDB != null) {

				if (passwordDB.equals(password)) {
					
					//create cookie
					Cookie cookie = new Cookie("userId", email);
					resp.addCookie(cookie);
					
					//Creating HttpSession
					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("session", email);
					System.out.print(httpSession);
					
//					HttpSession httpSession1 = req.getSession();
//					System.out.print(httpSession1);   httpsession can creates once only
					
					req.setAttribute("msg", "Login successful");

					req.setAttribute("list", crud.displayDetails());

					RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
					dispatcher.forward(req, resp);

				} else {
					req.setAttribute("message", "Invalid password");

					RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
					dispatcher.forward(req, resp);

				}
			} else {

				req.setAttribute("message", "User not Register........... Please Register");

				RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
				dispatcher.forward(req, resp);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
