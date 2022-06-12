package net.javaguides.qlbanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.qlbanhang.model.CTHD;
import net.javaguides.qlbanhang.model.HoaDon;
import net.javaguides.qlbanhang.model.Product;
import net.javaguides.qlbanhang.model.UserAccount;


public class DBUtils {
	
	public static String insertUserAccount(Connection conn, UserAccount user) throws SQLException {
	        String sql="insert into user_account(MaNV,Username,Password,Sdt,Gmail) values (?,?,?,?,?)";
	        PreparedStatement pstm =conn.prepareStatement(sql);
	        
	        pstm.setString(1,user.getMaNV());
	        pstm.setString(2,user.getFullName());
	        pstm.setString(3,user.getPassword());
	        pstm.setString(4,user.getPhone());
	        pstm.setString(5,user.getEmail());
	        
	        try {
	        	 pstm.executeUpdate();	
	        }catch (Exception e) {
				// TODO: handle exception
	        	return "INVALID";
			}
	      
	        return "SUCCESS";
    }
	
	public static void insertUserRole(Connection conn, UserAccount user, String role) throws SQLException {
        String sql="insert into user_role(ma_nv,role_id) values (?,?)";
        PreparedStatement pstm =conn.prepareStatement(sql);
        
        pstm.setString(1,user.getMaNV());
        if("QUAN_LY".equals(role)) {
        	pstm.setLong(2, 2);
        }else {
        	pstm.setLong(2, 1);
        }
        
        try {
        	 pstm.executeUpdate();	
        }catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static UserAccount findUserByCode(Connection conn, String maNV) throws SQLException {
	    String sql="select maNV, username, password, Sdt, Gmail from user_account " + " where MaNV=?";
	    PreparedStatement pstm=conn.prepareStatement(sql);
	    pstm.setString(1,maNV);
	    ResultSet rs=pstm.executeQuery();
	    
	    if(rs.next()){
	        UserAccount user=new UserAccount();
	        user.setMaNV(rs.getString("maNV"));
	        user.setFullName(rs.getString("username"));
	        user.setPassword(rs.getString("password"));
	        user.setPhone(rs.getString("Sdt"));
	        user.setEmail(rs.getString("Gmail"));
	        return user;
	    }
	    return null;
	}	
	
	public static UserAccount findUser(Connection conn, String maNV, String password) throws SQLException {
		    String sql="select maNV, username, password, Sdt, Gmail from user_account " + " where MaNV=? and Password=?";
		    PreparedStatement pstm=conn.prepareStatement(sql);
		    pstm.setString(1,maNV);
		    pstm.setString(2,password);
		    ResultSet rs=pstm.executeQuery();
		    
		    if(rs.next()){
		        UserAccount user=new UserAccount();
		        user.setMaNV(rs.getString("maNV"));
		        user.setFullName(rs.getString("username"));
		        user.setPassword(rs.getString("password"));
		        user.setPhone(rs.getString("Sdt"));
		        user.setEmail(rs.getString("Gmail"));
		        return user;
		    }
		    return null;
	}
		
	public static String genarateMaNV(Connection conn) throws SQLException {
			String sql="select maNV from user_account";
			PreparedStatement pstm=conn.prepareStatement(sql);
		    ResultSet rs= pstm.executeQuery();
		    List<String> list = new ArrayList<String>();
		    while(rs.next()){
		    	list.add(rs.getString("maNV"));
		    }
		   
		    
		    return "NV" + String.format("%04d", list.size() + 1);
	}
	
	public static String genarateProductCode(Connection conn) throws SQLException {
		String sql="select id from product";
		PreparedStatement pstm=conn.prepareStatement(sql);
	    ResultSet rs= pstm.executeQuery();
	    List<Integer> list = new ArrayList<Integer>();
	    while(rs.next()){
	    	list.add(rs.getInt("id"));
	    }
	   
	    
	    return "PREFIX" + String.format("%04d", list.size() + 1);
	}
		
	public static List<Product> queryProduct(Connection conn) throws SQLException {
		String sql = "Select a.Id, a.Code, a.Image, a.Name, a.Type, a.Price from Product a";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("Id"));
			String code = rs.getString("Code");
			String image= rs.getString("Image");
			String name = rs.getString("Name");
			String type = rs.getString("Type");
			Double price = rs.getDouble("Price");
			Product product = new Product();
			product.setId(id);
			product.setCode(code);
			product.setImage(image);
			product.setName(name);
			product.setType(type);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}
	
