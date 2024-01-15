package Models;

import java.sql.Date;
import java.time.*;

public class CTXH {
	private String MaHD;
	private String TenHD;
	private String Diem;
	private String NoiDung;
	private LocalDateTime TgDienRa;
	private LocalDateTime TgKetThuc;
	private String TrangThai;
	
	public CTXH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTXH(String maHD, String tenHD, String diem, String noiDung, LocalDateTime tgDienRa, LocalDateTime tgKetThuc,
			String trangThai) {
		super();
		MaHD = maHD;
		TenHD = tenHD;
		Diem = diem;
		NoiDung = noiDung;
		TgDienRa = tgDienRa;
		TgKetThuc = tgKetThuc;
		TrangThai = trangThai;
	}

	public CTXH(String tenHD, String diem, String noiDung, LocalDateTime tgDienRa, LocalDateTime tgKetThuc, String trangThai) {
		super();
		TenHD = tenHD;
		Diem = diem;
		NoiDung = noiDung;
		TgDienRa = tgDienRa;
		TgKetThuc = tgKetThuc;
		TrangThai = trangThai;
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

	public LocalDateTime getTgDienRa() {
		return TgDienRa;
	}

	public void setTgDienRa(LocalDateTime tgDienRa) {
		TgDienRa = tgDienRa;
	}

	public LocalDateTime getTgKetThuc() {
		return TgKetThuc;
	}

	public void setTgKetThuc(LocalDateTime tgKetThuc) {
		TgKetThuc = tgKetThuc;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
}
