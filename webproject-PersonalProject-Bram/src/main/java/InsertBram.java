/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertBram")
public class InsertBram extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertBram() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("userName");
      String userID = request.getParameter("userID");
      String money = request.getParameter("money");

      Connection connection = null;
      String insertSql = " INSERT INTO UserTable (id, USERNAME, USERID, MONEY) values (default, ?, ?, ?)";

      try {
         DBConnectionBram.getDBConnection();
         connection = DBConnectionBram.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, userName);
         preparedStmt.setString(2, userID);
         preparedStmt.setString(3, money);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Account Successfully Created";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>User Name</b>: " + userName + "\n" + //
            "  <li><b>User ID</b>: " + userID + "\n" + //
            "  <li><b>Money</b>: " + money + "\n" + //
            
            "</ul>\n");

      out.println("<a href=/webproject-PersonalProject-Bram/mainPage.html>Main Page</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
