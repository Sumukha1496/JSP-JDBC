

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Main() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			try {
//				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDB", "root", "");
//				Statement stmt = con.createStatement();
//				stmt.executeUpdate("insert into Employees values(2, 'Abcd', 25, 21000)");
//				System.out.println("Inserted");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		RequestDispatcher view = request.getRequestDispatcher("mainpage.jsp");
		view.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("errors", false);
		Employee e = new Employee();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String  name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		
		if(name.length() == 0 || id == 0 || age == 0 || salary == 0) {
			System.out.println("Error");
		}
		else {
			e.setName(name);
			e.setAge(age);
			e.setId(id);
			e.setSalary(salary);
			
			e.toString();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDB", "root", "");
					Statement stmt = con.createStatement();
					String insert_query = "insert into Employees values("+e.getId()+", '" + e.getName()+"', "+ e.getAge() +", " + e.getSalary()+")";
					stmt.executeUpdate(insert_query);
					System.out.println("Inserted");
				} catch (SQLException err) {
					err.printStackTrace();
				}
			} catch (ClassNotFoundException err) {
				err.printStackTrace();
			}
			
			response.sendRedirect("Notification.jsp");
			
		}
	}

}
