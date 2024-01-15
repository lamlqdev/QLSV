package Models;

import java.sql.Date;
import java.time.*;

public class DKCTXH {
	private String id;
	private String TenDichVu;
	private String MaHD;
	private String TenHD;
	private LocalDateTime ThoiGianDK;
	private String MSSV;
	private LocalDateTime ThoiGianPH;
	private String TieuDe;
	private String MaCTSV;
	private String NDPhanHoi;
	private String TrangThai;
	private String Diem;
	private String NoiDung;
	
	public DKCTXH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DKCTXH(String id, String tenDichVu, String maHD, String tenHD, LocalDateTime thoiGianDK, String mSSV,
			LocalDateTime thoiGianPH, String tieuDe, String maCTSV, String nDPhanHoi, String trangThai) {
		super();
		this.id = id;
		TenDichVu = tenDichVu;
		MaHD = maHD;
		TenHD = tenHD;
		ThoiGianDK = thoiGianDK;
		MSSV = mSSV;
		ThoiGianPH = thoiGianPH;
		TieuDe = tieuDe;
		MaCTSV = maCTSV;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
	}

	public DKCTXH(String tenDichVu, String maHD, String tenHD, LocalDateTime thoiGianDK, String mSSV,
			LocalDateTime thoiGianPH, String tieuDe, String maCTSV, String nDPhanHoi, String trangThai) {
		super();
		TenDichVu = tenDichVu;
		MaHD = maHD;
		TenHD = tenHD;
		ThoiGianDK = thoiGianDK;
		MSSV = mSSV;
		ThoiGianPH = thoiGianPH;
		TieuDe = tieuDe;
		MaCTSV = maCTSV;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
	}
	
	public DKCTXH(String tenDichVu, String maHD, String tenHD, LocalDateTime thoiGianDK, String mSSV, String nDPhanHoi,
			String trangThai, String diem, String noiDung) {
		super();
		TenDichVu = tenDichVu;
		MaHD = maHD;
		TenHD = tenHD;
		ThoiGianDK = thoiGianDK;
		MSSV = mSSV;
		NDPhanHoi = nDPhanHoi;
		TrangThai = trangThai;
		Diem = diem;
		NoiDung = noiDung;
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

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public String getTenHD() {
		return TenHD;
	}

	public void setTenHD(String tenHD) {
		TenHD = tenHD;
	}

	public LocalDateTime getThoiGianDK() {
		return ThoiGianDK;
	}

	public void setThoiGianDK(LocalDateTime thoiGianDK) {
		ThoiGianDK = thoiGianDK;
	}

	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
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

	public String getDiem() {
		return Diem;
	}

	public void setDiem(String diem) {
		Diem = diem;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	
}
