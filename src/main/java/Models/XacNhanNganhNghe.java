package Models;

import java.sql.Date;

public class XacNhanNganhNghe {
	private int ID;
	private String tenDichVu;
	private Date thoiGianDangKi;
	private String namHoc;
	private String hocKy;
	private int maLyDo;
	private int mssv;
	private String tenLyDo;
	private Date thoiGianPhanHoi;
	private String tieuDe;
	private int MaCTSV;
	private String noiDungPhanHoi;
	private String trangThai;
	
	public XacNhanNganhNghe() {
		super();
	}
	
	public XacNhanNganhNghe(int ID, Date thoiGianDangKi, String namHoc, String hocKy, int mssv,
			String tenLyDo, String trangThai) {
		super();
		this.ID = ID;
		this.thoiGianDangKi = thoiGianDangKi;
		this.namHoc = namHoc;
		this.hocKy = hocKy;
		this.mssv = mssv;
		this.tenLyDo = tenLyDo;
		this.trangThai = trangThai;
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public Date getThoiGianDangKi() {
		return thoiGianDangKi;
	}

	public void setThoiGianDangKi(Date thoiGianDangKi) {
		this.thoiGianDangKi = thoiGianDangKi;
	}

	public String getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}

	public String getHocKy() {
		return hocKy;
	}

	public void setHocKy(String hocKy) {
		this.hocKy = hocKy;
	}

	public int getMaLyDo() {
		return maLyDo;
	}

	public void setMaLyDo(int maLyDo) {
		this.maLyDo = maLyDo;
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

	public int getMaCTSV() {
		return MaCTSV;
	}

	public void setMaCTSV(int maCTSV) {
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
	public int getMssv() {
		return mssv;
	}

	public void setMssv(int mssv) {
		this.mssv = mssv;
	}
	public String getTenLyDo() {
		return tenLyDo;
	}

	public void setTenLyDo(String tenLyDo) {
		this.tenLyDo = tenLyDo;
	}
}
