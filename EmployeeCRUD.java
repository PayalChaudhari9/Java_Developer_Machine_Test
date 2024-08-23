package jsp_employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp?user =root & password = root");

	}

	public int signUp(Employee employee) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO EMPLOYEE (id, name, phone, email, password, designation, salary)VALUES (?,?,?,?,?,?,?)");

		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setLong(3, employee.getPhone());
		preparedStatement.setString(4, employee.getEmail());
		preparedStatement.setString(5, employee.getPassword());
		preparedStatement.setString(6, employee.getDesignation());
		preparedStatement.setDouble(7, employee.getSalary());

		int result = preparedStatement.executeUpdate();
		return result;

	}

	public String getPassword(String email) throws ClassNotFoundException, SQLException  {

		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection
				.prepareStatement("Select password from employee where email =?");

		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		String password = null;

		while (resultSet.next()) {
			password = resultSet.getString("password");

		}

		connection.close();
		return password;

	}

	public List<Employee> displayDetails() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("Select * from employee");

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Employee> list = new ArrayList<Employee>();

		while (resultSet.next()) {
			Employee employee = new Employee();

			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setPhone(resultSet.getLong("phone"));
			employee.setEmail(resultSet.getString("email"));
			employee.setDesignation(resultSet.getString("designation"));
			employee.setPassword(resultSet.getString("password"));
			employee.setSalary(resultSet.getDouble("salary"));

			list.add(employee);

		}

		connection.close();
		return list;

	}

	public int deleteEmployee(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE ID = ?");

		preparedStatement.setInt(1, id);

		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}

	public Employee findEmployee(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID=?");
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		Employee employee = new Employee();

		while (resultSet.next()) {

			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setPhone(resultSet.getLong("phone"));
			employee.setEmail(resultSet.getString("email"));
			employee.setDesignation(resultSet.getString("designation"));
			employee.setPassword(resultSet.getString("password"));
			employee.setSalary(resultSet.getDouble("salary"));

		}

		connection.close();
		return employee;
	}

	public int updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE EMPLOYEE SET ID=?,NAME=?,PHONE=?,EMAIL=?,DESIGNATION=?,SALARY=?,PASSWORD=? WHERE ID=?");

		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setLong(3, employee.getPhone());
		preparedStatement.setString(4, employee.getEmail());
		preparedStatement.setString(7, employee.getPassword());
		preparedStatement.setString(5, employee.getDesignation());
		preparedStatement.setDouble(6, employee.getSalary());
		preparedStatement.setInt(8, employee.getId());

		int count = preparedStatement.executeUpdate();

		connection.close();
		return count;
	}

}
