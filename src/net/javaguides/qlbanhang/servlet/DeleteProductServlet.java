package net.javaguides.qlbanhang.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.qlbanhang.dao.DBUtils;
import net.javaguides.qlbanhang.dao.MySQLConnUtils;
import net.javaguides.qlbanhang.model.UserAccount;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn;
        try{
        
            conn= MySQLConnUtils.getMySQLConnection();
   
            Long id = Long.parseLong(request.getParameter("id"));
			DBUtils.deleteProduct(conn, id);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            response.getWriter().write("SUCCESS");

        }catch (Exception ex) {
           Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
           response.getWriter().write("INVALID");
        }
	}

}
