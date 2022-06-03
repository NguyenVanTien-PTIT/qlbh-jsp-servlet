package net.javaguides.qlbanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
//
//
//	public static Product findProduct(Connection conn, String ID) throws SQLException {
//		String sql = "Select a.ID, a.Name, a.Price from Product a where a.Code=?";
//		
//		
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, ID);
//		ResultSet rs = pstm.executeQuery();
//		while (rs.next()) {
//				String image = rs.getString("image"); 
//				String name = rs.getString("Name");
//				String type = rs.getString("Type");
//		        Float price = rs.getFloat("Price");
//		        
//		        Product product = new Product(ID, image, name, type, price);
//		        return product;
//		}
//		return null;
//	}
//	
	
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
}

