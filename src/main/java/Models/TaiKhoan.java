package Models;

public class TaiKhoan {

	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}
	private String matkhau;
	private String tenTk;
	private String idTk;
	private String phanQuyen;
	private String trangThai;

	
	public TaiKhoan( String matkhau, String tenTk, String idTk ,String phanQuyen) {
		super();
		this.matkhau = matkhau;
		this.tenTk = tenTk;
		this.idTk =idTk;
		this.phanQuyen =phanQuyen;
		
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String password) {
		this.matkhau = password;
	}
	
	public String getTenTk() {
		return this.tenTk;
	}

	public void setTenTk(String tenTk) {
		this.tenTk = tenTk;
	}
	
	public String getIdTk() {
		return this.idTk;
	}
	
	public void setIdTk(String idTk) {
		this.idTk = idTk;
	}
	
	public String getPhanQuyen() {
		return this.phanQuyen;
	}
	
	public void setPhanQuyen(String phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	
	public String getTrangThai() {
		return this.trangThai;
	}
	
	public void setTrangThai(String phanQuyen) {
		this.trangThai = phanQuyen;
	}
}
