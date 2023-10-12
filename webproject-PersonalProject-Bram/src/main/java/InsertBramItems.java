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

@WebServlet("/InsertBramItems")
public class InsertBramItems extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertBramItems() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String itemName = request.getParameter("itemName");
      String itemID = request.getParameter("itemID");
      String itemPrice = request.getParameter("itemPrice");
      
      
      Connection connection = null;
      

      String insertSql = " INSERT INTO ItemsTable (id, ITEMNAME, ITEMID, ITEMPRICE) values (default, ?, ?, ?)";
      
      
      try {
         DBConnectionBram.getDBConnection();
         connection = DBConnectionBram.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, itemName);
         preparedStmt.setString(2, itemID);
         preparedStmt.setString(3, itemPrice);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Items Added to Cart";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Item</b>: " + itemName + "\n" + //
            "  <li><b>Item ID</b>: " + itemID + "\n" + //
            "  <li><b>Item Price</b>: " + itemPrice + "\n" + //
            
            "</ul>\n");

      out.println("<a href=/webproject-PersonalProject-Bram/mainPage.html>Main Page</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
