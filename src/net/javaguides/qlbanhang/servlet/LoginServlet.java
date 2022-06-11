package net.javaguides.qlbanhang.servlet;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setAttribute("action", "LOGOUT");
       request.getRequestDispatcher("index.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
        try{
        
            conn= MySQLConnUtils.getMySQLConnection();
   
            String maNV = request.getParameter("maNV");
            String password = request.getParameter("password");

            UserAccount u = DBUtils.findUser(conn, maNV, password);
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
            if(u!=null) {
            	u.setRoles(DBUtils.findRolesByUser(conn, maNV));
                response.getWriter().write(u.toString());
            } else {
                response.getWriter().write("INVALID");
            }

        }catch (Exception ex) {
               Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
