package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.ProductDTO;

/*
 	작업자 : 강윤수
 	작업날짜 : 2023/09/14
 	작업내용 : insert Product
*/

public class ProductDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO product) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, product.getProdCate1());
			psmt.setInt(2, product.getProdCate2());
			psmt.setString(3, product.getProdName());
			psmt.setString(4, product.getDescript());
			psmt.setString(5, product.getCompany());
			psmt.setString(6, product.getSeller());
			psmt.setInt(7, product.getPrice());
			psmt.setInt(8, product.getDiscount());
			psmt.setInt(9, product.getPoint());
			psmt.setInt(10, product.getStock());
			psmt.setInt(11, product.getDelivery());
			psmt.setString(12, product.getThumb1());
			psmt.setString(13, product.getThumb2());
			psmt.setString(14, product.getThumb3());
			psmt.setString(15, product.getDetail());
			psmt.setString(16, product.getStatus());
			psmt.setString(17, product.getDuty());
			psmt.setString(18, product.getReceipt());
			psmt.setString(19, product.getBizType());
			psmt.setString(20, product.getOrigin());
			psmt.setString(21, product.getIp());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertProduct() error : "+e.getMessage());
		}
	}
	public ProductDTO selectProduct(String cate1, String cate2, String prodNo) {
		
		ProductDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, prodNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new ProductDTO();
				dto.setProdNo(rs.getString("prodNo"));
				dto.setProdCate1(rs.getString("prodCate1"));
				dto.setProdCate2(rs.getString("prodCate2"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				dto.setProdCompany(rs.getString("prodCompany"));
				dto.setSeller(rs.getString("seller"));
				dto.setPrice(rs.getString("price"));
				dto.setDiscount(rs.getString("discount"));
				dto.setPoint(rs.getString("point"));
				dto.setStock(rs.getString("stock"));
				dto.setSold(rs.getString("sold"));
				dto.setDelivery(rs.getString("delivery"));
				dto.setHit(rs.getString("hit"));
				dto.setScore(rs.getString("score"));
				dto.setReview(rs.getString("review"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setDetail(rs.getString("detail"));
				dto.setStatus(rs.getString("status"));
				dto.setDuty(rs.getString("duty"));
				dto.setReceipt(rs.getString("receipt"));
				dto.setBizType(rs.getString("bizType"));
				dto.setOrigin(rs.getString("origin"));
				dto.setIp(rs.getString("ip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setLevel(rs.getString("level"));
				dto.setCompany(rs.getString("company"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectProduct() error :"+e.getMessage());
		}
		return dto;
	}

	public void updateProduct(ProductDTO dto) {}
	public void deleteProduct(String uid, String no) {
		
		try {
			conn= getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_PRODUCT);
			psmt.setString(1, uid);
			psmt.setString(2, no);
			psmt.executeUpdate();
			
			close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public List<ProductDTO> selectProductsAll(int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL);
			psmt.setInt(1, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProdNo(rs.getString("prodNo"));
				dto.setProdCate1(rs.getString("prodCate1"));
				dto.setProdCate2(rs.getString("prodCate2"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				dto.setProdCompany(rs.getString("prodCompany"));
				dto.setSeller(rs.getString("seller"));
				dto.setPrice(rs.getString("price"));
				dto.setDiscount(rs.getString("discount"));
				dto.setPoint(rs.getString("point"));
				dto.setStock(rs.getString("stock"));
				dto.setSold(rs.getString("sold"));
				dto.setDelivery(rs.getString("delivery"));
				dto.setHit(rs.getString("hit"));
				dto.setScore(rs.getString("score"));
				dto.setReview(rs.getString("review"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setDetail(rs.getString("detail"));
				dto.setStatus(rs.getString("status"));
				dto.setDuty(rs.getString("duty"));
				dto.setReceipt(rs.getString("receipt"));
				dto.setBizType(rs.getString("bizType"));
				dto.setOrigin(rs.getString("origin"));
				dto.setIp(rs.getString("ip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setLevel(rs.getString("level"));
				dto.setCompany(rs.getString("company"));
				
				products.add(dto);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectProductsAll() error :"+e.getMessage());
		}
		
		return products;
	}
	
	/************************* 대분류 상품 불러오기 *************************/
	public List<ProductDTO> selectProductsByCate1(String cate1, int start) {
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_BY_CATE1);
			psmt.setString(1, cate1);
			psmt.setInt(2, start);
			
			rs = psmt.executeQuery();

			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProdNo(rs.getString("prodNo"));
				dto.setProdCate1(rs.getString("prodCate1"));
				dto.setProdCate2(rs.getString("prodCate2"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				dto.setProdCompany(rs.getString("prodCompany"));
				dto.setSeller(rs.getString("seller"));
				dto.setPrice(rs.getString("price"));
				dto.setDiscount(rs.getString("discount"));
				dto.setPoint(rs.getString("point"));
				dto.setStock(rs.getString("stock"));
				dto.setSold(rs.getString("sold"));
				dto.setDelivery(rs.getString("delivery"));
				dto.setHit(rs.getString("hit"));
				dto.setScore(rs.getString("score"));
				dto.setReview(rs.getString("review"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setDetail(rs.getString("detail"));
				dto.setStatus(rs.getString("status"));
				dto.setDuty(rs.getString("duty"));
				dto.setReceipt(rs.getString("receipt"));
				dto.setBizType(rs.getString("bizType"));
				dto.setOrigin(rs.getString("origin"));
				dto.setIp(rs.getString("ip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setLevel(rs.getString("level"));
				dto.setCompany(rs.getString("company"));
				
				products.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectProductsByCate1() error :"+e.getMessage());
		}
		return products;
	}
	
	/************************* 소분류 상품 불러오기 *************************/
	public List<ProductDTO> selectProductsByCate2(String cate1, String cate2, int start) {
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_BY_CATE2);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setInt(3, start);
			
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProdNo(rs.getString("prodNo"));
				dto.setProdCate1(rs.getString("prodCate1"));
				dto.setProdCate2(rs.getString("prodCate2"));
				dto.setProdName(rs.getString("prodName"));
				dto.setDescript(rs.getString("descript"));
				dto.setProdCompany(rs.getString("prodCompany"));
				dto.setSeller(rs.getString("seller"));
				dto.setPrice(rs.getString("price"));
				dto.setDiscount(rs.getString("discount"));
				dto.setPoint(rs.getString("point"));
				dto.setStock(rs.getString("stock"));
				dto.setSold(rs.getString("sold"));
				dto.setDelivery(rs.getString("delivery"));
				dto.setHit(rs.getString("hit"));
				dto.setScore(rs.getString("score"));
				dto.setReview(rs.getString("review"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setDetail(rs.getString("detail"));
				dto.setStatus(rs.getString("status"));
				dto.setDuty(rs.getString("duty"));
				dto.setReceipt(rs.getString("receipt"));
				dto.setBizType(rs.getString("bizType"));
				dto.setOrigin(rs.getString("origin"));
				dto.setIp(rs.getString("ip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setLevel(rs.getString("level"));
				dto.setCompany(rs.getString("company"));
				
				products.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectProductsByCate1() error :"+e.getMessage());
		}
		return products;
	}
	/************************* 상품 전체 불러오기 *************************/
	public int selectCountProductsAll() {
		int total = 0;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_COUNT_PRODUCTS_ALL);
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountProductsAll() error : "+e.getMessage());
		}
		return total;
	}
	public int selectCountProductsByCate1(String cate1) {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_BY_CATE1);
			psmt.setString(1, cate1);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountProductsByCate1() error : "+e.getMessage());
		}
		return total;
	}
	public int selectCountProductsByCate2(String cate1, String cate2) {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_BY_CATE2);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountProductsByCate1() error : "+e.getMessage());
		}
		return total;
	}
}
