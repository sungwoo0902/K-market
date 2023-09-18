package kr.co.kmarket.dto;

public class CsDTO {
	private int no;
	private int boardCate1;
	private int boardCate2;	
	private int boardCate3;
	private String uid;
	private String title;
	private String content;
	private String rDate;
	

	
	public int getBoardCate1() {
		return boardCate1;
	}
	public void setBoardCate1(int boardCate1) {
		this.boardCate1 = boardCate1;
	}
	public void setBoardCate1(String boardCate1) {
		this.boardCate1 = Integer.parseInt(boardCate1);
	}
	
	
	
	public int getBoardCate2() {
		return boardCate2;
	}
	public void setBoardCate2(int boardCate2) {
		this.boardCate2 = boardCate2;
	}
	public void setBoardCate2(String boardCate2) {
		this.boardCate2 = Integer.parseInt(boardCate2);
	}
	
	
	
	public int getBoardCate3() {
		return boardCate3;
	}
	public void setBoardCate3(int boardCate3) {
		this.boardCate3 = boardCate3;
	}
	public void setBoardCate3(String boardCate3) {
		this.boardCate3 = Integer.parseInt(boardCate3);
	}
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setNo(String no) {
		this.no = Integer.parseInt(no);
	}
	
	
	
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	// 추가 필드
	private String cate1_name;
	private String cate2_name;
	private String cate2_discription;
	private String cate3_name;
	
	public String getCate1_name() {
		return cate1_name;
	}
	public void setCate1_name(String cate1_name) {
		this.cate1_name = cate1_name;
	}
	
	
	
	public String getCate2_name() {
		return cate2_name;
	}
	public void setCate2_name(String cate2_name) {
		this.cate2_name = cate2_name;
	}
	
	
	
	public String getCate2_discription() {
		return cate2_discription;
	}
	public void setCate2_discription(String cate2_discription) {
		this.cate2_discription = cate2_discription;
	}
	
	
	
	public String getCate3_name() {
		return cate3_name;
	}
	public void setCate3_name(String cate3_name) {
		this.cate3_name = cate3_name;
	}
	
	
	
	
	@Override
	public String toString() {
		return "CsDTO [no=" + no + ", boardCate1=" + boardCate1 + ", boardCate2=" + boardCate2 + ", boardCate3="
				+ boardCate3 + ", uid=" + uid + ", title=" + title + ", content=" + content + ", rDate=" + rDate
				+ ", cate1_name=" + cate1_name + ", cate2_name=" + cate2_name + ", cate2_discription="
				+ cate2_discription + ", cate3_name=" + cate3_name + "]";
	}
	
	
	
	
}