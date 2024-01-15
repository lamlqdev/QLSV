package Models;

import java.sql.Date;

public class SinhVien
{

	private String MSSV;
	private String maKhoa;
	private String tenKhoa;
	private String maKhoaHoc;
	private String tenKH;
	private int namBD;
	private int namKT;
	private int diemCTX;
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
 
    
    public SinhVien() {}
    

	public SinhVien(String MSSV, String hoTen, String gioitinh, Date ngaySinh, String tenkhoa, String tenKH,
			String email, String sdt, String quequan, String trangthai) {
		this.MSSV = MSSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.tenKhoa = tenkhoa;
        this.tenKH = tenKH;
        this.queQuan = quequan;
        this.soDienThoai = sdt;
        this.email = email;
        this.trangThai=trangthai;
		// TODO Auto-generated constructor stub
	}


	public TaiKhoan getTaiKhoan() {
		return this.taikhoan;
	}
	
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taikhoan = taiKhoan;
	}
	public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
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
    
    public String getMaKH() {
        return maKhoaHoc;
    }

    public void setMaKH(String maKH) {
        this.maKhoaHoc = maKH;
    }
    
    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
    
    public int getNamBD() {
        return namBD;
    }

    public void setNamBD(int namBD) {
        this.namBD = namBD;
    }
    
    public int getNamKT() {
        return namKT;
    }

    public void setNamKT(int namKT) {
        this.namKT = namKT;
    }
    
    public int getDiemCTXH() {
        return namBD;
    }

    public void setDiemCTXH(int diemCTXH) {
        this.diemCTX = diemCTXH;
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
