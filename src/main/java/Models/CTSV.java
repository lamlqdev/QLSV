package Models;

import java.sql.Date;

public class CTSV {

	public CTSV() {
		// TODO Auto-generated constructor stub
	}
	private String maCTSV;
    private String idTk;
    private String maTT;
    private String trangThai;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String queQuan;
    private String soDienThoai;
    private String email;
    private String gioiTinh;
    private TaiKhoan taikhoan;

    public CTSV(String maQTV, String idTk, String maTT, String trangThai, String hoTen, Date ngaySinh,
            String diaChi, String queQuan, String soDienThoai, String email) {
        this.maCTSV = maQTV;
        this.idTk = idTk;
        this.maTT = maTT;
        this.trangThai = trangThai;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.queQuan = queQuan;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }
    
    public CTSV(String MaCTSV,String HoTen,String gioitinh,Date NgaySinh,String email,String sdt,String diachi,String quequan,String trangthai) {
        this.maCTSV = MaCTSV;
        this.trangThai = trangthai;
        this.hoTen = HoTen;
        this.ngaySinh = NgaySinh;
        this.diaChi = diachi;
        this.queQuan = quequan;
        this.soDienThoai = sdt;
        this.email = email;
        this.gioiTinh = gioitinh;
    }
    
    public TaiKhoan getTaiKhoan() {
		return this.taikhoan;
	}
	
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taikhoan = taiKhoan;
	}
	
    public String getMaCTSV() {
        return maCTSV;
    }

    public void setMaCTSV(String maQTV) {
        this.maCTSV = maQTV;
    }

    public String getIdTk() {
        return idTk;
    }

    public void setIdTk(String idTk) {
        this.idTk = idTk;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.gioiTinh = GioiTinh;
    }
}
