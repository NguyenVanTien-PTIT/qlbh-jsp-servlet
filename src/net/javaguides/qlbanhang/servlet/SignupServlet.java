package net.javaguides.qlbanhang.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
 * Servlet implementation class SignupServlet
 */
@WebServlet("/sign-up")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = null; //connect SQL
    	String maNV = "";
		try {
			conn= MySQLConnUtils.getMySQLConnection();
			maNV = DBUtils.genarateMaNV(conn);
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		
		request.setAttribute("maNV",maNV);
       request.getRequestDispatcher("sign-up.jsp").forward(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String rs = "";
    	Connection conn = null; //connect SQL
		try {
			conn= MySQLConnUtils.getMySQLConnection();
			
            String ma=request.getParameter("maNV");
            String fullName=request.getParameter("fullName");
            String password=request.getParameter("password");
            String phone=request.getParameter("phone");
            String email=request.getParameter("email");
            
            UserAccount usr = new UserAccount(ma, password, fullName, phone, email);

            UserAccount user = DBUtils.findUserByCode(conn, ma);
            
            if(user != null) {
            	rs = "INVALID";
            }else {
                rs = DBUtils.insertUserAccount(conn,usr); 	
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(rs);
        }catch (Exception ex) {
               System.err.println(ex.getMessage());
        }
	}
		
}
