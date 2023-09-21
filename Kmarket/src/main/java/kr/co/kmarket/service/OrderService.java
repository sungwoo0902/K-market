package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.OrderDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.dto.OrderItemDTO;

public enum OrderService {

	INSTANCE;
	private OrderDAO dao = new OrderDAO();
	
	public void insertOrder(OrderDTO order) {
		dao.insertOrder(order);
	}
	
	public String selectOrdNo(String uid) {
		return dao.selectOrdNo(uid);
	}
	public void selectOrders() {}
	public void updateOrder() {}
	public void deleteOrder() {}
	
	
	public void insertOrderItems(List<CartDTO> carts, String ordNo) {
		for(CartDTO orderItem : carts) {
			dao.insertOrderItem(orderItem, ordNo);
		}
	}
	public List<OrderItemDTO> selectOrderItems(String ordNo) {
		return dao.selectOrderItems(ordNo);
	}
}
