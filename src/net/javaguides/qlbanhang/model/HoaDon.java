package net.javaguides.qlbanhang.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class HoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idHoaDon;
	private String maHoaDon;
	private String maNV;
	private String tenKH;
	private String sdt;
	private String diaChi;
	private Float tongTien;
	private String thoiGian;
	private Integer trangThai;
	private List<Cart> carts;
	
	
	
	public HoaDon() {
	}

	public Long getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(Long idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	public String getMaNV() {
		return maNV;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Float getTongTien() {
		return tongTien;
	}

	public void setTongTien(Float tongTien) {
		this.tongTien = tongTien;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	@Override
	public String toString() {
		return "HoaDon [idHoaDon=" + idHoaDon + ", maNV=" + maNV + ", tenKH=" + tenKH + ", sdt=" + sdt + ", diaChi="
				+ diaChi + ", tongTien=" + tongTien + ", trangThai=" + trangThai + "]";
	}
	
	
}
