package kr.co.kmarket.dto;

import java.io.File;
import java.util.UUID;

import kr.co.kmarket.db.Utils;

public class ProductDTO {

	private int prodNo;
	private int prodCate1;
	private int prodCate2;
	private String prodName;
	private String descript;
	private String prodCompany;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String ip;
	private String rdate;
	
	/************************* 추가 *************************/
	private String path;
	private int level;
	private String company;
	private String c1Name;
	private String c2Name;
	
	public String getC1Name() {
		return c1Name;
	}

	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}

	public String getC2Name() {
		return c2Name;
	}

	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}

	public ProductDTO() {
		
	}
	
	public ProductDTO(String path) {
		this.path = path;
	}
	
	public int getDisPrice() {
		return this.price - ((this.price/100) * this.discount);
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setLevel(String level) {
		this.level = Integer.parseInt(level);
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	/******************************************************/
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = Integer.parseInt(prodNo);
	}
	public int getProdCate1() {
		return prodCate1;
	}
	public void setProdCate1(int prodCate1) {
		this.prodCate1 = prodCate1;
	}
	public void setProdCate1(String prodCate1) {
		this.prodCate1 = Integer.parseInt(prodCate1);
	}
	public int getProdCate2() {
		return prodCate2;
	}
	public void setProdCate2(int prodCate2) {
		this.prodCate2 = prodCate2;
	}
	public void setProdCate2(String prodCate2) {
		this.prodCate2 = Integer.parseInt(prodCate2);
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getProdCompany() {
		return prodCompany;
	}
	public void setProdCompany(String prodCompany) {
		this.prodCompany = prodCompany;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = Integer.parseInt(discount);
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setPoint(String point) {
		this.point = Integer.parseInt(point);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setStock(String stock) {
		this.stock = Integer.parseInt(stock);
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public void setSold(String sold) {
		this.sold = Integer.parseInt(sold);
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public void setHit(String hit) {
		this.hit = Integer.parseInt(hit);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setScore(String score) {
		this.score = Integer.parseInt(score);
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public void setReview(String review) {
		this.review = Integer.parseInt(review);
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public void setThumb1ForRename(String thumb1) {
		this.thumb1 = fileRename(thumb1);
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	public void setThumb2ForRename(String thumb2) {
		this.thumb2 = fileRename(thumb2);
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}
	public void setThumb3ForRename(String thumb3) {
		this.thumb3 = fileRename(thumb3);
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setDetailForRename(String detail) {
		this.detail = fileRename(detail);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	/************** Value With Comma *************************************/
	public String getPriceWithComma() {
		return Utils.comma(price);
	}
	public String getDeliveryWithComma() {
		return Utils.comma(delivery);
	}
	public String getDisPriceWithComma() {
		return Utils.comma(this.price - ((this.price/100) * this.discount));
	}
	
	
	public String fileRename(String thumb) {
		
		int i = thumb.lastIndexOf(".");
		// 확장자
		String ext = thumb.substring(i);
		// 중복되지 않는 랜덤 이름 생성
		String uuid = UUID.randomUUID().toString();
		// 랜덤 이름과 확장자 붙여서 저장할 때 사용할 이름 생성
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+thumb);
		File f2 = new File(path+"/"+sName);
		f1.renameTo(f2);
		
		return sName;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodNo=" + prodNo + ", prodCate1=" + prodCate1 + ", prodCate2=" + prodCate2 + ", prodName="
				+ prodName + ", descript=" + descript + ", prodCompany=" + prodCompany + ", seller=" + seller
				+ ", price=" + price + ", discount=" + discount + ", point=" + point + ", stock=" + stock + ", sold="
				+ sold + ", delivery=" + delivery + ", hit=" + hit + ", score=" + score + ", review=" + review
				+ ", thumb1=" + thumb1 + ", thumb2=" + thumb2 + ", thumb3=" + thumb3 + ", detail=" + detail
				+ ", status=" + status + ", duty=" + duty + ", receipt=" + receipt + ", bizType=" + bizType
				+ ", origin=" + origin + ", ip=" + ip + ", rdate=" + rdate + ", path=" + path + ", level=" + level
				+ ", company=" + company + "]";
	}
	
	
}
