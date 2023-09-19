package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.CartDAO;
import kr.co.kmarket.dto.CartDTO;

public enum CartService {

	INSTANCE;
	
	private CartDAO dao = new CartDAO(); 
	
	public void insertCart(CartDTO dto) {
		dao.insertCart(dto);
	}
	public CartDTO selectCart(String cartNo) {
		return null;
	}
	public List<CartDTO> selectCarts(String uid) {
		return dao.selectCarts(uid);
	}
	public void updateCart(CartDTO dto) {}
	public void deleteCart(String cartNo) {}
}
