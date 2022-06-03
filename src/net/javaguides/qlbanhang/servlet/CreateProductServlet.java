package net.javaguides.qlbanhang.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.xdevapi.JsonArray;

import net.javaguides.qlbanhang.dao.DBUtils;
import net.javaguides.qlbanhang.dao.MySQLConnUtils;
import net.javaguides.qlbanhang.model.Product;

/**
 * Servlet implementation class CreateProductServlet
 */
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 100
)
@WebServlet("/create-product")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null; //connect SQL
		String code = "";
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			conn = MySQLConnUtils.getMySQLConnection();
			code = DBUtils.genarateProductCode(conn);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		request.setAttribute("productCode", code);
		request.getRequestDispatcher("create-product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// TODO Auto-generated method stub
		String result = "";
		Connection conn = null; //connect SQL
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			conn = MySQLConnUtils.getMySQLConnection();
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String code = request.getParameter("code");
			Double price=Double.valueOf(request.getParameter("price"));
			String image="";
			Part part= request.getPart("image");
			String fileName=extractFileName(part);
			fileName=new File(fileName).getName();
			part.write("D:/JAVA fullstack/servlet-tutorial/login-jsp-servlet-jdbc-example/WebContent/Images/"+fileName);
			image="Images/"+fileName;
			Product pro= new Product(null, code, image, name, type, price); //tạo đối tượng product

			// Kiem tra trung ma code
			Product product = DBUtils.findProductByCode(conn, code);
			
			if(product != null) {
				result = "INVALID"; 
			}else {
				// thêm đối tượng vào CSDL
				DBUtils.insertProduct(conn, pro);	
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(result);
	}
	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