	public static List<Product> searchProduct(Connection conn, String key) throws SQLException {
		String sql = "Select a.Id, a.Code, a.Image, a.Name, a.Type, a.Price "
				+ "from Product a "
				+ "where UPPER(a.code) like concat('%', UPPER(?), '%') "
				+ "or UPPER(a.name) like concat('%', UPPER(?), '%') ";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, key);
		pstm.setString(2, key);
		
		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("Id"));
			String code = rs.getString("Code");
			String image= rs.getString("Image");
			String name = rs.getString("Name");
			String type = rs.getString("Type");
			Double price = rs.getDouble("Price");
			Product product = new Product();
			product.setId(id);
			product.setCode(code);
			product.setImage(image);
			product.setName(name);
			product.setType(type);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}
	
	public static void deleteProduct(Connection conn, Long id) throws SQLException {
		String sql = "Delete From Product where ID= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, id);
		pstm.executeUpdate();
	}

	public static List<String> findRolesByUser(Connection conn, String maNV) throws SQLException {
		String sql = "Select r.role_name "
				+ " from role r join user_role ur on r.id = ur.role_id "
				+ " where ur.ma_nv = ?";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maNV);
		ResultSet rs = pstm.executeQuery();
		List<String> roles = new ArrayList<>();
		while (rs.next()) {
			
			String roleName = rs.getString("role_name");
			roles.add(roleName);
		}
		return roles;
	}
	
	public static Product findProductByCode(Connection conn, String code) throws SQLException {
		String sql = "Select a.id, a.image, a.name, a.type, a.price, a.code from Product a where a.code=?";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Long id = rs.getLong("id");
			String image = rs.getString("image"); 
			String name = rs.getString("name");
			String type = rs.getString("type");
	        Double price = rs.getDouble("price");
	        Product product = new Product(id, code, image, name, type, price);
	        return product;
		}
		return null;
	}
	
	public static Product findProductById(Connection conn, Long id) throws SQLException {
		String sql = "Select a.code, a.image, a.name, a.type, a.price, a.code from Product a where a.id=?";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String code = rs.getString("code");
			String image = rs.getString("image"); 
			String name = rs.getString("name");
			String type = rs.getString("type");
	        Double price = rs.getDouble("price");
	        Product product = new Product(id, code, image, name, type, price);
	        return product;
		}
		return null;
	}

	public static void updateProduct(Connection conn, Product product) throws SQLException {
	    String sql = "Update product set code=?, Image=?, Name =?, Type =?, Price=? where ID=? ";
	    
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, product.getCode());
	    pstm.setString(2, product.getImage());
	    pstm.setString(3, product.getName());
	    pstm.setString(4, product.getType());
	    pstm.setDouble(5, product.getPrice());
	   
	    pstm.setLong(6, product.getId());
	    pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
	    String sql = "Insert into product(code, Image, Name, Type, Price) values (?,?,?,?,?)";
	   
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, product.getCode());
	    pstm.setString(2, product.getImage());
	    pstm.setString(3, product.getName());
	    pstm.setString(4, product.getType());
	    pstm.setDouble(5, product.getPrice());
	    pstm.executeUpdate();
    }
	
	public static void insertHoaDon(Connection conn, HoaDon hoadon) throws SQLException {
	    String sql = "Insert into hoa_don(ma_hd, ma_nv, ten_kh, sdt, dia_chi, tong_tien, trang_thai, thoi_gian) values (?,?,?,?,?,?,?,?)";
	   
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, hoadon.getMaHoaDon());
	    pstm.setString(2, hoadon.getMaNV());
	    pstm.setString(3, hoadon.getTenKH());
	    pstm.setString(4, hoadon.getSdt());
	    pstm.setString(5, hoadon.getDiaChi());
	    pstm.setDouble(6, hoadon.getTongTien());
	    pstm.setDouble(7, hoadon.getTrangThai());
	    pstm.setString(8, hoadon.getThoiGian());
	    pstm.executeUpdate();
    }
	
	
	public static void insertCTHD(Connection conn, CTHD cthd) throws SQLException {
	    String sql = "Insert into cthd(ma_cthd, ma_hd, ma_sp, tong_tien, don_gia, so_luong) values (?,?,?,?,?,?)";
	   
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, cthd.getMaCTHD());
	    pstm.setString(2, cthd.getMaHoaDon());
	    pstm.setString(3, cthd.getMaSP());
	    pstm.setFloat(4, cthd.getTongTien());
	    pstm.setDouble(5, cthd.getDonGia());
	    pstm.setDouble(6, cthd.getSoLuong());
	    pstm.executeUpdate();
    }
	
	public static List<String> findCTHDByMaSP(Connection conn, String maSP) throws SQLException {
		String sql = "Select ma_hd from cthd where ma_sp=?";
		
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maSP);
		ResultSet rs = pstm.executeQuery();
		
		List<String> list = new ArrayList<>();
		
		while (rs.next()) {
			String maHD = rs.getString("ma_hd"); 
	        list.add(maHD);
		}
		return list;
	}
}

