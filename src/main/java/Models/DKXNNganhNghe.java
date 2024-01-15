package Models;

import java.sql.Date;
import java.time.*;

public class DKXNNganhNghe {
	private String id;
	private String TenDichVu;
	private String NamHoc;
	private String HocKy;
	private String MaLyDo;
	private String NDLyDo;
	private String MSSV;
	private LocalDateTime ThoiGianDK;
	private LocalDateTime ThoiGianPH;
	private String TieuDe;
	private String MaCTSV;
	private String NDPhanHoi;
	private String TrangThai;
	
	public DKXNNganhNghe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DKXNNganhNghe(String id, String tenDichVu, String namHoc, String hocKy, String maLyDo, String ndLyDo,String mSSV,
			LocalDateTime thoiGianDK, LocalDateTime thoiGianPH, String tieuDe, String maCTSV, String nDPhanHoi,
			String trangThai) {
		super();
		this.id = id;
		TenDichVu = tenDichVu;
		NamHoc = namHoc;
		HocKy = hocKy;
		MaLyDo = maLyDo;
		NDLyDo = ndLyDo;
		MSSV = mSSV;
		ThoiGianDK = thoiGianDK;
		ThoiGianPH = thoiGianPH;
		TieuDe = tieuDe;
		MaCTSV = maCTSV;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
	}

	public DKXNNganhNghe(String tenDichVu, String namHoc, String hocKy, String maLyDo, String nDLyDo, String mSSV,
			LocalDateTime thoiGianDK, String trangThai) {
		super();
		TenDichVu = tenDichVu;
		NamHoc = namHoc;
		HocKy = hocKy;
		MaLyDo = maLyDo;
		NDLyDo = nDLyDo;
		MSSV = mSSV;
		ThoiGianDK = thoiGianDK;
		TrangThai = trangThai;
	}

	public String getNDLyDo() {
		return NDLyDo;
	}

	public void setNDLyDo(String nDLyDo) {
		NDLyDo = nDLyDo;
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

	public String getNamHoc() {
		return NamHoc;
	}

	public void setNamHoc(String namHoc) {
		NamHoc = namHoc;
	}

	public String getHocKy() {
		return HocKy;
	}

	public void setHocKy(String hocKy) {
		HocKy = hocKy;
	}

	public String getMaLyDo() {
		return MaLyDo;
	}

	public void setMaLyDo(String maLyDo) {
		MaLyDo = maLyDo;
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
	
}
