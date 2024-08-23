package jsp_employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteEmployee extends HttpServlet{
	//if you are using anchor tag override doget method,else dopost
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		EmployeeCRUD crud = new EmployeeCRUD();
		
		try {
			int result = crud.deleteEmployee(id);
			if(result !=0) {
				req.setAttribute("list", crud.displayDetails());
				RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
				dispatcher.forward(req, resp);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
