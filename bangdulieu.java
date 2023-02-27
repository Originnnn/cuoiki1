package DTO;

public class bangdulieu {
	private int stt;
	private String the_loai_thu_chi;
	private String ngay;
	private int so_tien;
	private String tai_khoan;
	private String the_loai;
	private String ghi_chu;
	public bangdulieu(int stt, String the_loai_thu_chi, String ngay, int so_tien, String tai_khoan, String the_loai,
			String ghi_chu) {
		this.stt = stt;
		this.the_loai_thu_chi = the_loai_thu_chi;
		this.ngay = ngay;
		this.so_tien = so_tien;
		this.tai_khoan = tai_khoan;
		this.the_loai = the_loai;
		this.ghi_chu = ghi_chu;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public String getThe_loai_thu_chi() {
		return the_loai_thu_chi;
	}
	public void setThe_loai_thu_chi(String the_loai_thu_chi) {
		this.the_loai_thu_chi = the_loai_thu_chi;
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public int getSo_tien() {
		return so_tien;
	}
	public void setSo_tien(int so_tien) {
		this.so_tien = so_tien;
	}
	public String getTai_khoan() {
		return tai_khoan;
	}
	public void setTai_khoan(String tai_khoan) {
		this.tai_khoan = tai_khoan;
	}
	public String getThe_loai() {
		return the_loai;
	}
	public void setThe_loai(String the_loai) {
		this.the_loai = the_loai;
	}
	public String getGhi_chu() {
		return ghi_chu;
	}
	public void setGhi_chu(String ghi_chu) {
		this.ghi_chu = ghi_chu;
	}
	
}	