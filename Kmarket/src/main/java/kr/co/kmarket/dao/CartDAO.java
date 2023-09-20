package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CartDTO;

public class CartDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertCart(CartDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_CART);
			psmt.setString(1, dto.getUid());
			psmt.setInt(2, dto.getProdNo());
			psmt.setInt(3, dto.getCount());
			psmt.setInt(4, dto.getCartPrice());
			psmt.setInt(5, dto.getDiscount());
			psmt.setInt(6, dto.getPoint());
			psmt.setInt(7, dto.getDelivery());
			psmt.setInt(8, dto.getTotal());
			psmt.executeUpdate();

			close();
		} catch (Exception e) {
			logger.error("insertCart() error :"+e.getMessage());
		}
	}
	public CartDTO selectedCart(String uid, String cartNo) {
		
		CartDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_SELECTED_CART);
			psmt.setString(1, uid);
			psmt.setString(2, cartNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CartDTO();
				dto.setCartNo(rs.getString(1));
				dto.setUid(rs.getString(2));
				dto.setProdNo(rs.getString(3));
				dto.setCount(rs.getString(4));
				dto.setCartPrice(rs.getString(5));
				dto.setDiscount(rs.getString(6));
				dto.setPoint(rs.getString(7));
				dto.setDelivery(rs.getString(8));
				dto.setTotal(rs.getString(9));
				dto.setRdate(rs.getString(10));
				dto.setThumb1(rs.getString(11));
				dto.setProdCate1(rs.getString(12));
				dto.setProdCate2(rs.getString(13));
				dto.setProdName(rs.getString(14));
				dto.setDescript(rs.getString(15));
				dto.setOrgPrice(rs.getString(16));
			}
			close();
			logger.debug("selectedCart() complete");
		} catch (Exception e) {
			logger.error("selectedCart() error :"+e.getMessage());
		}
		return dto;
	}
	
	public int selectCountCart(String uid, String prodNo) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_CART);
			psmt.setString(1, uid);
			psmt.setString(2, prodNo);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCart() error :"+e.getMessage());
		}
		return result;
	}
	public List<CartDTO> selectCarts(String uid) {
		List<CartDTO> carts = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CARTS);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCartNo(rs.getString(1));
				dto.setUid(rs.getString(2));
				dto.setProdNo(rs.getString(3));
				dto.setCount(rs.getString(4));
				dto.setCartPrice(rs.getString(5));
				dto.setDiscount(rs.getString(6));
				dto.setPoint(rs.getString(7));
				dto.setDelivery(rs.getString(8));
				dto.setTotal(rs.getString(9));
				dto.setRdate(rs.getString(10));
				dto.setThumb1(rs.getString(11));
				dto.setProdCate1(rs.getString(12));
				dto.setProdCate2(rs.getString(13));
				dto.setProdName(rs.getString(14));
				dto.setDescript(rs.getString(15));
				dto.setOrgPrice(rs.getString(16));
				
				carts.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCarts() error :"+e.getMessage());
		}
		return carts;
	}
	public void updateCart(CartDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_CART);
			psmt.setInt(1, dto.getCount());
			psmt.setInt(2, dto.getTotal());
			psmt.setString(3, dto.getUid());
			psmt.setInt(4, dto.getProdNo());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("updateCart() error :"+e.getMessage());
		}
	}
	public void deleteCart(String uid, String cartNo) {
		
		try {
			logger.debug("deleteCart uid:"+uid);
			logger.debug("deleteCart prodNo:"+cartNo);
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_CART);
			psmt.setString(1, uid);
			psmt.setString(2, cartNo);
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("deleteCart() error :"+e.getMessage());
		}
		
	}
	
}
