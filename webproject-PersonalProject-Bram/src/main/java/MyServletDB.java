//Name: Jonathan Bram
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServletDB")
public class MyServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String url = "jdbc:mysql://ec2-18-221-65-21.us-east-2.compute.amazonaws.com:3306/PersonalProjectBram";
	static String user = "jbramremoteuser";
	static String password = "jbram";
	static Connection connection = null;

	public MyServletDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("-------- MySQL JDBC Connection Testing------------<br>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // old:com.mysql.jdbc.Driver
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		response.getWriter().println("MySQL JDBC Driver Registered!<br>");
		connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			response.getWriter().println("You made it, take control your database now! <br>");
		} else {
			System.out.println("Failed to make connection!");
		}
		try {
			String selectSQL = "SELECT * FROM UserTable WHERE USERNAME LIKE ?";
			String theUserName = "user%";
			response.getWriter().println(selectSQL + "<br>");
			response.getWriter().println("------------------------------------------ <br>");
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, theUserName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String userName = rs.getString("USERNAME");
				String userID = rs.getString("USERID");
				String money = rs.getString("MONEY");
				response.getWriter().append("ID: " + id + ", ");
				response.getWriter().append("USER NAME: " + userName + ", ");
				response.getWriter().append("USER ID: " + userID + ", ");
				response.getWriter().append("MONEY: " + money + ", ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
	}
}
