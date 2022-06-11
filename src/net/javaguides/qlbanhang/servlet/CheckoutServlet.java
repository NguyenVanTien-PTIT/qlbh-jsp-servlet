package net.javaguides.qlbanhang.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;

import net.javaguides.qlbanhang.dao.DBUtils;
import net.javaguides.qlbanhang.dao.MySQLConnUtils;
import net.javaguides.qlbanhang.model.CTHD;
import net.javaguides.qlbanhang.model.Cart;
import net.javaguides.qlbanhang.model.HoaDon;
import net.javaguides.qlbanhang.model.UserAccount;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		request.setAttribute("today", dateFormat.format(date));
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       Connection conn;
	        try{
	        
	            conn= MySQLConnUtils.getMySQLConnection();
	            Calendar calendar = Calendar.getInstance();
	            
	            String codeHoaDon = "hoa_don_" + 
	            		calendar.get(Calendar.YEAR) +
                        (calendar.get(Calendar.MONTH) + 1) +
                        calendar.get(Calendar.DATE) +
                        "_" +
                        calendar.get(Calendar.HOUR) +
                        calendar.get(Calendar.MINUTE) +
                        calendar.get(Calendar.MILLISECOND);
	            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		Date date = new Date();
	            
	            HoaDon hoaDon = new ObjectMapper().readValue(request.getReader(), HoaDon.class);
	            
	            hoaDon.setMaHoaDon(codeHoaDon);
	            hoaDon.setThoiGian(dateFormat.format(date));
	            hoaDon.setTrangThai(0);
	            
	            DBUtils.insertHoaDon(conn, hoaDon);
	            
	            hoaDon.getCarts().forEach(
            		cart -> {
            			CTHD cthd = new CTHD();
            			cthd.setMaCTHD("chi_tiet_hoa_don_" + 
        	            		calendar.get(Calendar.YEAR) +
                                (calendar.get(Calendar.MONTH) + 1) +
                                calendar.get(Calendar.DATE) +
                                "_" +
                                calendar.get(Calendar.HOUR) +
                                calendar.get(Calendar.MINUTE) +
                                calendar.get(Calendar.MILLISECOND)+ "_" + UUID.randomUUID());
            			cthd.setMaHoaDon(codeHoaDon);
            			cthd.setMaSP(cart.getProduct().getCode());
            			cthd.setSoLuong(cart.getQuantity());
            			cthd.setDonGia(cart.getProduct().getPrice().floatValue());
            			cthd.setTongTien(cthd.getDonGia() * cthd.getSoLuong());
            			try {
							DBUtils.insertCTHD(conn, cthd);
						} catch (SQLException e) {
							e.printStackTrace();
						}
            		}
	            );
	            response.setContentType("text/plain");
	            response.setCharacterEncoding("UTF-8");
	            

                response.getWriter().write("SUCCESS");

	        }catch (Exception ex) {
	               Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

}
