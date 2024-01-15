package Models;

import java.sql.Date;

public class XacNhanGXN {
	private String ID;
	private String tenDichVu;
	private String soLuong;
	private String maLoaiGiay;
	private String tenLoaiGiay;
	private String stt;
	private String Mssv;
	private Date thoiGianDangKi;
	private Date thoiGianPhanHoi;
	private String tieuDe;
	private String MaCTSV;
	private String noiDungPhanHoi;
	private String trangThai;
	
	public XacNhanGXN() {
		super();
	}

	public XacNhanGXN(String iD, String tenDichVu, String soLuong, String maLoaiGiay, String tenLoaiGiay, String mssv,
			Date thoiGianDangKi, Date thoiGianPhanHoi, String tieuDe, String maCTSV, String noiDungPhanHoi,
			String trangThai) {
		super();
		ID = iD;
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.maLoaiGiay = maLoaiGiay;
		this.tenLoaiGiay = tenLoaiGiay;
		this.Mssv = mssv;
		this.thoiGianDangKi = thoiGianDangKi;
		this.thoiGianPhanHoi = thoiGianPhanHoi;
		this.tieuDe = tieuDe;
		MaCTSV = maCTSV;
		this.noiDungPhanHoi = noiDungPhanHoi;
		this.trangThai = trangThai;
	}

	public XacNhanGXN(String iD, String tenLoaiGiay, String mssv, Date thoiGianDangKi, String soLuong, String stt, String trangThai) {
		super();
		this.ID = iD;
		this.tenLoaiGiay = tenLoaiGiay;
		this.Mssv = mssv;
		this.thoiGianDangKi = thoiGianDangKi;
		this.soLuong = soLuong;
		this.stt = stt;
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

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaLoaiGiay() {
		return maLoaiGiay;
	}

	public void setMaLoaiGiay(String maLoaiGiay) {
		this.maLoaiGiay = maLoaiGiay;
	}

	public String getTenLoaiGiay() {
		return tenLoaiGiay;
	}

	public void setTenLoaiGiay(String tenLoaiGiay) {
		this.tenLoaiGiay = tenLoaiGiay;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getMssv() {
		return Mssv;
	}

	public void setMssv(String mssv) {
		Mssv = mssv;
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
