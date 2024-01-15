package Models;

import java.sql.Date;
import java.time.*;

public class DKGiayXacNhan {
	private String id;
	private String TenDichVu;
	private String SoLuong;
	private String MaLoaiGiay;
	private String MSSV;
	private LocalDateTime ThoiGianDK;
	private LocalDateTime ThoiGianPH;
	private String TieuDe;
	private String MaCTSV;
	private String NDPhanHoi;
	private String TrangThai;
	private String STT;
	
	public DKGiayXacNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DKGiayXacNhan(String id, String tenDichVu, String soLuong, String maLoaiGiay, String mSSV,
			LocalDateTime thoiGianDK, LocalDateTime thoiGianPH, String tieuDe, String maCTSV, String nDPhanHoi,
			String trangThai) {
		super();
		this.id = id;
		TenDichVu = tenDichVu;
		SoLuong = soLuong;
		MaLoaiGiay = maLoaiGiay;
		MSSV = mSSV;
		ThoiGianDK = thoiGianDK;
		ThoiGianPH = thoiGianPH;
		TieuDe = tieuDe;
		MaCTSV = maCTSV;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
	}
	

	public DKGiayXacNhan(String tenDichVu, String soLuong, String maLoaiGiay, String mSSV, LocalDateTime thoiGianDK,
			String nDPhanHoi, String trangThai) {
		super();
		TenDichVu = tenDichVu;
		SoLuong = soLuong;
		MaLoaiGiay = maLoaiGiay;
		MSSV = mSSV;
		ThoiGianDK = thoiGianDK;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
	}
	
	

	public DKGiayXacNhan(String tenDichVu, String soLuong, String maLoaiGiay, String mSSV, LocalDateTime thoiGianDK,
			String nDPhanHoi, String trangThai, String sTT) {
		super();
		TenDichVu = tenDichVu;
		SoLuong = soLuong;
		MaLoaiGiay = maLoaiGiay;
		MSSV = mSSV;
		ThoiGianDK = thoiGianDK;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
		STT = sTT;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenDichVu() {
		return TenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		TenDichVu = tenDichVu;
	}

	public String getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(String soLuong) {
		SoLuong = soLuong;
	}

	public String getMaLoaiGiay() {
		return MaLoaiGiay;
	}

	public void setMaLoaiGiay(String maLoaiGiay) {
		MaLoaiGiay = maLoaiGiay;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public LocalDateTime getThoiGianDK() {
		return ThoiGianDK;
	}

	public void setThoiGianDK(LocalDateTime thoiGianDK) {
		ThoiGianDK = thoiGianDK;
	}

	public LocalDateTime getThoiGianPH() {
		return ThoiGianPH;
	}

	public void setThoiGianPH(LocalDateTime thoiGianPH) {
		ThoiGianPH = thoiGianPH;
	}

	public String getTieuDe() {
		return TieuDe;
	}

	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}

	public String getMaCTSV() {
		return MaCTSV;
	}

	public void setMaCTSV(String maCTSV) {
		MaCTSV = maCTSV;
	}

	public String getNDPhanHoi() {
		return NDPhanHoi;
	}

	public void setNDPhanHoi(String nDPhanHoi) {
		NDPhanHoi = nDPhanHoi;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public String getSTT() {
		return STT;
	}

	public void setSTT(String sTT) {
		STT = sTT;
	}
}
