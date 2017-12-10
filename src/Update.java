

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
					String update_query = "update Employees set name='"+name+"',age="+  age +",salary=" + salary+" where id="+id;
					stmt.executeUpdate(update_query);
					System.out.println(update_query);
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
