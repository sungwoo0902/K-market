package kr.co.kmarket.dto;

public class BoardDTO {
	private int no;
	private int boardCate1;
	private int boardCate2;	
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
	public int getBoardCate2() {
		return boardCate2;
	}
	public void setBoardCate2(int boardCate2) {
		this.boardCate2 = boardCate2;
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

}
