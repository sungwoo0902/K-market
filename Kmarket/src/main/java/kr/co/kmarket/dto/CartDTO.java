package kr.co.kmarket.dto;

public class CartDTO {
	
	private int cartNo;
	private String uid;
	private int prodNo;
	private int count;
	private int cartPrice;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	
	//추가 필드
	private String thumb1;
	private int prodCate1;
	private int prodCate2;
	private String prodName;
	private String descript;
	private int orgPrice;
	
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
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
	public int getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(int orgPrice) {
		this.orgPrice = orgPrice;
	}
	public void setOrgPrice(String orgPrice) {
		this.orgPrice = Integer.parseInt(orgPrice);
	}
	
	/////////////////////////////////////////////////
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public void setCartNo(String cartNo) {
		this.cartNo = Integer.parseInt(cartNo);
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	public int getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}
	public void setCartPrice(String cartPrice) {
		this.cartPrice = Integer.parseInt(cartPrice);
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
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
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setTotal(String total) {
		this.total = Integer.parseInt(total);
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "CartDTO [cartNo=" + cartNo + ", uid=" + uid + ", prodNo=" + prodNo + ", count=" + count + ", cartPrice="
				+ cartPrice + ", discount=" + discount + ", point=" + point + ", delivery=" + delivery + ", total="
				+ total + ", rdate=" + rdate + ", thumb1=" + thumb1 + ", prodCate1=" + prodCate1 + ", prodCate2="
				+ prodCate2 + ", prodName=" + prodName + ", descript=" + descript + ", orgPrice=" + orgPrice + "]";
	}
	

	
	
}
