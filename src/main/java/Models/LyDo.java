package Models;

public class LyDo {
	private String MaLyDo;
	private String TenLyDo;
	private String NoiDung;
	private String TrangThai;
	
	public LyDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LyDo(String maLyDo, String tenLyDo, String noiDung, String trangThai) {
		super();
		MaLyDo = maLyDo;
		TenLyDo = tenLyDo;
		NoiDung = noiDung;
		TrangThai = trangThai;
	}

	public String getMaLyDo() {
		return MaLyDo;
	}

	public void setMaLyDo(String maLyDo) {
		MaLyDo = maLyDo;
	}

	public String getTenLyDo() {
		return TenLyDo;
	}

	public void setTenLyDo(String tenLyDo) {
		TenLyDo = tenLyDo;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	
}
