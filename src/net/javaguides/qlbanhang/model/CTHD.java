package net.javaguides.qlbanhang.model;

import java.io.Serializable;

public class CTHD implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String maCTHD;
	private String maHoaDon;
	private String maSP;
	private Float tongTien;
	private Float donGia;
	private Integer soLuong;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public Float getTongTien() {
		return tongTien;
	}

	public void setTongTien(Float tongTien) {
		this.tongTien = tongTien;
	}

	public Float getDonGia() {
		return donGia;
	}

	public void setDonGia(Float donGia) {
		this.donGia = donGia;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	
	

}
