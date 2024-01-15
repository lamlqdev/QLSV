package Models;

public class KhoaHoc {
	
	private String maKH;
	private String tenKH;
	private String namBD;
	private String namKT;
	
	public KhoaHoc() {
		// TODO Auto-generated constructor stub
	}
	
	 public KhoaHoc(String maKhoa, String tenKhoa,String namBD,String namKT ) {
			// TODO Auto-generated constructor stub
			 this.maKH = maKhoa;
			 this.tenKH=tenKhoa;
			 this.namBD = namBD;
			 this.namKT = namKT;
	}

	public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKhoa) {
        this.maKH = maKhoa;
    }
    
    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKhoa) {
        this.tenKH = tenKhoa;
    }
    
    public String getNamBD() {
        return namBD;
    }

    public void setNamBD(String namBD) {
        this.namBD = namBD;
    }
    
    public String getNamKT() {
        return namKT;
    }

    public void setNamKT(String namKT) {
        this.namKT = namKT;
    }
    
   

}
