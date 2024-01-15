package Models;

import java.sql.Date;

public class DangKiCTXH {
	private String ID;
	private String tenDichVu;
	private String maHoatDong;
	private String tenHoatDong;
	private String diem;
	private String mssv;
	private Date thoiGianDangKi;
	private Date thoiGianPhanHoi;
	private String tieuDe;
	private String MaCTSV;
	private String noiDungPhanHoi;
	private String trangThai;
	
	public DangKiCTXH() {
		super();
	}
	
	public DangKiCTXH(String iD, String tenHoatDong, String diem, String mssv, Date thoiGianDangKi, String trangThai) {
		super();
		this.ID = iD;
		this.tenHoatDong = tenHoatDong;
		this.diem = diem;
		this.mssv = mssv;
		this.thoiGianDangKi = thoiGianDangKi;
		this.trangThai = trangThai;
	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
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

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public Date getThoiGianDangKi() {
		return thoiGianDangKi;
	}

	public void setThoiGianDangKi(Date thoiGianDangKi) {
		this.thoiGianDangKi = thoiGianDangKi;
	}

	public Date getThoiGianPhanHoi() {
		return thoiGianPhanHoi;
	}

	public void setThoiGianPhanHoi(Date thoiGianPhanHoi) {
		this.thoiGianPhanHoi = thoiGianPhanHoi;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getMaCTSV() {
		return MaCTSV;
	}

	public void setMaCTSV(String maCTSV) {
		MaCTSV = maCTSV;
	}

	public String getNoiDungPhanHoi() {
		return noiDungPhanHoi;
	}

	public void setNoiDungPhanHoi(String noiDungPhanHoi) {
		this.noiDungPhanHoi = noiDungPhanHoi;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
}
