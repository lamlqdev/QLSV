package Models;

import java.sql.Date;

public class HoatDongCTXH {
	private String maHoatDong;
	private String tenHoatDong;
	private String diem;
	private String noiDung;
	private Date thoiGianDienRa;
	private Date thoiGianKetThuc;
	private String trangThai;
	
	public HoatDongCTXH() {
		super();
	}
	
	public HoatDongCTXH(String maHoatDong, String tenHoatDong, String diem, String noiDung, Date thoiGianDienRa,
			Date thoiGianKetThuc) {
		super();
		this.maHoatDong = maHoatDong;
		this.tenHoatDong = tenHoatDong;
		this.diem = diem;
		this.noiDung = noiDung;
		this.thoiGianDienRa = thoiGianDienRa;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public HoatDongCTXH(String maHoatDong, String tenHoatDong, String diem, String noiDung, Date thoiGianDienRa,
			Date thoiGianKetThuc, String trangThai) {
		super();
		this.maHoatDong = maHoatDong;
		this.tenHoatDong = tenHoatDong;
		this.diem = diem;
		this.noiDung = noiDung;
		this.thoiGianDienRa = thoiGianDienRa;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.trangThai = trangThai;
	}


	public String getMaHoatDong() {
		return maHoatDong;
	}

	public void setMaHoatDong(String maHoatDong) {
		this.maHoatDong = maHoatDong;
	}

	public String getTenHoatDong() {
		return tenHoatDong;
	}

	public void setTenHoatDong(String tenHoatDong) {
		this.tenHoatDong = tenHoatDong;
	}

	public String getDiem() {
		return diem;
	}

	public void setDiem(String diem) {
		this.diem = diem;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getThoiGianDienRa() {
		return thoiGianDienRa;
	}

	public void setThoiGianDienRa(Date thoiGianDienRa) {
		this.thoiGianDienRa = thoiGianDienRa;
	}

	public Date getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Date thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
}
