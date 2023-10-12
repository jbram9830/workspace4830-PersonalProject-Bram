
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteItemsFromTable")
public class Purchase extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Purchase() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
      String itemName = request.getParameter("itemName");
      String itemID = request.getParameter("itemID");
      String itemPrice = request.getParameter("itemPrice");

      Connection connection = null;
      String deleteSql = " DELETE FROM ItemsTable";
      String truncateSql = "ALTER TABLE ItemsTable AUTO_INCREMENT = 1";
      try {
         DBConnectionBram.getDBConnection();
         connection = DBConnectionBram.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(deleteSql);
         PreparedStatement preparedStmt2 = connection.prepareStatement(truncateSql);
         preparedStmt.execute();
         preparedStmt2.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Thank you for your purchase!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //
            
            "</ul>\n");

      out.println("<a href=/webproject-PersonalProject-Bram/mainPage.html>Main Page</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
