package kr.co.kmarket.dto;

import kr.co.kmarket.db.Utils;

public class ReviewDTO {

	private int    revNo;
	private int    prodNo;
	private String content;
	private String uid;
	private int    rating;
	private String regip;
	private String rdate;
	
	
	/* 추가 필드 */
	private String prodName;
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	
	
	
	public int getRevNo() {
		return revNo;
	}
	public void setRevNo(int revNo) {
		this.revNo = revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = Integer.parseInt(revNo);
	}
	
	
	
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = Integer.parseInt(prodNo);
	}
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	public String getUid() {
		return uid;
	}
	public String getMaskingUid() {
		return Utils.masking(uid);
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setRating(String rating) {
		this.rating = Integer.parseInt(rating);
	}
	
	
	
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	
	
	
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate.substring(0, 10);
	}
	
	
	
	@Override
	public String toString() {
		return "ReviewDTO [revNo=" + revNo + ", prodNo=" + prodNo + ", content=" + content + ", uid=" + uid
				+ ", rating=" + rating + ", regip=" + regip + ", rdate=" + rdate + ", prodName=" + prodName + "]";
	}
	
	
	
}
