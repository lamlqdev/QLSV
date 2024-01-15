package Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public class ThongBao {

	
	private String tenDV;
	private String tenSV;
	private Timestamp tgGui;
	private String tenCTSV;
	private Timestamp tgPhanHoi;
	private String tieuDe;
	private String ndPhanHoi;
	private String tenKhoa;
	private String tenKH;
	private String trangThai;
	private LocalDateTime tgPhanHoi1;
	private LocalDateTime tgGui1;

	
	public ThongBao()
	{		
	}
	
	public LocalDateTime getTgGui() {
		return tgGui1;
	}


	public void setTgGui(LocalDateTime tgGui) {
		this.tgGui1 = tgGui;
	}
	
	public LocalDateTime getTgPhanHoi() {
		return tgPhanHoi1;
	}


	public void setTgPhanHoi(LocalDateTime tgPhanHoi) {
		this.tgPhanHoi1 = tgPhanHoi;
	}

	
	
	public String getTenDV()
	{
		return this.tenDV;
	}
	
	public String getTenSV()
	{
		return this.tenSV;
	}
	public String getTenCTSV()
	{
		return this.tenCTSV;
	}
	
	public Timestamp getTGGui()
	{
		return this.tgGui;
	}
	
	public Timestamp getTGPhanHoi()
	{
		return this.tgPhanHoi;
	}
	
	public String getTieuDe()
	{
		return this.tieuDe;
	}
	
	public String getNDPhanHoi()
	{
		return this.ndPhanHoi;
	}
	
	public String getTenKhoa()
	{
		return this.tenKhoa;
	}
	
	public String getTenKH()
	{
		return this.tenKH;
	}
	
	public void setTenDV(String tenDV){
		this.tenDV = tenDV;
	}
	
	public void setTenSV(String tenSV){
		this.tenSV = tenSV;
	}
	
	public void setTGGui(Timestamp tgGui){
		this.tgGui = tgGui;
	}
			
	
	public void setTenCTSV(String tenSV){
		this.tenCTSV = tenSV;
	}
	
	public void setTGPhanHoi(Timestamp tgGui){
		this.tgPhanHoi = tgGui;
	}
	
	public void setTieuDe(String tieuDe)
	{
		 this.tieuDe= tieuDe;
	}
	
	public void setNDPhanHoi(String nd)
	{
		 this.ndPhanHoi = nd;
	}
	
	public void setTenKhoa(String nd)
	{
		 this.tenKhoa = nd;
	}
	
	public void setTenKH(String nd)
	{
		 this.tenKH = nd;
	}
	
	public String getTrangThai ()
	{
		return this.trangThai;
	}
	
	public void setTrangThai(String nd)
	{
		 this.trangThai = nd;
	}
	
}
