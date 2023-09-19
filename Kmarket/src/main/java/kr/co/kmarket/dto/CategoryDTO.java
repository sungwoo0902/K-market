package kr.co.kmarket.dto;

public class CategoryDTO {

	private int cate1No;
	private String c1Name;
	private int cate2No;
	private String c2Name;
	private String cate1Icon;
	
	
	public int getCate1No() {
		return cate1No;
	}
	public void setCate1No(int cate1No) {
		this.cate1No = cate1No;
	}
	public void setCate1No(String cate1No) {
		this.cate1No = Integer.parseInt(cate1No);
	}
	public String getC1Name() {
		return c1Name;
	}
	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}
	public int getCate2No() {
		return cate2No;
	}
	public void setCate2No(int cate2No) {
		this.cate2No = cate2No;
	}
	public void setCate2No(String cate2No) {
		this.cate2No = Integer.parseInt(cate2No);
	}
	public String getC2Name() {
		return c2Name;
	}
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
	
	
	public String getCate1Icon() {
		return cate1Icon;
	}
	public void setCate1Icon(String cate1Icon) {
		this.cate1Icon = cate1Icon;
	}
	@Override
	public String toString() {
		return "CategoryDAO [cate1=" + cate1No + ", c1Name=" + c1Name + ", cate2=" + cate2No + ", c2Name=" + c2Name + "]";
	}
}
