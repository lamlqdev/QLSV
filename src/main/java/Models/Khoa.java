package Models;

public class Khoa {
	
	private String maKhoa;
	private String tenKhoa;
	private String trangThai;

	public Khoa() {
		// TODO Auto-generated constructor stub
	}
	
	 public Khoa(String maKhoa, String tenKhoa, String trangThai) {
		// TODO Auto-generated constructor stub
		 this.maKhoa = maKhoa;
		 this.tenKhoa=tenKhoa;
		 this.trangThai=trangThai;
	}

	public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
