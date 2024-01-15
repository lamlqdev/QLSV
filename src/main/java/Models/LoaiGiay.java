package Models;

public class LoaiGiay {
	private String maLoaiGiay;
	private String tenLoaiGiay;
	
	public LoaiGiay() {
		super();
	}

	public LoaiGiay(String maLoaiGiay, String tenLoaiGiay) {
		super();
		this.maLoaiGiay = maLoaiGiay;
		this.tenLoaiGiay = tenLoaiGiay;
	}

	public String getMaLoaiGiay() {
		return maLoaiGiay;
	}

	public void setMaLoaiGiay(String maLoaiGiay) {
		this.maLoaiGiay = maLoaiGiay;
	}

	public String getTenLoaiGiay() {
		return tenLoaiGiay;
	}

	public void setTenLoaiGiay(String tenLoaiGiay) {
		this.tenLoaiGiay = tenLoaiGiay;
	}
	
}
