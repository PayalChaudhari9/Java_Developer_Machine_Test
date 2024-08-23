package jsp_employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String designation = req.getParameter("designation");
		double salary = Double.parseDouble(req.getParameter("salary"));
		
		Employee employee = new Employee();
		
		employee.setId(id);
		employee.setName(name);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setDesignation(designation);
		employee.setSalary(salary);
		
		EmployeeCRUD crud = new EmployeeCRUD();
		
		try
		{
			
			int count = crud.signUp(employee);
			
			if(count!=0)
			{
				req.setAttribute("message", "SignUp success...... Please Login !!!");
			
				
				 RequestDispatcher dispatcher =req.getRequestDispatcher("login.jsp");
				 dispatcher.forward(req, resp);
			}
			
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();  //it will prints all the exceptions / errors on browser
		
		}
		
		
	}
	
	

}
