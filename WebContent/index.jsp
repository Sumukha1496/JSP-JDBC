<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 

<html> 
	<head> 
		<title>Connection with mysql database</title> 
	</head> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	<body>
<% 
	try {
		String connectionURL = "jdbc:mysql://localhost:3306/EmployeeDB"; 
		
		Connection connection = null; 
		
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		
		connection = DriverManager.getConnection(connectionURL, "root", "");
		
		if(!connection.isClosed())
		%>
			<font color="green"></b>
		<% 
		out.println("Successfully connected to " + "MySQL server using TCP/IP...");
		connection.close();
	}
	catch(Exception ex){
		%>
			</font>
			<font size="+3" color="red"></b>
		<%
		out.println("Unable to connect to database.");
	}
%>
		</font>
		<br/><br/>
		<div class="container">
		<div class="col">
			<div class="title">List of All Employees</div> <hr>
			<table border="border">
				<tr>
					<th>&emsp; ID &emsp;</th>
					<th>&emsp; NAME &emsp;</th>
					<th>&emsp; AGE &emsp;</th>
					<th>&emsp; SALARY &emsp;</th>
				</tr>
			
			<% 
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDB", "root", "");
					Statement stmt = con.createStatement();
					String read_query = "select * from Employees order by id";
					ResultSet rs = stmt.executeQuery(read_query);
					System.out.println("Reading.....");
					while(rs.next()) {
					%>
						<tr>
							<td><% int id = rs.getInt("id"); out.println(id);%></td>
							<td><% String name = rs.getString("name"); out.println(name); %></td>
							<td><% int age =rs.getInt("age"); out.println(age);%></td>
							<td><% int sal = rs.getInt("salary"); out.println(sal); %></td>
						</tr>
						
					<%
					}
				} catch (SQLException err) {
					err.printStackTrace();
				}
			} catch (ClassNotFoundException err) {
				err.printStackTrace();
			}
				
		%>
		</table>
		<br/>
		<span style="float:left; margin-right:15px;">
			<form action="addEmployee.jsp" >
				<input type="submit" value="ADD an Employee" class="btn btn-primary"/>
			</form>
			<p>&emsp;</p>
		</span>
		<span style="float:left;">
			<form action="updateEmployee.jsp" >
				<input type="submit" value="UPDATE an Employee" class="btn btn-info"/>
			</form>
		</span>
		<!-- <a class="btn btn-warning" href="http://localhost:8080/EmployeeDB/mainpage.jsp">ADD</a>
			<legend>Employee details</legend>
		<form action="Main" method="post">
			Id : <input name="id" type="number" /> <br><br>
			Name : <input name="name" type="text" /> <br><br>
			age : <input name="age" type="number" /> <br><br>
			salary : <input name="salary" type="number" /> <br><br>
			<br><br><br>
			<input type="submit" id="submitbtn" value="ADD Employee" class="btn btn-primary"/><br/><br/>
		</form> -->
		</div>
	</div>
		
	</body> 
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
</html>